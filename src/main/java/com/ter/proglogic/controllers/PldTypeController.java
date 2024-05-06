package com.ter.proglogic.controllers;

import com.ter.proglogic.dto.PldType;
import com.ter.proglogic.service.PldTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/pld-types")
public class PldTypeController {
    private final PldTypeService pldTypeService;

    @Autowired
    public PldTypeController(PldTypeService pldTypeService) {
        this.pldTypeService = pldTypeService;
    }

    @GetMapping
    public List<PldType> getPldTypes() {
        return pldTypeService.getAllPldTypes();
    }

    @PostMapping("/addPldType")
    public void addPldType(@RequestBody PldType pldType) {
        pldTypeService.addNewPldType(pldType);
    }
}