package com.ter.proglogic.service;

import com.ter.proglogic.dto.TerStatus;
import com.ter.proglogic.exceptions.DuplicateValueException;
import com.ter.proglogic.exceptions.MissingArgumentException;
import com.ter.proglogic.repository.TerStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing operations related to TER statuses.
 */
@Service
public class TerStatusService {
    private final TerStatusRepository terStatusRepository;

    /**
     * Constructor for initializing TerStatusService with a repository.
     *
     * @param terStatusRepository repository for TER statuses
     */
    @Autowired
    public TerStatusService(TerStatusRepository terStatusRepository) {
        this.terStatusRepository = terStatusRepository;
    }

    /**
     * Retrieves all TER statuses.
     *
     * @return list of all TER statuses
     */
    public List<TerStatus> getAllStatuses() {
        return terStatusRepository.findAll();
    }

    /**
     * Adds a new TER status.
     *
     * @param terStatus the TER status to add
     * @throws DuplicateValueException   if the TER status already exists
     * @throws MissingArgumentException if the status name is missing
     */
    public void addNewStatus(TerStatus terStatus) {
        if (terStatus.getStatus_name() == null || terStatus.getStatus_name().isEmpty()) {
            throw new MissingArgumentException("Missing status name");
        }

        String statusNameUppercase = terStatus.getStatus_name().toUpperCase();
        terStatus.setStatus_name(statusNameUppercase);

        terStatusRepository.findTerStatusByStatusName(statusNameUppercase).ifPresent(existingStatus -> {
            throw new DuplicateValueException("Status already exists");
        });

        terStatusRepository.save(terStatus);
    }
}
