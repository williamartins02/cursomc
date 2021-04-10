package com.williamartins.cursomc.services.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/* Criaçao da a anotação customizada Pra validar CPF ou CNPJ*/

@Constraint(validatedBy = ClienteInsertValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)

/* Validação personalizada; */

public @interface ClienteInsert {
		String message() default "Erro de validação";
	
		Class<?>[] groups() default {};
	
		Class<? extends Payload>[] payload() default {};
	}
