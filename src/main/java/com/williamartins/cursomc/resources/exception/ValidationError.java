package com.williamartins.cursomc.resources.exception;

import java.util.ArrayList;
import java.util.List;

/*SubClasse do StandardError, vai ter todos os dados do StandardError*/
public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;
	
	/*Alem de herda os dados tambem a lista de Messagem */
	private List<FieldMessage> errors = new ArrayList<>();
	
	
	/*Herdando atributos da class StandardError*/
	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
		
	}
	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String messagem) {
	   errors.add(new FieldMessage(fieldName, messagem));
	}
	
}
