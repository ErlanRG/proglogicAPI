package com.ter.proglogic.controllers;

import com.ter.proglogic.dto.LifecyclePhase;
import com.ter.proglogic.repository.LifecyclePhaseRepository;
import com.ter.proglogic.service.LifecyclePhaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/lifecycle-phases")
public class LifecyclePhaseController {
    private final LifecyclePhaseService lifecyclePhaseService;

    @Autowired
    public LifecyclePhaseController(LifecyclePhaseService lifecyclePhaseService, LifecyclePhaseRepository lifecyclePhaseRepository) {
        this.lifecyclePhaseService = lifecyclePhaseService;
    }

    @GetMapping
    public List<LifecyclePhase> getAllLifecyclePhases() {
        return lifecyclePhaseService.getAllLifecyclePhases();
    }

    @PostMapping("/addLifecyclePhase")
    public void addLifecyclePhase(@RequestBody LifecyclePhase lifecyclePhase) {
        lifecyclePhaseService.addNewLifecyclePhase(lifecyclePhase);
    }
}