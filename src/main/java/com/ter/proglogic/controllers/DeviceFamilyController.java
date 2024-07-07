package com.ter.proglogic.controllers;

import com.ter.proglogic.dto.DeviceFamily;
import com.ter.proglogic.service.DeviceFamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing device families.
 */
@RestController
@RequestMapping(path = "api/v1/device-families")
public class DeviceFamilyController {
    private final DeviceFamilyService deviceFamilyService;

    /**
     * Constructor for DeviceFamilyController, which injects the DeviceFamilyService.
     *
     * @param deviceFamilyService the service used to manage device families
     */
    @Autowired
    public DeviceFamilyController(DeviceFamilyService deviceFamilyService) {
        this.deviceFamilyService = deviceFamilyService;
    }

    /**
     * Endpoint to retrieve all device families.
     *
     * @return a list of all device families
     */
    @GetMapping
    public List<DeviceFamily> getAllDevices() {
        return deviceFamilyService.getAllDevices();
    }

    /**
     * Endpoint to add a new device family.
     *
     * @param deviceFamily the device family to be added
     */
    @PostMapping("/addDeviceFamily")
    public void addDevice(@RequestBody DeviceFamily deviceFamily) {
        deviceFamilyService.addNewDevice(deviceFamily);
    }

    /**
     * Endpoint to retrieve a device family by its part number.
     *
     * @param partNumber the part number of the device family to be retrieved
     * @return the device family with the specified part number
     */
    @GetMapping("/{partNumber}")
    public DeviceFamily getDeviceFamilyByPartNumber(@PathVariable String partNumber) {
        return deviceFamilyService.getDeviceByPartNumber(partNumber);
    }
}
