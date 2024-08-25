package com.residential.app.repository;

import com.residential.app.entity.Condominium;
import com.residential.app.entity.Residence;
import com.residential.app.entity.embeddablesId.CondominiumId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface CondominiumRepository extends JpaRepository<Condominium, CondominiumId> {

    Optional<Condominium> findByIdName(String idName);

    Optional<Condominium> findByResidencesId(UUID residenceId);

    Condominium findAllResidenceByIdName(String idName);

}

