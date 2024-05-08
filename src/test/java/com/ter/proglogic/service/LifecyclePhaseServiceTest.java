package com.ter.proglogic.service;

import com.ter.proglogic.dto.LifecyclePhase;
import com.ter.proglogic.exceptions.DuplicateValueException;
import com.ter.proglogic.exceptions.MissingArgumentException;
import com.ter.proglogic.repository.LifecyclePhaseRepository;
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
class LifecyclePhaseServiceTest {

    LifecyclePhase lifecyclePhase1;
    LifecyclePhase lifecyclePhase2;

    @Mock
    LifecyclePhaseRepository lifecyclePhaseRepository;

    @InjectMocks
    LifecyclePhaseService lifecyclePhaseService;

    @BeforeEach
    void setUp() {
        lifecyclePhase1 = new LifecyclePhase("NEW");
        lifecyclePhase2 = new LifecyclePhase("MAINSTREAM");
    }

    @Test
    void getAllLifecyclePhasesTest() {
        // Arrange
        List<LifecyclePhase> lifecycleList = List.of(lifecyclePhase1, lifecyclePhase2);
        when(lifecyclePhaseRepository.findAll()).thenReturn(lifecycleList);

        // Act
        List<LifecyclePhase> expectedLifecycleList = lifecyclePhaseService.getAllLifecyclePhases();

        // Assert
        Assertions.assertThat(expectedLifecycleList).containsExactlyElementsOf(lifecycleList);
        Assertions.assertThat(expectedLifecycleList).hasSize(2);
        Assertions.assertThat(expectedLifecycleList).isEqualTo(lifecycleList);
        Assertions.assertThat(expectedLifecycleList).isNotEmpty();
    }

    @Test
    void getLifecyclesWhenNoValuesTest() {
        // Arrange
        List<LifecyclePhase> lifecyclePhaseList = new ArrayList<>();
        when(lifecyclePhaseRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<LifecyclePhase> expectedLifecycleList = lifecyclePhaseService.getAllLifecyclePhases();

        // Assert
        Assertions.assertThat(expectedLifecycleList).isEmpty();
        Assertions.assertThat(expectedLifecycleList).isEqualTo(lifecyclePhaseList);
    }

    @Test
    void addNewLifecyclePhaseTest() {
        // Arrange
        String lifecycleName = lifecyclePhase1.getLifecycleName();
        when(lifecyclePhaseRepository.findLifecyclePhaseByLifecycleName(lifecycleName)).thenReturn(Optional.empty());

        ArgumentCaptor<LifecyclePhase> savedLifecycle = ArgumentCaptor.forClass(LifecyclePhase.class);
        when(lifecyclePhaseRepository.save(savedLifecycle.capture())).thenReturn(lifecyclePhase1);

        // Act
        lifecyclePhaseService.addNewLifecyclePhase(lifecyclePhase1);

        // Arrange
        Assertions.assertThat(lifecycleName).isNotBlank();
        Assertions.assertThat(lifecycleName).isNotEmpty();
        Assertions.assertThat(lifecycleName).isNotNull();
        Assertions.assertThat(savedLifecycle.getValue()).isEqualTo(lifecyclePhase1);
        Assertions.assertThat(savedLifecycle.getValue()).isNotNull();
        Assertions.assertThat(savedLifecycle.getValue().getLifecycleName()).isEqualTo(lifecyclePhase1.getLifecycleName());
    }

    @Test
    void addNewLifecycle_MissingArgException_NullName() {
        // Arrange
        LifecyclePhase lifecycle = new LifecyclePhase(null);

        // Act
        assertThrows(MissingArgumentException.class, () -> lifecyclePhaseService.addNewLifecyclePhase(lifecycle));

        // Assert
        verify(lifecyclePhaseRepository, never()).save(any());
    }

    @Test
    void addNewLifecycle_MissingArgException_EmptyName() {
        // Arrange
        LifecyclePhase lifecyclePhase = new LifecyclePhase("");

        // Act
        assertThrows(MissingArgumentException.class, () -> lifecyclePhaseService.addNewLifecyclePhase(lifecyclePhase));

        // Assert
        verify(lifecyclePhaseRepository, never()).save(any());
    }

    @Test
    void addNewLifecycle_DuplicateValueExceptionTest() {
        // Arrange
        String existingLifecycleName = lifecyclePhase1.getLifecycleName();
        when(lifecyclePhaseRepository.findLifecyclePhaseByLifecycleName(existingLifecycleName)).thenReturn(Optional.of(lifecyclePhase1));

        LifecyclePhase newLifecycle = new LifecyclePhase(existingLifecycleName);

        // Act
        assertThrows(DuplicateValueException.class, () -> lifecyclePhaseService.addNewLifecyclePhase(newLifecycle));

        // Assert
        verify(lifecyclePhaseRepository, never()).save(any());
    }
}