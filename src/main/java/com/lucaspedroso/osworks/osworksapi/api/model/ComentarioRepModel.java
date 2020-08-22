package com.lucaspedroso.osworks.osworksapi.api.model;

import java.time.OffsetDateTime;

public class ComentarioRepModel {
  private Long id;
  private String descricao;
  private OffsetDateTime dataEnvio;

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescricao() {
    return this.descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public OffsetDateTime getDataEnvio() {
    return this.dataEnvio;
  }

  public void setDataEnvio(OffsetDateTime dataEnvio) {
    this.dataEnvio = dataEnvio;
  }

}