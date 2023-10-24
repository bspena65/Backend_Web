package com.ucatolica.ecommerce.controller;

import com.ucatolica.ecommerce.dto.*;
import com.ucatolica.ecommerce.dto.user.*;
import com.ucatolica.ecommerce.exceptions.AuthenticationFailException;
import com.ucatolica.ecommerce.exceptions.CustomException;
import com.ucatolica.ecommerce.model.User;
import com.ucatolica.ecommerce.repository.UserRepository;
import com.ucatolica.ecommerce.service.AuthenticationService;
import com.ucatolica.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador que gestiona las operaciones relacionadas con usuarios.
 */
@RequestMapping("user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserService userService;

    /**
     * Obtiene todos los usuarios.
     *
     * @param token El token de autenticación del usuario que realiza la solicitud.
     * @return Una lista de todos los usuarios.
     * @throws AuthenticationFailException Si la autenticación falla.
     */
    @GetMapping("/all")
    public List<User> findAllUser(@RequestParam("token") String token) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        return userRepository.findAll();
    }

    /**
     * Registra un nuevo usuario.
     *
     * @param signupDto Los datos de registro del usuario.
     * @return Un objeto ResponseDto con la respuesta de registro.
     * @throws CustomException Si ocurre una excepción personalizada.
     */
    @PostMapping("/signup")
    public ResponseDto Signup(@RequestBody SignupDto signupDto) throws CustomException {
        return userService.signUp(signupDto);
    }

    /**
     * Inicia sesión con un usuario existente.
     *
     * @param signInDto Los datos de inicio de sesión del usuario.
     * @return Un objeto SignInResponseDto con la respuesta de inicio de sesión.
     * @throws CustomException Si ocurre una excepción personalizada.
     */
    @PostMapping("/signIn")
    public SignInResponseDto Signup(@RequestBody SignInDto signInDto)
            throws CustomException {
        return userService.signIn(signInDto);
    }

    /**
     * Actualiza la información de un usuario.
     *
     * @param token         El token de autenticación del usuario que realiza la solicitud.
     * @param userUpdateDto Los datos de actualización del usuario.
     * @return Un objeto ResponseDto con la respuesta de actualización del usuario.
     */
    @PostMapping("/updateUser")
    public ResponseDto updateUser(@RequestParam("token") String token, @RequestParam("userId") Integer userId, @RequestBody UserUpdateDto userUpdateDto) {
        authenticationService.authenticate(token);
        return userService.updateUser(userId, userUpdateDto);
    }


    /**
     * Crea un nuevo usuario.
     *
     * @param token          El token de autenticación del usuario que realiza la solicitud.
     * @param userCreateDto  Los datos para crear un nuevo usuario.
     * @return Un objeto ResponseDto con la respuesta de creación del usuario.
     * @throws CustomException Si ocurre una excepción personalizada.
     * @throws AuthenticationFailException Si la autenticación falla.

    @PostMapping("/createUser")
    public ResponseDto updateUser(@RequestParam("token") String token, @RequestBody UserCreateDto userCreateDto)
            throws CustomException, AuthenticationFailException {
        authenticationService.authenticate(token);
        return userService.createUser(token, userCreateDto);
    }
     */
}
