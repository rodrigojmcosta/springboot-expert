package io.github.rodrigojmcosta.domain.repository;

import io.github.rodrigojmcosta.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItensPedido extends JpaRepository<ItemPedido, Integer> {
}
