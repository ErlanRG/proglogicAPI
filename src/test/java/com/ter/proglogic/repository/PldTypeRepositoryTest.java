package com.ter.proglogic.repository;

import com.ter.proglogic.dto.PldType;
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
class PldTypeRepositoryTest {

    PldType pldType1;

    @Autowired
    private PldTypeRepository pldTypeRepository;

    @BeforeEach
    void setUp() {
        pldType1 = new PldType("FPGA");
    }


    @Test
    void getAllPldTypesWhenValuesExistTest() {
        // Arrange
        pldTypeRepository.save(pldType1);

        // Act
        List<PldType> pldTypes = pldTypeRepository.findAll();

        // Assert
        Assertions.assertThat(pldTypes).isNotEmpty();
        Assertions.assertThat(pldTypes).isNotNull();
        Assertions.assertThat(pldTypes).size().isGreaterThan(0);
    }

    @Test
    void getAllPldTypesWhenNoValuesTest() {
        // Arrange
        List<PldType> pldTypes;

        // Act
        pldTypes = pldTypeRepository.findAll();

        // Assert
        Assertions.assertThat(pldTypes).isEmpty();
        Assertions.assertThat(pldTypes).isNotNull();
        Assertions.assertThat(pldTypes).size().isEqualTo(0);
    }

    @Test
    void findPldTypeByPldTypeNameTest() {
        // Arrange
        pldTypeRepository.save(pldType1);

        // Act
        Optional<PldType> existingPldType = pldTypeRepository.findPldTypeByPldTypeName("FPGA");

        // Arrange
        Assertions.assertThat(existingPldType).isNotEmpty();
        Assertions.assertThat(existingPldType).isNotNull();
        Assertions.assertThat(existingPldType).isPresent();
        Assertions.assertThat(existingPldType.get().getPldTypeId()).isEqualTo(pldType1.getPldTypeId());
        Assertions.assertThat(existingPldType.get().getPldTypeId()).isGreaterThan(0);
        Assertions.assertThat(existingPldType.get().getPldTypeName()).isEqualTo("FPGA");
    }

    @Test
    void savePldTypeTest() {
        //Arrange
        PldType pldType = new PldType("FPGA");

        // Act
        PldType savedPldtype = pldTypeRepository.save(pldType);

        // Assert
        Assertions.assertThat(savedPldtype).isNotNull();
        Assertions.assertThat(savedPldtype.getPldTypeId()).isGreaterThan(0);
        Assertions.assertThat(savedPldtype.getPldTypeName()).isEqualTo("FPGA");
        Assertions.assertThat(savedPldtype.getPldTypeName()).isNotBlank();
        Assertions.assertThat(savedPldtype.getPldTypeName()).isNotEmpty();
    }
}