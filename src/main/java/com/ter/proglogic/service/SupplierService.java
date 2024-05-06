package com.ter.proglogic.service;

import com.ter.proglogic.dto.Supplier;
import com.ter.proglogic.exceptions.DuplicateValueException;
import com.ter.proglogic.exceptions.MissingArgumentException;
import com.ter.proglogic.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<Supplier> getSuppliers() {
        return supplierRepository.findAll();
    }

    public void addNewSupplier(Supplier supplier) {
        if (!supplier.getSupplierName().isEmpty()) {
            String supplierNameUppercase = supplier.getSupplierName().toUpperCase();
            supplier.setSupplierName(supplierNameUppercase);
        } else {
            throw new MissingArgumentException("Missing supplier name");
        }

        Optional<Supplier> existingSupplier = supplierRepository.findSupplierBySupplierName(supplier.getSupplierName());
        if (existingSupplier.isPresent()) {
            throw new DuplicateValueException("Supplier already exists");
        } else {
            supplierRepository.save(supplier);
        }
    }
}