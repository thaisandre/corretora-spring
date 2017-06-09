package br.com.caelum.corretora.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.caelum.corretora.modelo.Usuario;

public class UsuarioValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Usuario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "senha", "field.required");
		Usuario usuario = (Usuario) target;
		
		if(usuario.getLogin().isEmpty()) {
			errors.rejectValue("login", "field.required");
		}
	}

}
