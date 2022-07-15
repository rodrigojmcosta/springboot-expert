package io.github.rodrigojmcosta.domain.repository;

import io.github.rodrigojmcosta.domain.entity.Cliente;
import io.github.rodrigojmcosta.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Pedidos extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByCliente(Cliente cliente);
}
