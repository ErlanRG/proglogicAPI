package com.ter.proglogic.service;

import com.ter.proglogic.dto.LifecyclePhase;
import com.ter.proglogic.exceptions.DuplicateValueException;
import com.ter.proglogic.exceptions.MissingArgumentException;
import com.ter.proglogic.repository.LifecyclePhaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing operations related to Lifecycle Phase entities.
 */
@Service
public class LifecyclePhaseService {
    private final LifecyclePhaseRepository lifecyclePhaseRepository;

    /**
     * Constructor for initializing LifecyclePhaseService with a repository.
     *
     * @param lifecyclePhaseRepository repository for LifecyclePhase entities
     */
    @Autowired
    public LifecyclePhaseService(LifecyclePhaseRepository lifecyclePhaseRepository) {
        this.lifecyclePhaseRepository = lifecyclePhaseRepository;
    }

    /**
     * Retrieves all Lifecycle Phase entities.
     *
     * @return list of all Lifecycle Phase entities
     */
    public List<LifecyclePhase> getAllLifecyclePhases() {
        return lifecyclePhaseRepository.findAll();
    }

    /**
     * Adds a new Lifecycle Phase entity.
     *
     * @param lifecyclePhase the Lifecycle Phase entity to add
     * @throws DuplicateValueException   if the Lifecycle Phase already exists
     * @throws MissingArgumentException if the lifecycle name is missing
     */
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
