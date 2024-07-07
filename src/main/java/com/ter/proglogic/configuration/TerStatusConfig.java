package com.ter.proglogic.configuration;

import com.ter.proglogic.dto.TerStatus;
import com.ter.proglogic.repository.TerStatusRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * Configuration class for setting up default TER statuses in the application.
 */
@Configuration
public class TerStatusConfig {

    /**
     * Bean definition for a CommandLineRunner that initializes the TER statuses if none exist in the repository.
     *
     * @param terStatusRepository the repository to manage TerStatus entities
     * @return a CommandLineRunner that sets up the default TER statuses
     */
    @Bean
    CommandLineRunner clrDefaultTerStatuses(TerStatusRepository terStatusRepository) {
        return args -> {
            // Check if the repository is empty and populate it with default TER statuses if true
            if (terStatusRepository.count() == 0) {
                List<TerStatus> defaultTerStatuses = Arrays.asList(
                        new TerStatus("NO NEW USE"),
                        new TerStatus("RESTRICTED USE"),
                        new TerStatus("DISCONTINUED"),
                        new TerStatus("DISCONTINUANCE PENDING")
                );
                terStatusRepository.saveAll(defaultTerStatuses);
            }
        };
    }
}
