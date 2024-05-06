package com.ter.proglogic.repository;

import com.ter.proglogic.dto.LifecyclePhase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LifecyclePhaseRepository extends JpaRepository<LifecyclePhase, Long> {
    @Query("SELECT l FROM LifecyclePhase l WHERE l.lifecycleName = ?1")
    Optional<LifecyclePhase> findLifecyclePhaseByLifecycleName(String lifecycleName);
}
