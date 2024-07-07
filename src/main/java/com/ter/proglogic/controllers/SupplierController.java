package com.ter.proglogic.controllers;

import com.ter.proglogic.service.SupplierService;
import com.ter.proglogic.dto.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing suppliers.
 */
@RestController
@RequestMapping(path = "api/v1/suppliers")
public class SupplierController {
    private final SupplierService supplierService;

    /**
     * Constructor for SupplierController, which injects the SupplierService.
     *
     * @param supplierManager the service used to manage suppliers
     */
    @Autowired
    public SupplierController(SupplierService supplierManager) {
        this.supplierService = supplierManager;
    }

    /**
     * Endpoint to retrieve all suppliers.
     *
     * @return a list of all suppliers
     */
    @GetMapping
    public List<Supplier> getSuppliers() {
        return supplierService.getSuppliers();
    }

    /**
     * Endpoint to add a new supplier.
     *
     * @param supplier the supplier to be added
     */
    @PostMapping("/addSupplier")
    public void addSupplier(@RequestBody Supplier supplier) {
        supplierService.addNewSupplier(supplier);
    }
}
