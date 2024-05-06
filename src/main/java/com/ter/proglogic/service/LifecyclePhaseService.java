package com.ter.proglogic.service;

import com.ter.proglogic.dto.LifecyclePhase;
import com.ter.proglogic.exceptions.DuplicateValueException;
import com.ter.proglogic.exceptions.MissingArgumentException;
import com.ter.proglogic.repository.LifecyclePhaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LifecyclePhaseService {
    private final LifecyclePhaseRepository lifecyclePhaseRepository;

    @Autowired
    public LifecyclePhaseService(LifecyclePhaseRepository lifecyclePhaseRepository) {
        this.lifecyclePhaseRepository = lifecyclePhaseRepository;
    }

    public List<LifecyclePhase> getAllLifecyclePhases() {
        return lifecyclePhaseRepository.findAll();
    }

    public void addNewLifecyclePhase(LifecyclePhase lifecyclePhase) {
        if (!lifecyclePhase.getLifecycleName().isEmpty()) {
            String lifecycleNameUppercase = lifecyclePhase.getLifecycleName().toUpperCase();
            lifecyclePhase.setLifecycleName(lifecycleNameUppercase);
        } else {
            throw new MissingArgumentException("Missing lifecycle name");
        }

        Optional<LifecyclePhase> existingLifecyclePhase = lifecyclePhaseRepository.findLifecyclePhaseByLifecycleName(lifecyclePhase.getLifecycleName());
        if (existingLifecyclePhase.isPresent()) {
            throw new DuplicateValueException("Lifecycle phase already exists");
        } else {
            lifecyclePhaseRepository.save(lifecyclePhase);
        }
    }
}
