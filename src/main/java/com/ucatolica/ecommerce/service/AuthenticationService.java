package com.ucatolica.ecommerce.service;


import com.ucatolica.ecommerce.config.MessageStrings;
import com.ucatolica.ecommerce.exceptions.AuthenticationFailException;
import com.ucatolica.ecommerce.model.AuthenticationToken;
import com.ucatolica.ecommerce.model.User;
import com.ucatolica.ecommerce.repository.TokenRepository;
import com.ucatolica.ecommerce.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio encargado de gestionar la autenticación de usuarios y tokens de autenticación en el sistema de comercio electrónico.
 * Proporciona métodos para guardar, obtener y autenticar tokens de autenticación, así como obtener el usuario asociado a un token.
 */
@Service
public class AuthenticationService {

    @Autowired
    TokenRepository repository;

    /**
     * Guarda un token de autenticación en la base de datos.
     *
     * @param authenticationToken El token de autenticación que se va a guardar.
     */
    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        repository.save(authenticationToken);
    }

    /**
     * Obtiene el token de autenticación asociado a un usuario.
     *
     * @param user El usuario del que se desea obtener el token de autenticación.
     * @return El token de autenticación asociado al usuario.
     */
    public AuthenticationToken getToken(User user) {
        return repository.findTokenByUser(user);
    }

    /**
     * Obtiene el usuario asociado a un token de autenticación.
     *
     * @param token El token de autenticación del que se desea obtener el usuario.
     * @return El usuario asociado al token de autenticación.
     */
    public User getUser(String token) {
        AuthenticationToken authenticationToken = repository.findTokenByToken(token);
        if (Helper.notNull(authenticationToken)) {
            if (Helper.notNull(authenticationToken.getUser())) {
                return authenticationToken.getUser();
            }
        }
        return null;
    }

    /**
     * Autentica un token de autenticación.
     *
     * @param token El token de autenticación que se va a autenticar.
     * @throws AuthenticationFailException Si la autenticación falla debido a la ausencia o invalidez del token.
     */
    public void authenticate(String token) throws AuthenticationFailException {
        if (!Helper.notNull(token)) {
            throw new AuthenticationFailException(MessageStrings.AUTH_TOEKN_NOT_PRESENT);
        }
        if (!Helper.notNull(getUser(token))) {
            throw new AuthenticationFailException(MessageStrings.AUTH_TOEKN_NOT_VALID);
        }
    }
}
