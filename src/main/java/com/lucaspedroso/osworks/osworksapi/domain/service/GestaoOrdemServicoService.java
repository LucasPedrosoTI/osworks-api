package com.lucaspedroso.osworks.osworksapi.domain.service;

import java.time.OffsetDateTime;

import com.lucaspedroso.osworks.osworksapi.domain.exception.EntidadeNaoEncontradaException;
import com.lucaspedroso.osworks.osworksapi.domain.exception.NegocioException;
import com.lucaspedroso.osworks.osworksapi.domain.model.Cliente;
import com.lucaspedroso.osworks.osworksapi.domain.model.Comentario;
import com.lucaspedroso.osworks.osworksapi.domain.model.OrdemServico;
import com.lucaspedroso.osworks.osworksapi.domain.model.StatusOrdemServico;
import com.lucaspedroso.osworks.osworksapi.domain.repository.ClienteRepository;
import com.lucaspedroso.osworks.osworksapi.domain.repository.ComentarioRepository;
import com.lucaspedroso.osworks.osworksapi.domain.repository.OrdemServicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GestaoOrdemServicoService {

  @Autowired
  private OrdemServicoRepository ordemServicoRepository;

  @Autowired
  private ClienteRepository clienteRepository;

  @Autowired
  private ComentarioRepository comentarioRepository;

  public OrdemServico criar(OrdemServico ordemServico) {
    Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
        .orElseThrow(() -> new NegocioException("Cliente não encontrado"));

    ordemServico.setCliente(cliente);
    ordemServico.setStatus(StatusOrdemServico.ABERTA);
    ordemServico.setDataAbertura(OffsetDateTime.now());

    return ordemServicoRepository.save(ordemServico);
  }

  public void finalizar(Long ordemServicoId) {
    OrdemServico ordemServico = buscar(ordemServicoId);

    ordemServico.finalizar();

    ordemServicoRepository.save(ordemServico);
  }

  public Comentario adicionarComentario(Long ordemServicoId, String descricao) {
    OrdemServico ordemServico = buscar(ordemServicoId);

    Comentario comentario = new Comentario();
    comentario.setDataEnvio(OffsetDateTime.now());
    comentario.setDescricao(descricao);
    comentario.setOrdemServico(ordemServico);

    return comentarioRepository.save(comentario);

  }

  private OrdemServico buscar(Long ordemServicoId) {
    return ordemServicoRepository.findById(ordemServicoId)
        .orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de serviço não encontrada"));
  }
}