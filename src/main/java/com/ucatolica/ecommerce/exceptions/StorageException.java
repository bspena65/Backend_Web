package com.ucatolica.ecommerce.exceptions;

/**
 * Excepción personalizada que se lanza cuando ocurren errores relacionados con el almacenamiento en la aplicación.
 */
public class StorageException extends RuntimeException {

	/**
	 * Crea una nueva instancia de la excepción de almacenamiento con un mensaje de error.
	 *
	 * @param message El mensaje de error que describe la causa de la excepción.
	 */
	public StorageException(String message) {
		super(message);
	}

	/**
	 * Crea una nueva instancia de la excepción de almacenamiento con un mensaje de error y una causa subyacente.
	 *
	 * @param message El mensaje de error que describe la causa de la excepción.
	 * @param cause   La causa subyacente de la excepción.
	 */
	public StorageException(String message, Throwable cause) {
		super(message, cause);
	}
}
