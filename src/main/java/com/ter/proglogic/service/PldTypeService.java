package com.ter.proglogic.service;

import com.ter.proglogic.dto.PldType;
import com.ter.proglogic.exceptions.DuplicateValueException;
import com.ter.proglogic.exceptions.MissingArgumentException;
import com.ter.proglogic.repository.PldTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing operations related to Programmable Logic Device (PLD) types.
 */
@Service
public class PldTypeService {
    private final PldTypeRepository pldTypeRepository;

    /**
     * Constructor for initializing PldTypeService with a repository.
     *
     * @param pldTypeRepository repository for PLD types
     */
    @Autowired
    public PldTypeService(PldTypeRepository pldTypeRepository) {
        this.pldTypeRepository = pldTypeRepository;
    }

    /**
     * Retrieves all PLD types.
     *
     * @return list of all PLD types
     */
    public List<PldType> getAllPldTypes() {
        return pldTypeRepository.findAll();
    }

    /**
     * Adds a new PLD type.
     *
     * @param pldType the PLD type to add
     * @throws DuplicateValueException   if the PLD type already exists
     * @throws MissingArgumentException if the PLD type name is missing
     */
    public void addNewPldType(PldType pldType) {
        if (pldType.getPldTypeName() == null || pldType.getPldTypeName().isEmpty()) {
            throw new MissingArgumentException("Missing PLD type name");
        }

        String pldTypeNameUppercase = pldType.getPldTypeName().toUpperCase();
        pldType.setPldTypeName(pldTypeNameUppercase);

        pldTypeRepository.findPldTypeByPldTypeName(pldTypeNameUppercase).ifPresent(existingPld -> {
            throw new DuplicateValueException("PLD type already exists");
        });

        pldTypeRepository.save(pldType);
    }
}
