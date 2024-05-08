package com.ter.proglogic.repository;

import com.ter.proglogic.dto.TerStatus;
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
class TerStatusRepositoryTest {

    TerStatus status1;

    @Autowired
    private TerStatusRepository terStatusRepository;

    @BeforeEach
    void setUp() {
        status1 = new TerStatus("NO NEW USE");
    }

    @Test
    void getAllStatusesWhenValuesExistTest() {
        // Arrange
        terStatusRepository.save(status1);
        List<TerStatus> statuses;

        // Act
        statuses = terStatusRepository.findAll();

        // Arrange
        Assertions.assertThat(statuses).isNotEmpty();
        Assertions.assertThat(statuses).isNotNull();
        Assertions.assertThat(statuses).size().isGreaterThan(0);
    }

    @Test
    void getAllStatusesWhenNoValuesTest() {
        // Arrange
        List<TerStatus> statuses;

        // Act
        statuses = terStatusRepository.findAll();

        // Assert
        Assertions.assertThat(statuses).isEmpty();
        Assertions.assertThat(statuses).isNotNull();
        Assertions.assertThat(statuses).hasSize(0);
    }

    @Test
    void findTerStatusByStatusNameTest() {
        // Arrange
        TerStatus newStatus = new TerStatus("NO NEW USE");
        terStatusRepository.save(status1);

        // Act
        Optional<TerStatus> existingStatus = terStatusRepository.findTerStatusByStatusName(newStatus.getStatus_name());

        // Assert
        Assertions.assertThat(existingStatus).isNotEmpty();
        Assertions.assertThat(existingStatus).isNotNull();
        Assertions.assertThat(existingStatus).isPresent();
        Assertions.assertThat(existingStatus.get().getStatus_id()).isEqualTo(existingStatus.get().getStatus_id());
        Assertions.assertThat(existingStatus.get().getStatus_id()).isGreaterThan(0);
        Assertions.assertThat(existingStatus.get().getStatus_name()).isEqualTo(status1.getStatus_name());
    }

    @Test
    void saveTerStatusTest() {
        // Arrange
        TerStatus newStatus = new TerStatus("NO NEW USE");

        // Act
        TerStatus status = terStatusRepository.save(newStatus);

        // Arrange
        Assertions.assertThat(status).isNotNull();
        Assertions.assertThat(status.getStatus_id()).isGreaterThan(0);
        Assertions.assertThat(status.getStatus_name()).isEqualTo("NO NEW USE");
        Assertions.assertThat(status.getStatus_name()).isNotBlank();
        Assertions.assertThat(status.getStatus_name()).isNotEmpty();
    }
}