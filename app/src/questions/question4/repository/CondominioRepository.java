package org.question4.repository;

import org.question4.entity.CondominioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CondominioRepository extends JpaRepository<CondominioEntity, UUID> {
}
