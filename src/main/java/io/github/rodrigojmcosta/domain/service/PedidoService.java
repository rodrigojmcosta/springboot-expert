package io.github.rodrigojmcosta.domain.service;

import io.github.rodrigojmcosta.domain.entity.Pedido;
import io.github.rodrigojmcosta.rest.dto.PedidoDTO;

public interface PedidoService {

    Pedido salvar (PedidoDTO dto);

}
