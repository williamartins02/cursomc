package com.williamartins.cursomc.services.validation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.williamartins.cursomc.domain.Cliente;
import com.williamartins.cursomc.dto.ClienteDTO;
import com.williamartins.cursomc.repositories.ClienteRepository;
import com.williamartins.cursomc.resources.exception.FieldMessage;

/*Metodo auxiliar para validação para a anotações*/
public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	@Override
	public void initialize(ClienteUpdate ann) {
	}
	
	//instanciando o respository do cliente.
	@Autowired
	private ClienteRepository repo;
	
	//tem a funcçao que permiti opter o prametro da URi "URl"
	@Autowired
	private HttpServletRequest request;

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		
		/*condigo para pega id de cliente ja cadastrado no bd "exemplo : cliente/2" passando pela URL*/
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));//FIm
		
		List<FieldMessage> list = new ArrayList<>();

		/*Logica que faz verificação de email ja existente no bD, e  compara com o insert do novo cliente*/
		Cliente aux = repo.findByEmail(objDto.getEmail());
		if (aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email","E-mail já Existente"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}


