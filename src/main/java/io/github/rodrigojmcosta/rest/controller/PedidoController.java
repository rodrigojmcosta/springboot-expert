package io.github.rodrigojmcosta.rest.controller;

import io.github.rodrigojmcosta.domain.entity.Pedido;
import io.github.rodrigojmcosta.domain.service.PedidoService;
import io.github.rodrigojmcosta.rest.dto.PedidoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody PedidoDTO dto) {
        Pedido pedido  = service.salvar(dto);
        return pedido.getId();
    }

}
