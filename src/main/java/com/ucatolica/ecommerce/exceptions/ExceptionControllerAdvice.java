package com.ucatolica.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Clase de asesoramiento del controlador que maneja excepciones específicas en la aplicación.
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

    /**
     * Maneja excepciones de tipo `UpdateFailException` y genera una respuesta de error con estado "Bad Request".
     *
     * @param exception La excepción `UpdateFailException` a manejar.
     * @return Una respuesta con estado "Bad Request" y el mensaje de error de la excepción.
     */
    @ExceptionHandler(value = UpdateFailException.class)
    public final ResponseEntity<String> handleUpdateFailException(UpdateFailException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja excepciones de tipo `AuthenticationFailException` y genera una respuesta de error con estado "Bad Request".
     *
     * @param exception La excepción `AuthenticationFailException` a manejar.
     * @return Una respuesta con estado "Bad Request" y el mensaje de error de la excepción.
     */
    @ExceptionHandler(value = AuthenticationFailException.class)
    public final ResponseEntity<String> handleUpdateFailException(AuthenticationFailException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja excepciones de tipo `CustomException` y genera una respuesta de error con estado "Bad Request".
     *
     * @param exception La excepción `CustomException` a manejar.
     * @return Una respuesta con estado "Bad Request" y el mensaje de error de la excepción.
     */
    @ExceptionHandler(value = CustomException.class)
    public final ResponseEntity<String> handleUpdateFailException(CustomException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
