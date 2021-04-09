package com.williamartins.cursomc.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.williamartins.cursomc.services.exception.DataIntegrityException;

import javassist.tools.rmi.ObjectNotFoundException;

/*
 * 
 * classe que captura exceções, Manipulador de exceção do meu recurso.
 * Informa o codigo HTTP, o instante que ocorreu o erro etc..
 * 
 * */
@ControllerAdvice
public class ResourceExceptionHandler {
	
    /*Exceção personalizada para Categoria "Id não encontrado"*/
	@ExceptionHandler(ObjectNotFoundException.class)/*@ExceptionHandler tratador de execeção do tipo de exceçãon ObjectNotFoundException.class*/
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		/*NOT_FOUND "404" indica que o cliente pôde comunicar com o servidor, 
		 * mas o servidor não pôde encontrar o que foi pedido, ou foi configurado para não cumprir o pedido e não 
		 * revelar a razão.*/
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	/*Exceção personalizada para Categoria delete "Não pode excluir categoria com produtos."*/
	@ExceptionHandler(DataIntegrityException.class)/*@ExceptionHandler tratador de execeção do tipo de exceçãon ObjectNotFoundException.class*/
	public ResponseEntity<StandardError> dataError(DataIntegrityException e, HttpServletRequest request){
		
		/* 400 "Bad Request" pode ocorrer devido a uma URL digitada incorretamente, sintaxe malformada 
		 * ou uma URL que contenha caracteres ilegais ou exclusão de registro indevido */
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	
	/*Exceção personalizada para Categoria delete "Não pode excluir categoria com produtos."*/
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request){
		
	ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de validação", System.currentTimeMillis());
	//percorrendo a lista de erros que ja tem dentro da exceção "MethodArgumentNotValidException"
	 for(FieldError x : e.getBindingResult().getFieldErrors()) {
		 err.addError(x.getField(), x.getDefaultMessage());
	 }
	
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	
	
	
	
	
}
