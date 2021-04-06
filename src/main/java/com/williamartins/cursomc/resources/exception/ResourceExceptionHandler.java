package com.williamartins.cursomc.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javassist.tools.rmi.ObjectNotFoundException;

/*
 * 
 * classe que captura exceções, Manipulador de exceção do meu recurso.
 * Informa o codigo HTTP, o instante que ocorreu o erro etc..
 * 
 * */
@ControllerAdvice
public class ResourceExceptionHandler {
 
	@ExceptionHandler(ObjectNotFoundException.class)/*@ExceptionHandler tratador de execeção do tipo de exceçãon ObjectNotFoundException.class*/
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		/*NOT_FOUND "404" mostrando o status do erro, Mensagem da excecção, e o horario local do sistema */
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
}
