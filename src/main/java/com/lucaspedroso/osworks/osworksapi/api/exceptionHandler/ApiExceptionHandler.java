package com.lucaspedroso.osworks.osworksapi.api.exceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;

import com.lucaspedroso.osworks.osworksapi.domain.exception.EntidadeNaoEncontradaException;
import com.lucaspedroso.osworks.osworksapi.domain.exception.NegocioException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  @Autowired
  private MessageSource messageSource;

  @ExceptionHandler(EntidadeNaoEncontradaException.class)
  public ResponseEntity<Object> handleEntidadeNaoEncontrada(NegocioException ex, WebRequest request) {
    var status = HttpStatus.NOT_FOUND;

    var problema = new Problema(status.value(), ex.getMessage(), OffsetDateTime.now(), null);

    return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
  }

  @ExceptionHandler(NegocioException.class)
  public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request) {
    var status = HttpStatus.BAD_REQUEST;

    var problema = new Problema(status.value(), ex.getMessage(), OffsetDateTime.now(), null);

    return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {

    var campos = new ArrayList<Problema.Campo>();

    for (ObjectError error : ex.getBindingResult().getAllErrors()) {
      String nome = ((FieldError) error).getField();
      String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());

      campos.add(new Problema.Campo(nome, mensagem));
    }

    var problema = new Problema(status.value(),
        "Um ou mais campos estão inválidos. " + "Faça o preenchimento correto e tente novamente", OffsetDateTime.now(),
        campos);

    return super.handleExceptionInternal(ex, problema, headers, status, request);
  }

}