package com.williamartins.cursomc.services.exception;
/*
 * 
 * Camada de exceção para erros. tratando exceção com REST
 * 
 * */
public class DataIntegrityException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public DataIntegrityException(String msg) {
		super(msg);
	}
/*Sobrecarga para receber a Msg e outra exceção da causa que aconteceu anterior. */
	public DataIntegrityException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
