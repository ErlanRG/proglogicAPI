package com.ter.proglogic.repository;

import com.ter.proglogic.dto.PldType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PldTypeRepository extends JpaRepository<PldType, Long> {
    @Query("SELECT p FROM PldType p WHERE p.pldTypeName = ?1")
    Optional<PldType> findPldTypeByPldTypeName(String pldTypeName);
}
