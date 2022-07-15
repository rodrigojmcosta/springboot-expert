package io.github.rodrigojmcosta.domain.repository;

import io.github.rodrigojmcosta.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Pedidos extends JpaRepository<Pedido, Integer> {
}
