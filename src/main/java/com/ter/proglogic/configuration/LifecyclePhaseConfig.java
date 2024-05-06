package com.ter.proglogic.configuration;

import com.ter.proglogic.dto.LifecyclePhase;
import com.ter.proglogic.repository.LifecyclePhaseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class LifecyclePhaseConfig {
    @Bean
    CommandLineRunner clrDefaultLifecyclePhases(LifecyclePhaseRepository lifecyclePhaseRepository) {
        return args -> {
            if (lifecyclePhaseRepository.count() == 0) {
                List<LifecyclePhase> defaultLifecyclePhases = Arrays.asList(new LifecyclePhase("DECLINE"), new LifecyclePhase("MAINSTREAM"), new LifecyclePhase("MATURE"), new LifecyclePhase("NEW"), new LifecyclePhase("OBSOLETE"));
                lifecyclePhaseRepository.saveAll(defaultLifecyclePhases);
            }
        };
    }
}
