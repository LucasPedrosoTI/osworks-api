package com.lucaspedroso.osworks.osworksapi.domain.exception;

public class NegocioException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public NegocioException(final String message) {
    super(message);
  }
}