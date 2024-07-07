package com.ter.proglogic.controllers;

import com.ter.proglogic.dto.LifecyclePhase;
import com.ter.proglogic.repository.LifecyclePhaseRepository;
import com.ter.proglogic.service.LifecyclePhaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing lifecycle phases.
 */
@RestController
@RequestMapping(path = "api/v1/lifecycle-phases")
public class LifecyclePhaseController {
    private final LifecyclePhaseService lifecyclePhaseService;

    /**
     * Constructor for LifecyclePhaseController, which injects the LifecyclePhaseService.
     *
     * @param lifecyclePhaseService the service used to manage lifecycle phases
     * @param lifecyclePhaseRepository the repository used for lifecycle phases (currently unused)
     */
    @Autowired
    public LifecyclePhaseController(LifecyclePhaseService lifecyclePhaseService, LifecyclePhaseRepository lifecyclePhaseRepository) {
        this.lifecyclePhaseService = lifecyclePhaseService;
    }

    /**
     * Endpoint to retrieve all lifecycle phases.
     *
     * @return a list of all lifecycle phases
     */
    @GetMapping
    public List<LifecyclePhase> getAllLifecyclePhases() {
        return lifecyclePhaseService.getAllLifecyclePhases();
    }

    /**
     * Endpoint to add a new lifecycle phase.
     *
     * @param lifecyclePhase the lifecycle phase to be added
     */
    @PostMapping("/addLifecyclePhase")
    public void addLifecyclePhase(@RequestBody LifecyclePhase lifecyclePhase) {
        lifecyclePhaseService.addNewLifecyclePhase(lifecyclePhase);
    }
}
