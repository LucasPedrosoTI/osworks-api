package com.lucaspedroso.osworks.osworksapi.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.lucaspedroso.osworks.osworksapi.domain.model.StatusOrdemServico;

public class OrdemServicoRepModel {

  private Long id;
  private ClienteRepModel cliente;
  private String descricao;
  private BigDecimal preco;
  private StatusOrdemServico statusOrdem;
  private OffsetDateTime dataAbertura;
  private OffsetDateTime dataFinalizacao;

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

  public BigDecimal getPreco() {
    return this.preco;
  }

  public void setPreco(BigDecimal preco) {
    this.preco = preco;
  }

  public StatusOrdemServico getStatusOrdem() {
    return this.statusOrdem;
  }

  public void setStatusOrdem(StatusOrdemServico statusOrdem) {
    this.statusOrdem = statusOrdem;
  }

  public OffsetDateTime getDataAbertura() {
    return this.dataAbertura;
  }

  public void setDataAbertura(OffsetDateTime dataAbertura) {
    this.dataAbertura = dataAbertura;
  }

  public OffsetDateTime getDataFinalizacao() {
    return this.dataFinalizacao;
  }

  public void setDataFinalizacao(OffsetDateTime dataFinalizacao) {
    this.dataFinalizacao = dataFinalizacao;
  }

  public ClienteRepModel getCliente() {
    return this.cliente;
  }

  public void setCliente(ClienteRepModel cliente) {
    this.cliente = cliente;
  }

}