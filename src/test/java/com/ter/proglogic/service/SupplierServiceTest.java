package com.ter.proglogic.service;

import com.ter.proglogic.dto.Supplier;
import com.ter.proglogic.exceptions.DuplicateValueException;
import com.ter.proglogic.exceptions.MissingArgumentException;
import com.ter.proglogic.repository.SupplierRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SupplierServiceTest {

    Supplier testSupplier1;
    Supplier testSupplier2;

    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private SupplierService supplierService;

    @BeforeEach
    void setUp() {
        testSupplier1 = new Supplier("AMD");
        testSupplier2 = new Supplier("Intel");
    }

    @Test
    void getSuppliersTest() {
        // Arrange
        List<Supplier> supplierList = List.of(testSupplier1, testSupplier2);
        when(supplierRepository.findAll()).thenReturn(supplierList);

        // Act
        List<Supplier> expectedSupplierList = supplierService.getSuppliers();

        // Assert
        Assertions.assertThat(expectedSupplierList).isEqualTo(supplierList);
        Assertions.assertThat(expectedSupplierList).isNotEmpty();
        Assertions.assertThat(expectedSupplierList).hasSize(2);
        Assertions.assertThat(expectedSupplierList).containsExactlyElementsOf(supplierList);
    }

    @Test
    void getSuppliersWhenNoValuesTest() {
        // Arrange
        List<Supplier> supplierList = new ArrayList<>();
        when(supplierRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<Supplier> expectedSupplierList = supplierService.getSuppliers();

        // Assert
        Assertions.assertThat(expectedSupplierList).isEmpty();
        Assertions.assertThat(expectedSupplierList).isEqualTo(supplierList);
    }

    @Test
    void addNewSupplierTest() {
        // Arrange
        String supplierName = testSupplier1.getSupplierName();
        when(supplierRepository.findSupplierBySupplierName(supplierName)).thenReturn(Optional.empty());

        ArgumentCaptor<Supplier> savedSupplier = ArgumentCaptor.forClass(Supplier.class);
        when(supplierRepository.save(savedSupplier.capture())).thenReturn(testSupplier1);

        // Act
        supplierService.addNewSupplier(testSupplier1);

        // Arrange
        Assertions.assertThat(savedSupplier.getValue()).isEqualTo(testSupplier1);
        Assertions.assertThat(savedSupplier.getValue()).isNotNull();
        Assertions.assertThat(savedSupplier.getValue().getSupplierName()).isEqualTo(testSupplier1.getSupplierName());
        Assertions.assertThat(supplierName).isNotBlank();
        Assertions.assertThat(supplierName).isNotEmpty();
        Assertions.assertThat(supplierName).isNotNull();
    }

    @Test
    void addNewSupplier_MissingArgException_NullName() {
        // Arrange
        Supplier supplier1 = new Supplier(null);

        // Act
        assertThrows(MissingArgumentException.class, () -> supplierService.addNewSupplier(supplier1));

        // Assert
        verify(supplierRepository, never()).save(any());
    }

    @Test
    void addNewSupplier_MissingArgException_EmptyName() {
        // Arrange
        Supplier supplier1 = new Supplier("");

        // Act
        assertThrows(MissingArgumentException.class, () -> supplierService.addNewSupplier(supplier1));

        // Assert
        verify(supplierRepository, never()).save(any());
    }

    @Test
    void addNewSupplier_DuplicateValueExceptionTest() {
        // Arrange
        String existingSupplierName = testSupplier1.getSupplierName();
        when(supplierRepository.findSupplierBySupplierName(existingSupplierName)).thenReturn(Optional.of(testSupplier1));

        Supplier newSupplier = new Supplier(existingSupplierName);

        // Act
        assertThrows(DuplicateValueException.class, () -> supplierService.addNewSupplier(newSupplier));

        // Assert
        verify(supplierRepository, never()).save(any());
    }
}