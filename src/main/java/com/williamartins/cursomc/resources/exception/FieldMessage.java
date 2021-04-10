package com.williamartins.cursomc.resources.exception;

import java.io.Serializable;

/*Classe auxiliar para carregar nomes e campo do erro
 * quando der erro de "Preenchimento obrigatorio" e "caracteres"
 * validar cpf ou cnpj, email, emitindo. realatorio dos erros*/
public class FieldMessage  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String fieldName;
	private String message;
	
	public FieldMessage() {}

	public FieldMessage(String fieldName, String message) {
		super();
		this.fieldName = fieldName;
		this.message = message;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
