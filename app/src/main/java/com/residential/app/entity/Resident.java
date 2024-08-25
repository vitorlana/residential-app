package com.residential.app.entity;


import com.residential.app.enums.ResidentType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name= "resident")
@Getter
@Setter
public class Resident {

    @OneToOne(cascade = CascadeType.ALL)
    private Residence residence;
    @Id
    private String name;
    private String surname;
    private ResidentType type;
}
