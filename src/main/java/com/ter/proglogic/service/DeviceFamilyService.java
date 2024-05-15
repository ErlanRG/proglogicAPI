package com.ter.proglogic.service;

import com.ter.proglogic.dto.*;
import com.ter.proglogic.exceptions.DuplicateValueException;
import com.ter.proglogic.exceptions.InvalidAnnualReviewException;
import com.ter.proglogic.exceptions.MissingArgumentException;
import com.ter.proglogic.exceptions.NotFoundException;
import com.ter.proglogic.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceFamilyService {
    private final DeviceFamilyRepository deviceFamilyRepository;
    private final SupplierRepository supplierRepository;
    private final PldTypeRepository pldTypeRepository;
    private final LifecyclePhaseRepository lifecyclePhaseRepository;
    private final TerStatusRepository terStatusRepository;

    @Autowired
    public DeviceFamilyService(DeviceFamilyRepository deviceFamilyRepository, SupplierRepository supplierRepository, PldTypeRepository pldTypeRepository, LifecyclePhaseRepository lifecyclePhaseRepository, TerStatusRepository terStatusRepository) {
        this.deviceFamilyRepository = deviceFamilyRepository;
        this.supplierRepository = supplierRepository;
        this.pldTypeRepository = pldTypeRepository;
        this.lifecyclePhaseRepository = lifecyclePhaseRepository;
        this.terStatusRepository = terStatusRepository;
    }

    public List<DeviceFamily> getAllDevices() {
        return deviceFamilyRepository.findAll();
    }

    public void addNewDevice(DeviceFamily deviceFamily) {
        validateDeviceFamily(deviceFamily);

        // Device family to uppercase
        deviceFamily.setFamilyName(deviceFamily.getFamilyName().toUpperCase());

        // Family prefix to uppercase
        deviceFamily.setFamilyName(deviceFamily.getFamilyPrefix().toUpperCase());

        deviceFamilyRepository.findDeviceFamilyByFamilyName(deviceFamily.getFamilyName()).ifPresent(existingDevice -> {
            throw new DuplicateValueException("Can't add the new device. Device family already exists");
        });

        deviceFamilyRepository.save(deviceFamily);
    }

    private void validateDeviceFamily(DeviceFamily deviceFamily) {
        // Validate that the family name is not empty
        String familyName = deviceFamily.getFamilyName();
        if (familyName == null || familyName.isEmpty()) {
            throw new MissingArgumentException("Family name is required");
        }

        // Validate that the supplier is not null
        Supplier supplier = deviceFamily.getSupplier();
        if (supplier == null) {
            throw new MissingArgumentException("Supplier is required");
        }

        // Validate that the PLD type is not null
        PldType pldType = deviceFamily.getPldType();
        if (pldType == null) {
            throw new MissingArgumentException("PLD type is required");
        }

        LifecyclePhase lifecycle = deviceFamily.getLifecyclePhase();
        if (lifecycle == null) {
            throw new MissingArgumentException("Lifecycle phase is required");
        }

        TerStatus terStatus = deviceFamily.getStatus();
        if (terStatus == null) {
            throw new MissingArgumentException("TerStatus is required");
        }

        // Validate that the next annual review is not null and greater than 0
        Integer nextAnnualReview = deviceFamily.getNextAnnualReview();
        if (nextAnnualReview == null || nextAnnualReview <= 0) {
            throw new InvalidAnnualReviewException("Next Annual Review must be greater than 0");
        }

        // Search for the supplier by name
        supplierRepository.findSupplierBySupplierName(supplier.getSupplierName()).ifPresentOrElse(deviceFamily::setSupplier, () -> {
            throw new NotFoundException("Supplier not found");
        });

        // Search for the PLD type by name
        pldTypeRepository.findPldTypeByPldTypeName(pldType.getPldTypeName()).ifPresentOrElse(deviceFamily::setPldType, () -> {
            throw new NotFoundException("PLD type not found");
        });

        lifecyclePhaseRepository.findLifecyclePhaseByLifecycleName(lifecycle.getLifecycleName()).ifPresentOrElse(deviceFamily::setLifecyclePhase, () -> {
            throw new NotFoundException("Lifecycle phase not found");
        });

        terStatusRepository.findTerStatusByStatusName(terStatus.getStatus_name()).ifPresentOrElse(deviceFamily::setStatus, () -> {
            throw new NotFoundException("Teradyne status not found");
        });
    }

    public DeviceFamily getDeviceByPartNumber(String partNumber) {
        if (partNumber == null || partNumber.isEmpty()) {
            throw new MissingArgumentException("Part number is required");
        }

        return deviceFamilyRepository.findDeviceFamilyByPartNumber(partNumber).orElseThrow(() -> new NotFoundException("Device not found"));
    }
}