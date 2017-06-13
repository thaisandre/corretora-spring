package br.com.caelum.corretora.validators;

import java.time.LocalDate;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.caelum.corretora.modelo.Aplicacao;

public class AplicacaoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Aplicacao.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Aplicacao aplicacao = (Aplicacao) target;
		
	}
	
	
}
