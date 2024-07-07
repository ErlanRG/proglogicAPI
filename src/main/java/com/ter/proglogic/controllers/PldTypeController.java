package com.ter.proglogic.controllers;

import com.ter.proglogic.dto.PldType;
import com.ter.proglogic.service.PldTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing PLD (Programmable Logic Device) types.
 */
@RestController
@RequestMapping(path = "api/v1/pld-types")
public class PldTypeController {
    private final PldTypeService pldTypeService;

    /**
     * Constructor for PldTypeController, which injects the PldTypeService.
     *
     * @param pldTypeService the service used to manage PLD types
     */
    @Autowired
    public PldTypeController(PldTypeService pldTypeService) {
        this.pldTypeService = pldTypeService;
    }

    /**
     * Endpoint to retrieve all PLD types.
     *
     * @return a list of all PLD types
     */
    @GetMapping
    public List<PldType> getPldTypes() {
        return pldTypeService.getAllPldTypes();
    }

    /**
     * Endpoint to add a new PLD type.
     *
     * @param pldType the PLD type to be added
     */
    @PostMapping("/addPldType")
    public void addPldType(@RequestBody PldType pldType) {
        pldTypeService.addNewPldType(pldType);
    }
}
