package com.lucaspedroso.osworks.osworksapi.domain.repository;

import java.util.List;

import com.lucaspedroso.osworks.osworksapi.domain.model.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

  // caso precise criar métodos próprios
  List<Cliente> findByNome(String nome);

  List<Cliente> findByNomeContaining(String nome);

  Cliente findByEmail(String email);

}