package com.ter.proglogic.service;

import com.ter.proglogic.dto.PldType;
import com.ter.proglogic.exceptions.DuplicateValueException;
import com.ter.proglogic.exceptions.MissingArgumentException;
import com.ter.proglogic.repository.PldTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PldTypeService {
    private final PldTypeRepository pldTypeRepository;

    @Autowired
    public PldTypeService(PldTypeRepository pldTypeRepository) {
        this.pldTypeRepository = pldTypeRepository;
    }

    public List<PldType> getAllPldTypes() {
        return pldTypeRepository.findAll();
    }

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
