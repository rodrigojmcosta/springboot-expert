package io.github.rodrigojmcosta.rest.dto;

import io.github.rodrigojmcosta.validation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoDTO {

    @NotNull(message = "Informe o código do cliente.")
    private Integer cliente;

    @NotNull(message = "O campo 'total do pedido' é obrigatório")
    private BigDecimal total;

    @NotEmptyList(message = "Não é possível realizar um pedido sem itens.")
    private List<ItemPedidoDTO> itens;

}

