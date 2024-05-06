package com.ter.proglogic.service;

import com.ter.proglogic.dto.DeviceFamily;
import com.ter.proglogic.exceptions.DuplicateValueException;
import com.ter.proglogic.exceptions.InvalidAnnualReviewException;
import com.ter.proglogic.exceptions.MissingArgumentException;
import com.ter.proglogic.repository.DeviceFamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceFamilyService {
    private final DeviceFamilyRepository deviceFamilyRepository;

    @Autowired
    public DeviceFamilyService(DeviceFamilyRepository deviceFamilyRepository) {
        this.deviceFamilyRepository = deviceFamilyRepository;
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
        if (deviceFamily.getFamilyName() == null || deviceFamily.getFamilyName().isEmpty()) {
            throw new MissingArgumentException("Family name is required");
        }

        if (deviceFamily.getSupplier() == null) {
            throw new MissingArgumentException("Supplier is required");
        }

        if (deviceFamily.getPldType() == null) {
            throw new MissingArgumentException("PLD type is required");
        }

        if (deviceFamily.getNextAnnualReview() == null) {
            throw new MissingArgumentException("Next Annual Review is required");
        }

        if (deviceFamily.getNextAnnualReview() <= 0) {
            throw new InvalidAnnualReviewException("Next Annual Review must be greater than 0");
        }
    }
}