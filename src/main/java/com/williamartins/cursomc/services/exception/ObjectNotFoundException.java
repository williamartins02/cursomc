package com.williamartins.cursomc.services.exception;


/*
 * 
 * Camada de exceção para erros. tratando exceção com REST
 * 
 * */
public class ObjectNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(String msg) {
		super(msg);
	}
/*Sobrecarga para receber a Msg e outra exceção da causa que aconteceu anterior. */
	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
