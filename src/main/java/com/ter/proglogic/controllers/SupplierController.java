package com.ter.proglogic.controllers;

import com.ter.proglogic.service.SupplierService;
import com.ter.proglogic.dto.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/suppliers")
public class SupplierController {
    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierManager) {
        this.supplierService = supplierManager;
    }

    @GetMapping
    public List<Supplier> getSuppliers() {
        return supplierService.getSuppliers();
    }

    @PostMapping("/addSupplier")
    public void addSupplier(@RequestBody Supplier supplier) {
        supplierService.addNewSupplier(supplier);
    }
}