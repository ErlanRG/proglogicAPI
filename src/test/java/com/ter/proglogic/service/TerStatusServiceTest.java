package com.ter.proglogic.service;

import com.ter.proglogic.dto.TerStatus;
import com.ter.proglogic.exceptions.DuplicateValueException;
import com.ter.proglogic.exceptions.MissingArgumentException;
import com.ter.proglogic.repository.TerStatusRepository;
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
class TerStatusServiceTest {

    TerStatus obsoleteStatus;
    TerStatus matureStatus;

    @Mock
    private TerStatusRepository terStatusRepository;

    @InjectMocks
    private TerStatusService terStatusService;

    @BeforeEach
    void setUp() {
        obsoleteStatus = new TerStatus("OBSOLETE");
        matureStatus = new TerStatus("MATURE");
    }

    @Test
    void getAllStatusesTest() {
        // Arrange
        List<TerStatus> statusesList = List.of(obsoleteStatus, matureStatus);
        when(terStatusRepository.findAll()).thenReturn(statusesList);

        // Act
        List<TerStatus> expectedStatuses = terStatusService.getAllStatuses();

        // Assert
        Assertions.assertThat(expectedStatuses).isEqualTo(statusesList);
        Assertions.assertThat(expectedStatuses).isNotEmpty();
        Assertions.assertThat(expectedStatuses).hasSize(2);
        Assertions.assertThat(expectedStatuses).containsExactlyElementsOf(statusesList);
    }

    @Test
    void getStatusesWhenNoValuesTest() {
        // Arrange
        List<TerStatus> statusesList = new ArrayList<>();
        when(terStatusRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<TerStatus> expectedStatusList = terStatusService.getAllStatuses();

        // Assert
        Assertions.assertThat(expectedStatusList).isEmpty();
        Assertions.assertThat(expectedStatusList).isEqualTo(statusesList);
    }

    @Test
    void addNewStatusTest() {
        // Arrange
        String statusName = obsoleteStatus.getStatus_name();
        when(terStatusRepository.findTerStatusByStatusName(statusName)).thenReturn(Optional.empty());

        ArgumentCaptor<TerStatus> savedStatus = ArgumentCaptor.forClass(TerStatus.class);
        when(terStatusRepository.save(savedStatus.capture())).thenReturn(obsoleteStatus);

        // Act
        terStatusService.addNewStatus(obsoleteStatus);

        // Arrange
        Assertions.assertThat(savedStatus.getValue()).isEqualTo(obsoleteStatus);
        Assertions.assertThat(savedStatus.getValue()).isNotNull();
        Assertions.assertThat(savedStatus.getValue().getStatus_name()).isEqualTo(obsoleteStatus.getStatus_name());
        Assertions.assertThat(statusName).isNotBlank();
        Assertions.assertThat(statusName).isNotEmpty();
        Assertions.assertThat(statusName).isNotNull();
    }

    @Test
    void addNewStatus_MissingArgException_NullName() {
        // Arrange
        TerStatus status = new TerStatus(null);

        // Act
        assertThrows(MissingArgumentException.class, () -> terStatusService.addNewStatus(status));

        // Assert
        verify(terStatusRepository, never()).save(any());
    }

    @Test
    void addNewStatus_MissingArgException_EmptyName() {
        // Arrange
        TerStatus status = new TerStatus("");

        // Act
        assertThrows(MissingArgumentException.class, () -> terStatusService.addNewStatus(status));

        // Assert
        verify(terStatusRepository, never()).save(any());
    }

    @Test
    void addNewStatus_DuplicateValueExceptionTest() {
        // Arrange
        String existingStatusName = obsoleteStatus.getStatus_name();
        when(terStatusRepository.findTerStatusByStatusName(existingStatusName)).thenReturn(Optional.of(obsoleteStatus));

        TerStatus newStatus = new TerStatus(existingStatusName);

        // Act
        assertThrows(DuplicateValueException.class, () -> terStatusService.addNewStatus(newStatus));

        // Assert
        verify(terStatusRepository, never()).save(any());
    }
}