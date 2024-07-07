package com.ter.proglogic.controllers;

import com.ter.proglogic.dto.TerStatus;
import com.ter.proglogic.service.TerStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing TER statuses.
 */
@RestController
@RequestMapping(path = "/api/v1/ter-statuses")
public class TerStatusController {
    private final TerStatusService terStatusService;

    /**
     * Constructor for TerStatusController, which injects the TerStatusService.
     *
     * @param terStatusService the service used to manage TER statuses
     */
    @Autowired
    public TerStatusController(TerStatusService terStatusService) {
        this.terStatusService = terStatusService;
    }

    /**
     * Endpoint to retrieve all TER statuses.
     *
     * @return a list of all TER statuses
     */
    @GetMapping
    public List<TerStatus> getStatuses() {
        return terStatusService.getAllStatuses();
    }

    /**
     * Endpoint to add a new TER status.
     *
     * @param terStatus the TER status to be added
     */
    @PostMapping("/addStatus")
    public void addStatus(@RequestBody TerStatus terStatus) {
        terStatusService.addNewStatus(terStatus);
    }
}
