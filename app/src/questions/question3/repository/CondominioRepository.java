package org.question3.repository;

import org.question3.entity.CondominioEntity;
import org.question3.entity.MoradorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CondominioRepository extends JpaRepository<CondominioEntity, UUID> {
}
