package com.ter.proglogic.configuration;

import com.ter.proglogic.dto.PldType;
import com.ter.proglogic.repository.PldTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class PldTypeConfig {
    @Bean
    CommandLineRunner clrDefaultPldTypes(PldTypeRepository pldTypeRepository) {
        return args -> {
            if (pldTypeRepository.count() == 0) {
                List<PldType> defaultPldTypes = Arrays.asList(new PldType("FPGA"), new PldType("CPLD"), new PldType("ASIC"), new PldType("Config Device"));
                pldTypeRepository.saveAll(defaultPldTypes);
            }
        };
    }
}