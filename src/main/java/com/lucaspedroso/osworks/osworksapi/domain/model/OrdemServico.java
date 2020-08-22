package com.lucaspedroso.osworks.osworksapi.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.lucaspedroso.osworks.osworksapi.domain.exception.NegocioException;

@Entity
public class OrdemServico {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long id;

  @ManyToOne
  private Cliente cliente;

  private String descricao;
  private BigDecimal preco;

  @Enumerated(EnumType.STRING)
  private StatusOrdemServico status;

  private OffsetDateTime dataAbertura;

  private OffsetDateTime dataFinalizacao;

  @OneToMany(mappedBy = "ordemServico")
  private List<Comentario> comentarios = new ArrayList<>();

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Cliente getCliente() {
    return this.cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
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

  public StatusOrdemServico getStatus() {
    return this.status;
  }

  public void setStatus(StatusOrdemServico status) {
    this.status = status;
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

  public List<Comentario> getComentarios() {
    return this.comentarios;
  }

  public void setComentarios(List<Comentario> comentarios) {
    this.comentarios = comentarios;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof OrdemServico)) {
      return false;
    }
    OrdemServico ordemServico = (OrdemServico) o;
    return Objects.equals(id, ordemServico.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  public boolean podeSerFinalizada() {
    return StatusOrdemServico.ABERTA.equals(getStatus());
  }

  public boolean naoPodeSerFinalizada() {
    return !podeSerFinalizada();
  }

  public void finalizar() {
    if (naoPodeSerFinalizada()) {
      throw new NegocioException("Ordem de serviço não pode ser finalizada");
    }

    setStatus(StatusOrdemServico.FINALIZADA);
    setDataFinalizacao(OffsetDateTime.now());
  }

}