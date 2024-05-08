package com.ter.proglogic.repository;

import com.ter.proglogic.dto.LifecyclePhase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class LifecyclePhaseRepositoryTest {

    LifecyclePhase lifecyclePhase1;

    @Autowired
    private LifecyclePhaseRepository lifecyclePhaseRepository;

    @BeforeEach
    void setUp() {
        lifecyclePhase1 = new LifecyclePhase("OBSOLETE");
    }

    @Test
    void getAllLifecyclesWhenValuesExistTest() {
        // Arrange
        lifecyclePhaseRepository.save(lifecyclePhase1);

        // Act
        List<LifecyclePhase> lifecyclePhases = lifecyclePhaseRepository.findAll();

        // Assert
        Assertions.assertThat(lifecyclePhases).isNotEmpty();
        Assertions.assertThat(lifecyclePhases).isNotNull();
        Assertions.assertThat(lifecyclePhases).size().isGreaterThan(0);
    }

    @Test
    void getAllLifecyclesWhenNoValuesTest() {
        // Arrange
        List<LifecyclePhase> lifecyclePhases;

        // Act
        lifecyclePhases = lifecyclePhaseRepository.findAll();

        // Assert
        Assertions.assertThat(lifecyclePhases).isEmpty();
        Assertions.assertThat(lifecyclePhases).isNotNull();
        Assertions.assertThat(lifecyclePhases).size().isEqualTo(0);
    }

    @Test
    void findLifecyclePhaseByLifecycleName() {
        // Arrange
        lifecyclePhaseRepository.save(lifecyclePhase1);

        // Act
        Optional<LifecyclePhase> existingLifecycle = lifecyclePhaseRepository.findLifecyclePhaseByLifecycleName("OBSOLETE");

        // Arrange
        Assertions.assertThat(existingLifecycle).isNotEmpty();
        Assertions.assertThat(existingLifecycle).isNotNull();
        Assertions.assertThat(existingLifecycle).isPresent();
        Assertions.assertThat(existingLifecycle.get().getLifecycleName()).isEqualTo(lifecyclePhase1.getLifecycleName());
        Assertions.assertThat(existingLifecycle.get().getLifecyclePhaseId()).isGreaterThan(0);
        Assertions.assertThat(existingLifecycle.get().getLifecycleName()).isEqualTo("OBSOLETE");
    }

    @Test
    void saveLifecycleTest() {
        //Arrange
        LifecyclePhase lifecycle = new LifecyclePhase("NO NEW USE");

        // Act
        LifecyclePhase savedLifecycle = lifecyclePhaseRepository.save(lifecycle);

        // Assert
        Assertions.assertThat(savedLifecycle).isNotNull();
        Assertions.assertThat(savedLifecycle.getLifecyclePhaseId()).isGreaterThan(0);
        Assertions.assertThat(savedLifecycle.getLifecycleName()).isEqualTo("NO NEW USE");
        Assertions.assertThat(savedLifecycle.getLifecycleName()).isNotBlank();
        Assertions.assertThat(savedLifecycle.getLifecycleName()).isNotEmpty();
    }
}