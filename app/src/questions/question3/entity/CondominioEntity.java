package org.question3.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;
import java.util.UUID;


@Entity
@Table(name= "CONDOMINIO")
public class CondominioEntity {

    @Id
    private UUID idCondominio;
    @OneToMany(mappedBy = "unidade")
    private Set<UnidadeEntity> unidades;
}
