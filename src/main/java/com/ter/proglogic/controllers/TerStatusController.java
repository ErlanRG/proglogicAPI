package com.ter.proglogic.controllers;

import com.ter.proglogic.dto.TerStatus;
import com.ter.proglogic.service.TerStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/ter-statuses")
public class TerStatusController {
    private final TerStatusService terStatusService;

    @Autowired
    public TerStatusController(TerStatusService terStatusService) {
        this.terStatusService = terStatusService;
    }

    @GetMapping
    public List<TerStatus> getStatuses() {
        return terStatusService.getAllStatuses();
    }

    @PostMapping("/addStatus")
    public void addStatus(@RequestBody TerStatus terStatus) {
        terStatusService.addNewStatus(terStatus);
    }
}