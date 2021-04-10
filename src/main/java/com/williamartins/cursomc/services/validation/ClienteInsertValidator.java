package com.williamartins.cursomc.services.validation;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.williamartins.cursomc.domain.Cliente;
import com.williamartins.cursomc.domain.enums.TipoCliente;
import com.williamartins.cursomc.dto.ClienteNewDTO;
import com.williamartins.cursomc.repositories.ClienteRepository;
import com.williamartins.cursomc.resources.exception.FieldMessage;
import com.williamartins.cursomc.services.validation.utils.BR;

/*Validation para CPF e CNPJ, usando o metodo dentro do PACOTE "Validation.utils, 
 * instanciando dentro da class ClienteInsertValidator para verificar a veracidade do CPF ou CNPJ"*/
public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	@Override
	public void initialize(ClienteInsert ann) {
	}
	
	//instanciando o respository do cliente.
	@Autowired
	private ClienteRepository repo;

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();

		/*Verificando o cpf ou cnpj, e programado as messagem de erro.*/
		
		if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCpf(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		} 
		
		if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCnpj(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		} //fim
		
		/*Logica pra testar se o e-mail do cliente ja existe */
		Cliente aux = repo.findByEmail(objDto.getEmail());
		if (aux != null) {
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


