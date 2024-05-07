package com.ter.proglogic.repository;


import com.ter.proglogic.dto.Supplier;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class SupplierRepositoryTest {

    @Autowired
    private SupplierRepository supplierRepository;

    @Test
    void getAllSuppliersWhenValuesExistTest() {
        // Arrange
        Supplier amd = new Supplier("AMD");
        Supplier intel = new Supplier("INTEL");
        supplierRepository.save(amd);
        supplierRepository.save(intel);

        List<Supplier> suppliers;

        // Act
        suppliers = supplierRepository.findAll();

        // Assert
        Assertions.assertThat(suppliers).isNotEmpty();
        Assertions.assertThat(suppliers).isNotNull();
        Assertions.assertThat(suppliers).size().isGreaterThan(0);
    }

    @Test
    void getAllSuppliersWhenNoValuesTest() {
        // Arrange
        List<Supplier> suppliers;

        // Act
        suppliers = supplierRepository.findAll();

        // Assert
        Assertions.assertThat(suppliers).isEmpty();
        Assertions.assertThat(suppliers).isNotNull();
        Assertions.assertThat(suppliers).size().isEqualTo(0);
    }

    @Test
    void findSupplierBySupplierNameTest() {
        // Arrange
        Supplier amd = new Supplier("AMD");
        supplierRepository.save(amd);

        // Act
        Optional<Supplier> existingSupplier = supplierRepository.findSupplierBySupplierName("AMD");

        // Assert
        Assertions.assertThat(existingSupplier).isNotEmpty();
        Assertions.assertThat(existingSupplier).isNotNull();
        Assertions.assertThat(existingSupplier).isPresent();
        Assertions.assertThat(existingSupplier.get().getSupplierId()).isEqualTo(amd.getSupplierId());
        Assertions.assertThat(existingSupplier.get().getSupplierId()).isGreaterThan(0);
        Assertions.assertThat(existingSupplier.get().getSupplierName()).isEqualTo("AMD");
    }

    @Test
    void saveSupplierTest() {
        //Arrange
        Supplier supplier = new Supplier("Texas Instrument");

        // Act
        Supplier savedSupplier = supplierRepository.save(supplier);

        // Assert
        Assertions.assertThat(savedSupplier).isNotNull();
        Assertions.assertThat(savedSupplier.getSupplierId()).isGreaterThan(0);
        Assertions.assertThat(savedSupplier.getSupplierName()).isEqualTo("Texas Instrument");
        Assertions.assertThat(savedSupplier.getSupplierName()).isNotBlank();
        Assertions.assertThat(savedSupplier.getSupplierName()).isNotEmpty();
    }
}