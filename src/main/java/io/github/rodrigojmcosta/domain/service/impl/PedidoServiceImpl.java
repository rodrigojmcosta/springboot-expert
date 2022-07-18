package io.github.rodrigojmcosta.domain.service.impl;

import io.github.rodrigojmcosta.domain.repository.Pedidos;
import io.github.rodrigojmcosta.domain.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private Pedidos repository;

}
