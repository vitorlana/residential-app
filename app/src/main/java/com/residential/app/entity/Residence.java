package com.residential.app.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.residential.app.entity.embeddablesId.CondominiumId;
import com.residential.app.enums.ResidenceType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;


@Entity
@Table(name = "residence")
@Getter
@Setter
public class Residence {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Long number;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private Resident resident;
    private ResidenceType type;
    @Column(precision = 100,scale = 2)
    private BigDecimal area;
    @Column(precision = 100,scale = 10)
    private BigDecimal idealFraction;
    @Column(precision = 100,scale = 2)
    private BigDecimal condominiumValue;


}
