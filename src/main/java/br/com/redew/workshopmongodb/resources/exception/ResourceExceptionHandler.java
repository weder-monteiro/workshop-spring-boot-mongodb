package br.com.redew.workshopmongodb.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.redew.workshopmongodb.services.exception.ObjectNotFoundException;

@ControllerAdvice // Tratar possiveis erros nas requisições
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class) //Quando ocorrer essa exception faz o tratamento
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Não encontrado",
				e.getMessage(), request.getRequestURI());

		return ResponseEntity.status(status).body(err);
	}
}
