package io.github.rodrigojmcosta.domain.service;

import io.github.rodrigojmcosta.domain.entity.Pedido;
import io.github.rodrigojmcosta.domain.enums.StatusPedido;
import io.github.rodrigojmcosta.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);
    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizaStatus(Integer id, StatusPedido statusPedido);

}
