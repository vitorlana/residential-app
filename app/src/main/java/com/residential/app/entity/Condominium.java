package com.residential.app.entity;

import com.residential.app.entity.embeddablesId.CondominiumId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name= "condominium")
@Getter
@Setter
public class Condominium {

    @EmbeddedId
    private CondominiumId id;

    @OneToMany()
    private Set<Residence> residences;
    @Column(precision = 100,scale = 2)
    private BigDecimal expenses;
    @Column(precision = 100,scale = 2)
    private BigDecimal totalArea;


    public Condominium removeResidence(Residence residence) {
        this.residences.remove(residence);
        return this;
    }



}
