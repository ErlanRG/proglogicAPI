package com.ter.proglogic.service;

import com.ter.proglogic.dto.PldType;
import com.ter.proglogic.exceptions.DuplicateValueException;
import com.ter.proglogic.exceptions.MissingArgumentException;
import com.ter.proglogic.repository.PldTypeRepository;
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
class PldTypeServiceTest {

    PldType pldType1;
    PldType pldType2;

    @Mock
    private PldTypeRepository pldTypeRepository;

    @InjectMocks
    private PldTypeService pldTypeService;

    @BeforeEach
    void setUp() {
        pldType1 = new PldType("FPGA");
        pldType2 = new PldType("CPLD");
    }

    @Test
    void getAllPldTypesTest() {
        // Arrange
        List<PldType> pldTypeList = List.of(pldType1, pldType2);
        when(pldTypeRepository.findAll()).thenReturn(pldTypeList);

        // Act
        List<PldType> expectedPldtypeList = pldTypeService.getAllPldTypes();

        // Assert
        Assertions.assertThat(expectedPldtypeList).containsExactlyElementsOf(pldTypeList);
        Assertions.assertThat(expectedPldtypeList).hasSize(2);
        Assertions.assertThat(expectedPldtypeList).isEqualTo(pldTypeList);
        Assertions.assertThat(expectedPldtypeList).isNotEmpty();
    }

    @Test
    void getPldTypesWhenNoValuesTest() {
        // Arrange
        List<PldType> pldTypeList = new ArrayList<>();
        when(pldTypeRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<PldType> expectedPldtypesList = pldTypeService.getAllPldTypes();

        // Assert
        Assertions.assertThat(expectedPldtypesList).isEmpty();
        Assertions.assertThat(expectedPldtypesList).isEqualTo(pldTypeList);
    }

    @Test
    void addNewPldTypeTest() {
        // Arrange
        String pldTypeName = pldType1.getPldTypeName();
        when(pldTypeRepository.findPldTypeByPldTypeName(pldTypeName)).thenReturn(Optional.empty());

        ArgumentCaptor<PldType> savedPldType = ArgumentCaptor.forClass(PldType.class);
        when(pldTypeRepository.save(savedPldType.capture())).thenReturn(pldType1);

        // Act
        pldTypeService.addNewPldType(pldType1);

        // Arrange
        Assertions.assertThat(pldTypeName).isNotBlank();
        Assertions.assertThat(pldTypeName).isNotEmpty();
        Assertions.assertThat(pldTypeName).isNotNull();
        Assertions.assertThat(savedPldType.getValue()).isEqualTo(pldType1);
        Assertions.assertThat(savedPldType.getValue()).isNotNull();
        Assertions.assertThat(savedPldType.getValue().getPldTypeName()).isEqualTo(pldType1.getPldTypeName());
    }

    @Test
    void addNewPldType_MissingArgException_NullName() {
        // Arrange
        PldType pldType = new PldType(null);

        // Act
        assertThrows(MissingArgumentException.class, () -> pldTypeService.addNewPldType(pldType));

        // Assert
        verify(pldTypeRepository, never()).save(any());
    }

    @Test
    void addNewPldType_MissingArgException_EmptyName() {
        // Arrange
        PldType pldType1 = new PldType("");

        // Act
        assertThrows(MissingArgumentException.class, () -> pldTypeService.addNewPldType(pldType1));

        // Assert
        verify(pldTypeRepository, never()).save(any());
    }

    @Test
    void addNewPldType_DuplicateValueExceptionTest() {
        // Arrange
        String existingPldTypeName = pldType1.getPldTypeName();
        when(pldTypeRepository.findPldTypeByPldTypeName(existingPldTypeName)).thenReturn(Optional.of(pldType1));

        PldType newPldType = new PldType(existingPldTypeName);

        // Act
        assertThrows(DuplicateValueException.class, () -> pldTypeService.addNewPldType(newPldType));

        // Assert
        verify(pldTypeRepository, never()).save(any());
    }
}