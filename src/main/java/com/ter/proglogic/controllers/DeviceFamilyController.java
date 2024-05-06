package com.ter.proglogic.controllers;

import com.ter.proglogic.dto.DeviceFamily;
import com.ter.proglogic.service.DeviceFamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/device-families")
public class DeviceFamilyController {
    private final DeviceFamilyService deviceFamilyService;

    @Autowired
    public DeviceFamilyController(DeviceFamilyService deviceFamilyService) {
        this.deviceFamilyService = deviceFamilyService;
    }

    @GetMapping
    public List<DeviceFamily> getAllDevices() {
        return deviceFamilyService.getAllDevices();
    }

    @PostMapping("/addDeviceFamily")
    public void addDevice(@RequestBody DeviceFamily deviceFamily) {
        deviceFamilyService.addNewDevice(deviceFamily);
    }

    @GetMapping("/{partNumber}")
    public DeviceFamily getDeviceFamilyByPartNumber(@PathVariable String partNumber) {
        return deviceFamilyService.getDeviceByPartNumber(partNumber);
    }
}