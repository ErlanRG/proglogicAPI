package com.ter.proglogic.configuration;

import com.ter.proglogic.dto.PldType;
import com.ter.proglogic.repository.PldTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * Configuration class for setting up default PLD (Programmable Logic Device) types in the application.
 */
@Configuration
public class PldTypeConfig {

    /**
     * Bean definition for a CommandLineRunner that initializes the PLD types if none exist in the repository.
     *
     * @param pldTypeRepository the repository to manage PldType entities
     * @return a CommandLineRunner that sets up the default PLD types
     */
    @Bean
    CommandLineRunner clrDefaultPldTypes(PldTypeRepository pldTypeRepository) {
        return args -> {
            // Check if the repository is empty and populate it with default PLD types if true
            if (pldTypeRepository.count() == 0) {
                List<PldType> defaultPldTypes = Arrays.asList(
                        new PldType("FPGA"),
                        new PldType("CPLD"),
                        new PldType("ASIC"),
                        new PldType("Config Device")
                );
                pldTypeRepository.saveAll(defaultPldTypes);
            }
        };
    }
}
