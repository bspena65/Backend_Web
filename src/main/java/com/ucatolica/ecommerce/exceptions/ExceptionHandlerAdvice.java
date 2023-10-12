package com.ucatolica.ecommerce.exceptions;

import com.ucatolica.ecommerce.common.ApiResponse;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * Clase que maneja y gestiona las excepciones en la aplicación.
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

	/**
	 * Maneja excepciones de violación de integridad de datos y genera una respuesta de conflicto.
	 *
	 * @param ex La excepción de violación de integridad de datos.
	 * @return Una respuesta de conflicto con un mensaje de error.
	 */
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ApiResponse> conflict(DataIntegrityViolationException ex) {
		String message = getMostSpecificMessage(ex);

		return new ResponseEntity<ApiResponse>(new ApiResponse(false, message), HttpStatus.CONFLICT);
	}

	// @ExceptionHandler(AccessDeniedException.class)
	// public ResponseEntity<ApiResponse> accessDenied(AccessDeniedException ex){
	//     String message = ex.getMessage();
	//
	//     return new ResponseEntity<ApiResponse>(new ApiResponse(false, message), HttpStatus.FORBIDDEN);
	// }

	/**
	 * Maneja excepciones de validación y genera una respuesta con estado "Unprocessable Entity".
	 *
	 * @param ex La excepción de validación.
	 * @return Una respuesta con estado "Unprocessable Entity" y un mensaje de error.
	 */
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ApiResponse> validationException(ValidationException ex) {
		String message = ex.getMessage();

		return new ResponseEntity<ApiResponse>(new ApiResponse(false, message), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	/**
	 * Maneja excepciones de tipo de argumento de método incorrecto y genera una respuesta con estado "Internal Server Error".
	 *
	 * @param ex La excepción de tipo de argumento de método incorrecto.
	 * @return Una respuesta con estado "Internal Server Error" y un mensaje de error.
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ApiResponse> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
		ex.printStackTrace();

		String message = ex.getMessage();
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, message), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Maneja excepciones no controladas y genera una respuesta con estado "Internal Server Error".
	 *
	 * @param ex La excepción no controlada.
	 * @return Una respuesta con estado "Internal Server Error" y un mensaje de error.
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse> unhandledExceptions(Exception ex) {
		String message = NestedExceptionUtils.getMostSpecificCause(ex).getMessage();

		ex.printStackTrace();

		return new ResponseEntity<ApiResponse>(new ApiResponse(false, message), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Obtiene el mensaje más específico de una excepción de violación de integridad de datos.
	 *
	 * @param ex La excepción de violación de integridad de datos.
	 * @return El mensaje más específico de la excepción.
	 */
	private String getMostSpecificMessage(DataIntegrityViolationException ex) {
		String message = NestedExceptionUtils.getMostSpecificCause(ex).getMessage();

		if (message.contains("Detail:")) {
			message = message.substring(message.indexOf("Detail:") + "Detail:".length());
		}

		return message;
	}
}
