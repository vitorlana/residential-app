package org.question4.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "UNIDADE")
public class UnidadeEntity {

    @Id
    private Long numeroUnidade;
    @OneToOne(mappedBy = "morador")
    private MoradorEntity morador;

}
