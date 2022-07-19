package io.github.rodrigojmcosta.domain.service.impl;

import io.github.rodrigojmcosta.domain.entity.Cliente;
import io.github.rodrigojmcosta.domain.entity.ItemPedido;
import io.github.rodrigojmcosta.domain.entity.Pedido;
import io.github.rodrigojmcosta.domain.entity.Produto;
import io.github.rodrigojmcosta.domain.enums.StatusPedido;
import io.github.rodrigojmcosta.domain.repository.Clientes;
import io.github.rodrigojmcosta.domain.repository.ItensPedido;
import io.github.rodrigojmcosta.domain.repository.Pedidos;
import io.github.rodrigojmcosta.domain.repository.Produtos;
import io.github.rodrigojmcosta.domain.service.PedidoService;
import io.github.rodrigojmcosta.exception.PedidoNaoEncontradoException;
import io.github.rodrigojmcosta.exception.RegraNegocioException;
import io.github.rodrigojmcosta.rest.dto.ItemPedidoDTO;
import io.github.rodrigojmcosta.rest.dto.PedidoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final Pedidos repository;
    private final Clientes clientesRepository;
    private final Produtos produtosRepository;
    private final ItensPedido itensPedidoRepository;

    @Override
    @Transactional //Garante que o método execute todas as operações com sucesso na base de dados e,
    //caso aconteça qualquer erro será feito um rollback no banco, garantindo a integridade do pedido.
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository
                .findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido: " + idCliente));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItemPedido> itensPedido = converterItens(pedido, dto.getItens());
        repository.save(pedido);
        itensPedidoRepository.saveAll(itensPedido);
        pedido.setItens(itensPedido);
        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return repository.findByIdFetchItens(id);
    }

    @Override
    @Transactional
    public void atualizaStatus(Integer id, StatusPedido statusPedido) {
        repository
                .findById(id)
                .map(pedido -> {
                    pedido.setStatus(statusPedido);
                    return repository.save(pedido);
                })
                .orElseThrow(() -> new PedidoNaoEncontradoException());
    }

    private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> itens) {
        if (itens.isEmpty()) {
            throw new RegraNegocioException("Não é possível realizar um pedido sem itens.");
        }

        return itens
                .stream()
                .map(dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository.findById(idProduto)
                            .orElseThrow(
                                    () -> new RegraNegocioException(
                                            "Código de produto inválido: " + idProduto
                                    ));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }

}
