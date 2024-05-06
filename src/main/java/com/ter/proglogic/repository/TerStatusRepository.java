package com.ter.proglogic.repository;

import com.ter.proglogic.dto.TerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TerStatusRepository extends JpaRepository<TerStatus, Long> {
    @Query("SELECT ts from TerStatus ts where ts.status_name = ?1")
    Optional<TerStatus> findTerStatusByStatusName(String status);
}