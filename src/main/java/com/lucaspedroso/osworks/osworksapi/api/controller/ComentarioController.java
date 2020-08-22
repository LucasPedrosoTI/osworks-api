package com.lucaspedroso.osworks.osworksapi.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.lucaspedroso.osworks.osworksapi.api.model.ComentarioInput;
import com.lucaspedroso.osworks.osworksapi.api.model.ComentarioRepModel;
import com.lucaspedroso.osworks.osworksapi.domain.exception.EntidadeNaoEncontradaException;
import com.lucaspedroso.osworks.osworksapi.domain.exception.NegocioException;
import com.lucaspedroso.osworks.osworksapi.domain.model.Comentario;
import com.lucaspedroso.osworks.osworksapi.domain.model.OrdemServico;
import com.lucaspedroso.osworks.osworksapi.domain.repository.OrdemServicoRepository;
import com.lucaspedroso.osworks.osworksapi.domain.service.GestaoOrdemServicoService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ordens-servico/{ordemServicoId}/comentarios")
public class ComentarioController {

  @Autowired
  private GestaoOrdemServicoService gestaoOrdemServico;

  @Autowired
  private OrdemServicoRepository ordemServicoRepository;

  @Autowired
  private ModelMapper modelMapper;

  @GetMapping
  public List<ComentarioRepModel> listar(@PathVariable Long ordemServicoId) {
    OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
        .orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de serviço não encontrada"));

    List<ComentarioRepModel> comentarios = toCollectionModel(ordemServico.getComentarios());

    if (comentarios.isEmpty()) {
      throw new NegocioException("Ainda não há comentários para essa OS");
    }

    return comentarios;

  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ComentarioRepModel adicionar(@PathVariable Long ordemServicoId,
      @Valid @RequestBody ComentarioInput comentarioInput) {
    Comentario comentario = gestaoOrdemServico.adicionarComentario(ordemServicoId, comentarioInput.getDescricao());

    return toModel(comentario);
  }

  private ComentarioRepModel toModel(Comentario comentario) {
    return modelMapper.map(comentario, ComentarioRepModel.class);
  }

  private List<ComentarioRepModel> toCollectionModel(List<Comentario> comentarios) {
    return comentarios.stream().map(comentario -> toModel(comentario)).collect(Collectors.toList());
  }
}