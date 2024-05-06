package com.ter.proglogic.service;

import com.ter.proglogic.dto.DeviceFamily;
import com.ter.proglogic.dto.PldType;
import com.ter.proglogic.dto.Supplier;
import com.ter.proglogic.exceptions.DuplicateValueException;
import com.ter.proglogic.exceptions.InvalidAnnualReviewException;
import com.ter.proglogic.exceptions.MissingArgumentException;
import com.ter.proglogic.exceptions.NotFoundException;
import com.ter.proglogic.repository.DeviceFamilyRepository;
import com.ter.proglogic.repository.PldTypeRepository;
import com.ter.proglogic.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceFamilyService {
    private final DeviceFamilyRepository deviceFamilyRepository;
    private final SupplierRepository supplierRepository;
    private final PldTypeRepository pldTypeRepository;

    @Autowired
    public DeviceFamilyService(DeviceFamilyRepository deviceFamilyRepository, SupplierRepository supplierRepository, PldTypeRepository pldTypeRepository) {
        this.deviceFamilyRepository = deviceFamilyRepository;
        this.supplierRepository = supplierRepository;
        this.pldTypeRepository = pldTypeRepository;
    }

    public List<DeviceFamily> getAllDevices() {
        return deviceFamilyRepository.findAll();
    }

    public void addNewDevice(DeviceFamily deviceFamily) {
        validateDeviceFamily(deviceFamily);

        // Device family to uppercase
        String deviceFamilyNameUppercase = deviceFamily.getFamilyName().toUpperCase();
        deviceFamily.setFamilyName(deviceFamilyNameUppercase);

        Optional<DeviceFamily> existingDevice = deviceFamilyRepository.findDeviceFamilyByFamilyName(deviceFamily.getFamilyName());
        if (existingDevice.isPresent()) {
            throw new DuplicateValueException("The device family already exists");
        } else {
            deviceFamilyRepository.save(deviceFamily);
        }
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

        // Validate that the next annual review is not null and greater than 0
        Integer nextAnnualReview = deviceFamily.getNextAnnualReview();
        if (nextAnnualReview == null || nextAnnualReview <= 0) {
            throw new InvalidAnnualReviewException("Next Annual Review must be greater than 0");
        }

        // Search for the supplier by name
        Optional<Supplier> existingSupplier = supplierRepository.findSupplierBySupplierName(supplier.getSupplierName());
        if (existingSupplier.isEmpty()) {
            throw new NotFoundException("Supplier not found");
        }

        // Search for the PLD type by name
        Optional<PldType> existingPldType = pldTypeRepository.findPldTypeByPldTypeName(pldType.getPldTypeName());
        if (existingPldType.isEmpty()) {
            throw new NotFoundException("PLD type not found");
        }
    }
}