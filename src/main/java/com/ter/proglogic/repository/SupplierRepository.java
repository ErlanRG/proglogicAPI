package com.ter.proglogic.repository;

import com.ter.proglogic.dto.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    /**
     * Custom query to find a Supplier by its supplier name.
     *
     * @param name the name of the supplier to search for
     * @return an Optional containing the Supplier if found, or empty otherwise
     */
    @Query("SELECT s FROM Supplier s WHERE s.supplierName = ?1")
    Optional<Supplier> findSupplierBySupplierName(String name);
}
