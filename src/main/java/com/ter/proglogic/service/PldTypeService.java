package com.ter.proglogic.service;

import com.ter.proglogic.dto.PldType;
import com.ter.proglogic.exceptions.DuplicateValueException;
import com.ter.proglogic.exceptions.MissingArgumentException;
import com.ter.proglogic.repository.PldTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        if (!pldType.getPldTypeName().isEmpty()) {
            String pldTypeNameUppercase = pldType.getPldTypeName().toUpperCase();
            pldType.setPldTypeName(pldTypeNameUppercase);
        } else {
            throw new MissingArgumentException("Missing PLD type name");
        }

        Optional<PldType> existingPldType = pldTypeRepository.findPldTypeByPldTypeName(pldType.getPldTypeName());

        if (existingPldType.isPresent()) {
            throw new DuplicateValueException("PLD type already exists");
        } else {
            pldTypeRepository.save(pldType);
        }
    }
}
