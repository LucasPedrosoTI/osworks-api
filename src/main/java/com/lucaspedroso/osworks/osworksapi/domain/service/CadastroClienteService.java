package com.lucaspedroso.osworks.osworksapi.domain.service;

import com.lucaspedroso.osworks.osworksapi.domain.exception.NegocioException;
import com.lucaspedroso.osworks.osworksapi.domain.model.Cliente;
import com.lucaspedroso.osworks.osworksapi.domain.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroClienteService {

  @Autowired
  private ClienteRepository clienteRepository;

  public Cliente salvar(final Cliente cliente) {

    final Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());

    if (clienteExistente != null && !clienteExistente.equals(cliente)) {
      throw new NegocioException("Já existe um client cadastrado com esse e-mail.");
    }

    return clienteRepository.save(cliente);
  }

  public void excluir(final Long clienteId) {
    clienteRepository.deleteById(clienteId);
  }

}