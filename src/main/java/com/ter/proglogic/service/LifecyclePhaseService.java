package com.ter.proglogic.service;

import com.ter.proglogic.dto.LifecyclePhase;
import com.ter.proglogic.exceptions.DuplicateValueException;
import com.ter.proglogic.exceptions.MissingArgumentException;
import com.ter.proglogic.repository.LifecyclePhaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if (lifecyclePhase.getLifecycleName() == null || lifecyclePhase.getLifecycleName().isEmpty()) {
            throw new MissingArgumentException("Missing lifecycle name");
        }

        String lifecycleNameUppercase = lifecyclePhase.getLifecycleName().toUpperCase();
        lifecyclePhase.setLifecycleName(lifecycleNameUppercase);

        lifecyclePhaseRepository.findLifecyclePhaseByLifecycleName(lifecycleNameUppercase).ifPresent(existingLifecyclePhase -> {
            throw new DuplicateValueException("Lifecycle phase already exists");
        });

        lifecyclePhaseRepository.save(lifecyclePhase);
    }
}
