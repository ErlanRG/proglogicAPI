package com.ter.proglogic.service;

import com.ter.proglogic.dto.Supplier;
import com.ter.proglogic.exceptions.DuplicateValueException;
import com.ter.proglogic.exceptions.MissingArgumentException;
import com.ter.proglogic.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing operations related to suppliers.
 */
@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;

    /**
     * Constructor for initializing SupplierService with a repository.
     *
     * @param supplierRepository repository for suppliers
     */
    @Autowired
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    /**
     * Retrieves all suppliers.
     *
     * @return list of all suppliers
     */
    public List<Supplier> getSuppliers() {
        return supplierRepository.findAll();
    }

    /**
     * Adds a new supplier.
     *
     * @param supplier the supplier to add
     * @throws DuplicateValueException   if the supplier already exists
     * @throws MissingArgumentException if the supplier name is missing
     */
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
