package com.ter.proglogic.service;

import com.ter.proglogic.dto.Supplier;
import com.ter.proglogic.exceptions.DuplicateValueException;
import com.ter.proglogic.exceptions.MissingArgumentException;
import com.ter.proglogic.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if (supplier.getSupplierName() == null || supplier.getSupplierName().isEmpty()) {
            throw new MissingArgumentException("Missing supplier name");
        }

        String supplierNameUppercase = supplier.getSupplierName().toUpperCase();
        supplier.setSupplierName(supplierNameUppercase);

        supplierRepository.findSupplierBySupplierName(supplierNameUppercase).ifPresent(existingSupplier -> {
            throw new DuplicateValueException("Supplier already exists");
        });

        supplierRepository.save(supplier);
    }
}