package com.lucaspedroso.osworks.osworksapi.api.exceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Problema {

  private Integer status;
  private OffsetDateTime dataHora;
  private String titulo;
  private List<Campo> campos;

  public Problema(Integer status, String titulo, OffsetDateTime dataHora, List<Campo> campos) {
    this.status = status;
    this.dataHora = dataHora;
    this.titulo = titulo;
    this.campos = campos;
  }

  public static class Campo {
    private String name;
    private String message;

    public Campo(String name, String message) {
      this.name = name;
      this.message = message;
    }

    public String getName() {
      return this.name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getMessage() {
      return this.message;
    }

    public void setMessage(String message) {
      this.message = message;
    }
  }

  public Integer getStatus() {
    return this.status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public OffsetDateTime getDataHora() {
    return this.dataHora;
  }

  public void setDataHora(OffsetDateTime dataHora) {
    this.dataHora = dataHora;
  }

  public String getTitulo() {
    return this.titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public List<Campo> getCampos() {
    return this.campos;
  }

  public void setCampos(List<Campo> campos) {
    this.campos = campos;
  }

}