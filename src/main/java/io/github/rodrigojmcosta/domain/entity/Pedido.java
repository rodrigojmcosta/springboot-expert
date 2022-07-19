package io.github.rodrigojmcosta.domain.entity;

import io.github.rodrigojmcosta.domain.enums.StatusPedido;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "PEDIDO")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "CLIENTE_ID")
    private Cliente cliente;

    @Column(name = "DATA_PEDIDO")
    private LocalDate dataPedido;

    @Column(name = "TOTAL", scale = 2, precision = 20)
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private StatusPedido status;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;

}
