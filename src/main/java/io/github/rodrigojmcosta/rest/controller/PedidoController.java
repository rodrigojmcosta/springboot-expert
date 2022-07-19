package io.github.rodrigojmcosta.rest.controller;

import io.github.rodrigojmcosta.domain.entity.ItemPedido;
import io.github.rodrigojmcosta.domain.entity.Pedido;
import io.github.rodrigojmcosta.domain.enums.StatusPedido;
import io.github.rodrigojmcosta.domain.service.PedidoService;
import io.github.rodrigojmcosta.rest.dto.AtualizacaoStatusPedidoDTO;
import io.github.rodrigojmcosta.rest.dto.InformacaoItemPedidoDTO;
import io.github.rodrigojmcosta.rest.dto.InformacoesPedidoDTO;
import io.github.rodrigojmcosta.rest.dto.PedidoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody @Valid PedidoDTO dto) {
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }

    @GetMapping("{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id) {
        return service.obterPedidoCompleto(id)
                .map(p -> converter(p))
                .orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND, "Pedido não encontrado."));
    }

    @PatchMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateStatus(@PathVariable Integer id,
                             @RequestBody AtualizacaoStatusPedidoDTO dto) {
        String novoStatus = dto.getNovoStatus();
        service.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
    }

    private InformacoesPedidoDTO converter(Pedido pedido) {
        return InformacoesPedidoDTO.builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .totalPedido(pedido.getTotal())
                .status(pedido.getStatus().name())
                .itens(converter(pedido.getItens()))
                .build();
    }

    private List<InformacaoItemPedidoDTO> converter(List<ItemPedido> itensPedido) {
        if (CollectionUtils.isEmpty(itensPedido)) {
            return Collections.emptyList();
        }
        return itensPedido.stream().map(
                itemPedido -> InformacaoItemPedidoDTO
                        .builder().descricaoProduto(itemPedido.getProduto().getDescricao())
                        .precoUnitatio(itemPedido.getProduto().getPreco())
                        .quantidade(itemPedido.getQuantidade())
                        .build()
        ).collect(Collectors.toList());
    }

}
