package com.ter.proglogic.repository;

import com.ter.proglogic.dto.PldType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PldTypeRepository extends JpaRepository<PldType, Long> {

    /**
     * Custom query to find a PldType by its PLD type name.
     *
     * @param pldTypeName the name of the PLD type to search for
     * @return an Optional containing the PldType if found, or empty otherwise
     */
    @Query("SELECT p FROM PldType p WHERE p.pldTypeName = ?1")
    Optional<PldType> findPldTypeByPldTypeName(String pldTypeName);
}
