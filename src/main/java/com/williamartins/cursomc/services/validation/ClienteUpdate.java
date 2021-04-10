package com.williamartins.cursomc.services.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/* Criaçao da a anotação customizada @ClienteInsert*/

@Constraint(validatedBy = ClienteUpdateValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)

/* Validação personalizada; */

public @interface ClienteUpdate {
		String message() default "Erro de validação";
	
		Class<?>[] groups() default {};
	
		Class<? extends Payload>[] payload() default {};
	}
