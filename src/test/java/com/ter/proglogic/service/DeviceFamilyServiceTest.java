package com.ter.proglogic.service;

import com.ter.proglogic.dto.*;
import com.ter.proglogic.repository.DeviceFamilyRepository;
import com.ter.proglogic.repository.PldTypeRepository;
import com.ter.proglogic.repository.SupplierRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeviceFamilyServiceTest {

    DeviceFamily testDevice;
    Supplier testSupplier;
    PldType testPldType;

    @Mock
    DeviceFamilyRepository deviceFamilyRepository;
    @Mock
    SupplierRepository supplierRepository;
    @Mock
    PldTypeRepository pldTypeRepository;

    @InjectMocks
    DeviceFamilyService deviceFamilyService;

    @BeforeEach
    void setUp() {
        LifecyclePhase testLifecycle = new LifecyclePhase("OBSOLETE");
        testPldType = new PldType("FPGA");
        testSupplier = new Supplier("AMD");
        TerStatus testStatus = new TerStatus("NO NEW USE");
        testDevice = new DeviceFamily(testSupplier, "SPARTAN 6", "XC6S", testPldType, testLifecycle, testStatus, 2010, 2026, null);
    }

    @Test
    void getAllDevices() {
        // Arrange
        List<DeviceFamily> deviceFamilies = List.of(testDevice);
        when(deviceFamilyRepository.findAll()).thenReturn(deviceFamilies);

        // Act
        List<DeviceFamily> expectedDeviceList = deviceFamilyService.getAllDevices();

        // Assert
        Assertions.assertThat(expectedDeviceList).containsExactlyElementsOf(deviceFamilies);
        Assertions.assertThat(expectedDeviceList).hasSize(1);
        Assertions.assertThat(expectedDeviceList).isEqualTo(deviceFamilies);
        Assertions.assertThat(expectedDeviceList).isNotEmpty();
    }

    @Test
    void addNewDevice() {
        // Arrange
        String deviceName = testDevice.getFamilyName();
        when(supplierRepository.findSupplierBySupplierName("AMD")).thenReturn(Optional.of(testSupplier));
        when(pldTypeRepository.findPldTypeByPldTypeName("FPGA")).thenReturn(Optional.of(testPldType));
        when(deviceFamilyRepository.findDeviceFamilyByFamilyName("SPARTAN 6")).thenReturn(Optional.empty());

        ArgumentCaptor<DeviceFamily> savedDevice = ArgumentCaptor.forClass(DeviceFamily.class);
        when(deviceFamilyRepository.save(savedDevice.capture())).thenReturn(testDevice);

        // Act
        deviceFamilyService.addNewDevice(testDevice);

        // Assertions
        verify(deviceFamilyRepository).save(testDevice);
        Assertions.assertThat(deviceName).isNotBlank();
        Assertions.assertThat(deviceName).isNotEmpty();
        Assertions.assertThat(deviceName).isNotNull();
        Assertions.assertThat(savedDevice.getValue()).isEqualTo(testDevice);
        Assertions.assertThat(savedDevice.getValue()).isNotNull();
        Assertions.assertThat(savedDevice.getValue().getFamilyName()).isEqualTo(testDevice.getFamilyName());
    }

    @Test
    void getDeviceByPartNumber() {
        // Arrange
        when(deviceFamilyRepository.findDeviceFamilyByPartNumber("XC6S")).thenReturn(Optional.of(testDevice));

        // Act
        DeviceFamily searchedDevice = deviceFamilyService.getDeviceByPartNumber("XC6S");

        // Arrange
        Assertions.assertThat(searchedDevice).isEqualTo(testDevice);
    }
}