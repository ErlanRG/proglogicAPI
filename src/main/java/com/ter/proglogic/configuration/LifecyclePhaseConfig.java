package com.ter.proglogic.configuration;

import com.ter.proglogic.dto.LifecyclePhase;
import com.ter.proglogic.repository.LifecyclePhaseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * Configuration class for setting up default lifecycle phases in the application.
 */
@Configuration
public class LifecyclePhaseConfig {

    /**
     * Bean definition for a CommandLineRunner that initializes the lifecycle phases if none exist in the repository.
     *
     * @param lifecyclePhaseRepository the repository to manage LifecyclePhase entities
     * @return a CommandLineRunner that sets up the default lifecycle phases
     */
    @Bean
    CommandLineRunner clrDefaultLifecyclePhases(LifecyclePhaseRepository lifecyclePhaseRepository) {
        return args -> {
            // Check if the repository is empty and populate it with default lifecycle phases if true
            if (lifecyclePhaseRepository.count() == 0) {
                List<LifecyclePhase> defaultLifecyclePhases = Arrays.asList(
                        new LifecyclePhase("DECLINE"),
                        new LifecyclePhase("MAINSTREAM"),
                        new LifecyclePhase("MATURE"),
                        new LifecyclePhase("NEW"),
                        new LifecyclePhase("OBSOLETE")
                );
                lifecyclePhaseRepository.saveAll(defaultLifecyclePhases);
            }
        };
    }
}
