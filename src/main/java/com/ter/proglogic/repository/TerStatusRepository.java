package com.ter.proglogic.repository;

import com.ter.proglogic.dto.TerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TerStatusRepository extends JpaRepository<TerStatus, Long> {

    /**
     * Custom query to find a TerStatus by its status name.
     *
     * @param status the name of the status to search for
     * @return an Optional containing the TerStatus if found, or empty otherwise
     */
    @Query("SELECT ts from TerStatus ts where ts.status_name = ?1")
    Optional<TerStatus> findTerStatusByStatusName(String status);
}
