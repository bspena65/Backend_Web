package com.ucatolica.ecommerce.service;


import com.ucatolica.ecommerce.config.MessageStrings;
import com.ucatolica.ecommerce.dto.*;
import com.ucatolica.ecommerce.dto.user.*;
import com.ucatolica.ecommerce.enums.ResponseStatus;
import com.ucatolica.ecommerce.enums.Role;
import com.ucatolica.ecommerce.exceptions.AuthenticationFailException;
import com.ucatolica.ecommerce.exceptions.CustomException;
import com.ucatolica.ecommerce.model.AuthenticationToken;
import com.ucatolica.ecommerce.model.User;
import com.ucatolica.ecommerce.repository.UserRepository;
import com.ucatolica.ecommerce.utils.Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import static com.ucatolica.ecommerce.config.MessageStrings.USER_CREATED;

/**
 * Servicio que gestiona las operaciones relacionadas con los usuarios en el sistema de comercio electrónico.
 * Proporciona métodos para el registro de usuarios, inicio de sesión, creación y actualización de usuarios, y verificación de permisos.
 */

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    private EmailService emailService;

    Logger logger = LoggerFactory.getLogger(UserService.class);



    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param signupDto Datos de registro del nuevo usuario.
     * @return Un objeto ResponseDto que indica si el registro fue exitoso.
     * @throws CustomException Si se produce un error durante el registro.
     */
    public ResponseDto signUp(SignupDto signupDto)  throws CustomException {
        // Verificar si la dirección de correo electrónico ya ha sido registrada.
        if (Helper.notNull(userRepository.findByEmail(signupDto.getEmail()))) {
            // Si la dirección de correo electrónico ya está registrada, lanzar una excepción.
            throw new CustomException("User already exists");
        }
        // Primero encriptar la contraseña
        String encryptedPassword = signupDto.getPassword();
        try {
            encryptedPassword = hashPassword(signupDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.error("hashing password failed {}", e.getMessage());
        }


        User user = new User(signupDto.getName(), signupDto.getLastName(), signupDto.getEmail(), Role.user, encryptedPassword );

        User createdUser;
        try {
            // Guarda el usuario
            createdUser = userRepository.save(user);
            // generacion de token para el user
            final AuthenticationToken authenticationToken = new AuthenticationToken(createdUser);
            // guarda token en database
            authenticationService.saveConfirmationToken(authenticationToken);
            // Después de crear el usuario, enviar un correo electrónico de bienvenida
            emailService.enviarCorreo(signupDto.getEmail(), "Bienvenido a Ecommerce", "Gracias por registrarte en nuestro sitio.");
            // éxito en la creación
            return new ResponseDto(ResponseStatus.success.toString(), USER_CREATED);
        } catch (Exception e) {
            // manejar el error de registro
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Inicia sesión de un usuario en el sistema.
     *
     * @param signInDto Datos de inicio de sesión del usuario.
     * @return Un objeto SignInResponseDto que contiene el resultado de la autenticación.
     * @throws CustomException Si se produce un error durante el inicio de sesión.
     */
    public SignInResponseDto signIn(SignInDto signInDto) throws CustomException {
        // Primero, buscar al usuario por correo electrónico.
        User user = userRepository.findByEmail(signInDto.getEmail());
        if(!Helper.notNull(user)){
            throw  new AuthenticationFailException("user not present");
        }
        try {
            // Verificar si la contraseña es correcta.
            if (!user.getPassword().equals(hashPassword(signInDto.getPassword()))){
                // Si la contraseña no coincide.
                throw  new AuthenticationFailException(MessageStrings.WRONG_PASSWORD);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.error("hashing password failed {}", e.getMessage());
            throw new CustomException(e.getMessage());
        }

        // Obtener el token de autenticación para el usuario.
        AuthenticationToken token = authenticationService.getToken(user);

        if(!Helper.notNull(token)) {
            // Si no se encuentra el token.
            throw new CustomException("token not present");
        }

        return new SignInResponseDto ("success", token.getToken(), user.getRole().toString());
    }



    /**
     * Calcula el hash de una contraseña utilizando el algoritmo MD5.
     *
     * @param password La contraseña que se va a hashear.
     * @return El hash de la contraseña.
     * @throws NoSuchAlgorithmException Si no se encuentra el algoritmo de hash MD5.
     */
    String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return myHash;
    }


    /**
     * Crea un nuevo usuario en el sistema con privilegios específicos.
     *
     * @param token        El token de autenticación del usuario que realiza la creación.
     * @param userCreateDto Los datos del usuario que se va a crear.
     * @return Un objeto ResponseDto que indica el resultado de la creación del usuario.
     * @throws CustomException Si se produce un error durante la creación del usuario.
     * @throws AuthenticationFailException Si el usuario que realiza la creación no tiene permisos suficientes.
     */
    public ResponseDto createUser(String token, UserCreateDto userCreateDto) throws CustomException, AuthenticationFailException {
        // Obtener el usuario que realiza la creación a partir del token.
        User creatingUser = authenticationService.getUser(token);

        // Verificar si el usuario tiene permisos para crear un nuevo usuario.
        if (!canCrudUser(creatingUser.getRole())) {
            // El usuario no tiene permisos para crear un nuevo usuario.
            throw  new AuthenticationFailException(MessageStrings.USER_NOT_PERMITTED);
        }
        // Encriptar la contraseña del nuevo usuario.
        String encryptedPassword = userCreateDto.getPassword();
        try {
            encryptedPassword = hashPassword(userCreateDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.error("hashing password failed {}", e.getMessage());
        }

        // Crear el nuevo usuario.
        User user = new User(userCreateDto.getName(), userCreateDto.getLastName(), userCreateDto.getEmail(), userCreateDto.getRole(), encryptedPassword );
        User createdUser;
        try {
            // Guardar el usuario en la base de datos.
            createdUser = userRepository.save(user);
            // Crear y guardar el token de autenticación para el nuevo usuario.
            final AuthenticationToken authenticationToken = new AuthenticationToken(createdUser);
            authenticationService.saveConfirmationToken(authenticationToken);
            return new ResponseDto(ResponseStatus.success.toString(), USER_CREATED);
        } catch (Exception e) {
            // Manejar errores en la creación del usuario.
            throw new CustomException(e.getMessage());
        }

    }

    /**
     * Comprueba si un usuario tiene permisos para realizar operaciones CRUD en usuarios.
     *
     * @param role El rol del usuario.
     * @return `true` si el usuario tiene permisos para realizar operaciones CRUD en usuarios, `false` en caso contrario.
     */
    boolean canCrudUser(Role role) {
        if (role == Role.admin) {
            return true;
        }
        return false;
    }

    /**
     * Comprueba si un usuario tiene permisos para realizar operaciones CRUD en un usuario específico.
     *
     * @param userUpdating       El usuario que realiza la actualización.
     * @param userIdBeingUpdated El ID del usuario que se va a actualizar.
     * @return `true` si el usuario tiene permisos para realizar operaciones CRUD en el usuario especificado, `false` en caso contrario.
     */
    boolean canCrudUser(User userUpdating, Integer userIdBeingUpdated) {
        Role role = userUpdating.getRole();
        // Los administradores pueden realizar operaciones CRUD en cualquier usuario.
        if (role == Role.admin) {
            return true;
        }
        // Un usuario puede actualizar su propio registro, pero no su rol.
        if (role == Role.user && userUpdating.getId() == userIdBeingUpdated) {
            return true;
        }
        // En cualquier otro caso, no se tienen permisos para realizar operaciones CRUD.
        return false;
    }

    /**
     * Actualiza los datos de un usuario en el sistema.
     *
     * @param userUpdateDto El DTO que contiene los datos actualizados del usuario.
     * @return Un objeto ResponseDto que indica el resultado de la actualización del usuario.
     * @throws CustomException Si ocurre un error durante la actualización del usuario.
     */
    public ResponseDto updateUser(Integer userId, UserUpdateDto userUpdateDto) throws CustomException {
        // Intenta buscar al usuario que se va a actualizar por su ID
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            // El usuario fue encontrado, ahora puedes actualizar sus datos
            User existingUser = optionalUser.get();

            // Actualiza los campos necesarios del usuario con los valores de userUpdateDto
            existingUser.setName(userUpdateDto.getName());
            existingUser.setLastName(userUpdateDto.getLastName());
            existingUser.setRole(userUpdateDto.getRole());

            // Si hay otros campos que necesitas actualizar, hazlo aquí

            // Luego, guarda el usuario actualizado en la base de datos
            try {
                userRepository.save(existingUser);
                // Devuelve un ResponseDto de éxito si la actualización se realiza con éxito
                return new ResponseDto(ResponseStatus.success.toString(), "Usuario actualizado exitosamente");
            } catch (Exception e) {
                // Maneja cualquier excepción que pueda ocurrir al guardar el usuario actualizado
                throw new CustomException("Error al actualizar el usuario: " + e.getMessage());
            }
        } else {
            // El usuario no fue encontrado, puedes manejar esta situación como desees
            throw new CustomException("Usuario no encontrado");
        }
    }



}
