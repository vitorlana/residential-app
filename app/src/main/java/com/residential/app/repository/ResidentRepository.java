package com.residential.app.repository;

import com.residential.app.entity.Residence;
import com.residential.app.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidentRepository extends JpaRepository<Resident,String> {
}
