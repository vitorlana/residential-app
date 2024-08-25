package com.residential.app.repository;

import com.residential.app.entity.Residence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ResidenceRepository extends JpaRepository<Residence, UUID> {

    Residence findByResidentName(String nameResident);


}
