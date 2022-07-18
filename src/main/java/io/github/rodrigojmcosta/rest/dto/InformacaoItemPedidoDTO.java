package io.github.rodrigojmcosta.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacaoItemPedidoDTO {

    private String descricaoProduto;
    private BigDecimal precoUnitatio;
    private Integer quantidade;

}
