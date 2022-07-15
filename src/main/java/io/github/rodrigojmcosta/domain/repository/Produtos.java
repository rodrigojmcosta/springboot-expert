package io.github.rodrigojmcosta.domain.repository;

import io.github.rodrigojmcosta.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto, Integer> {
}
