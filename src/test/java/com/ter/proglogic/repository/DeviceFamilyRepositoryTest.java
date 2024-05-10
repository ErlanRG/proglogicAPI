package com.ter.proglogic.repository;

import com.ter.proglogic.dto.*;
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
class DeviceFamilyRepositoryTest {

    DeviceFamily testDevice;

    @Autowired
    DeviceFamilyRepository deviceFamilyRepository;

    @BeforeEach
    void setUp() {
        LifecyclePhase lifecycle = new LifecyclePhase("OBSOLETE");
        PldType pldType = new PldType("FPGA");
        Supplier supplier = new Supplier("AMD");
        TerStatus status = new TerStatus("NO NEW USE");
        testDevice = new DeviceFamily(supplier, "SPARTAN 6", "XC6S", pldType, lifecycle, status, 2010, 2026, null);
    }

    @Test
    void getAllDevicesWhenValuesExistTest() {
        // Arrange
        deviceFamilyRepository.save(testDevice);

        // Act
        List<DeviceFamily> deviceFamilyList = deviceFamilyRepository.findAll();

        // Arrange
        Assertions.assertThat(deviceFamilyList).isNotEmpty();
        Assertions.assertThat(deviceFamilyList).isNotNull();
        Assertions.assertThat(deviceFamilyList).size().isGreaterThan(0);
    }

    @Test
    void getAllDevicesWhenNoValuesTest() {
        // Arrange
        List<DeviceFamily> deviceFamilyList;

        // Act
        deviceFamilyList = deviceFamilyRepository.findAll();

        // Arrange
        Assertions.assertThat(deviceFamilyList).isEmpty();
        Assertions.assertThat(deviceFamilyList).isNotNull();
        Assertions.assertThat(deviceFamilyList).size().isEqualTo(0);
    }

    @Test
    void findDeviceFamilyByFamilyName() {
        // Arrange
        deviceFamilyRepository.save(testDevice);

        // Act
        Optional<DeviceFamily> existingDeviceFamily = deviceFamilyRepository.findDeviceFamilyByFamilyName("SPARTAN 6");

        // Arrange
        Assertions.assertThat(existingDeviceFamily).isNotEmpty();
        Assertions.assertThat(existingDeviceFamily).isNotNull();
        Assertions.assertThat(existingDeviceFamily).isPresent();
        Assertions.assertThat(existingDeviceFamily.get().getFamilyName()).isEqualTo(testDevice.getFamilyName());
        Assertions.assertThat(existingDeviceFamily.get().getDeviceFamilyId()).isGreaterThan(0);
        Assertions.assertThat(existingDeviceFamily.get().getFamilyName()).isEqualTo("SPARTAN 6");
    }

    @Test
    void findDeviceFamilyByPartNumber() {
        // Arrange
        deviceFamilyRepository.save(testDevice);

        // Act
        Optional<DeviceFamily> existingDeviceFamily = deviceFamilyRepository.findDeviceFamilyByPartNumber("XC6S");

        // Arrange
        Assertions.assertThat(existingDeviceFamily).isNotEmpty();
        Assertions.assertThat(existingDeviceFamily).isNotNull();
        Assertions.assertThat(existingDeviceFamily).isPresent();
        Assertions.assertThat(existingDeviceFamily.get().getFamilyPrefix()).isEqualTo(testDevice.getFamilyPrefix());
        Assertions.assertThat(existingDeviceFamily.get().getDeviceFamilyId()).isGreaterThan(0);
        Assertions.assertThat(existingDeviceFamily.get().getFamilyPrefix()).isEqualTo("XC6S");
    }
}