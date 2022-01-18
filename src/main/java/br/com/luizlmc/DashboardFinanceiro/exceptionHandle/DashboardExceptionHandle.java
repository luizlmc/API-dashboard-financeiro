package br.com.luizlmc.DashboardFinanceiro.exceptionHandle;

import java.util.*;
import java.util.stream.Collectors;

import br.com.luizlmc.DashboardFinanceiro.service.exception.NonexistentOrInactivePersonException;
import lombok.Getter;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class DashboardExceptionHandle extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        String userMessage = messageSource.getMessage("invalid.message", null, LocaleContextHolder.getLocale());
        String developerMessage = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
        List<Error> errors = Arrays.asList(new Error(userMessage, developerMessage));
        return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        //Get all errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, headers, status);

    }

    @ExceptionHandler({ EmptyResultDataAccessException.class })
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
        String userMessage = messageSource.getMessage("resource.not-found", null, LocaleContextHolder.getLocale());
        String developerMessage = ex.toString();
        List<Error> errors = Arrays.asList(new Error(userMessage, developerMessage));
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ DataIntegrityViolationException.class })
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        String userMessage = messageSource.getMessage("resource.operation-not-allowed", null, LocaleContextHolder.getLocale());
        String developerMessage = ExceptionUtils.getRootCauseMessage(ex);
        List<Error> errors = Arrays.asList(new Error(userMessage, developerMessage));
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        List<Error> errors = criarListaDeErros(ex);
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ NonexistentOrInactivePersonException.class })
    public ResponseEntity<Object> handleNonexistentOrInactivePersonException(NonexistentOrInactivePersonException ex) {
        String userMessage = messageSource.getMessage("person.nonexistent-or-inactive", null, LocaleContextHolder.getLocale());
        String developerMessage = ex.toString();
        List<DashboardExceptionHandle.Error> errors = Arrays.asList(new Error(userMessage, developerMessage));
        return ResponseEntity.badRequest().body(errors);
    }

    private List<Error> criarListaDeErros(ConstraintViolationException ex) {
        List<Error> erros = new ArrayList<>();

        for (ConstraintViolation<?> cv : ex.getConstraintViolations()) {
            String mensagemUsuario = cv.getPropertyPath().toString().toUpperCase().concat(" " + cv.getMessage());
            String mensagemDesenvolvedor = cv.toString();
            erros.add(new Error(mensagemUsuario, mensagemDesenvolvedor));
        }

        return erros;
    }

    @Getter
    public static class Error {

        private String userMessage;
        private String developerMessage;

        public Error(String userMessage, String developerMessage) {
            super();
            this.userMessage = userMessage;
            this.developerMessage = developerMessage;
        }
    }
}