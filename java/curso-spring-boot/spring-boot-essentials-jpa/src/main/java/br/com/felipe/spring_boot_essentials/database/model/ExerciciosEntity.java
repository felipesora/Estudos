package br.com.felipe.spring_boot_essentials.database.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "avaliacoes_fisicas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AvaliacoesFisicasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private BigDecimal peso;

    @Column(nullable = false)
    private BigDecimal altura;

    @Column(name = "porcentagem_gordura_corporal", nullable = false)
    private BigDecimal porcentagemGorduraCorporal;
}
