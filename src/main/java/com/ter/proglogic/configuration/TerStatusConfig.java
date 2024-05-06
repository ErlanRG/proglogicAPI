package com.ter.proglogic.configuration;

import com.ter.proglogic.dto.TerStatus;
import com.ter.proglogic.repository.TerStatusRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class TerStatusConfig {
    @Bean
    CommandLineRunner clrDefaultTerStatuses(TerStatusRepository terStatusRepository) {
        return args -> {
            if (terStatusRepository.count() == 0) {
                List<TerStatus> defaultTerStatuses = Arrays.asList(new TerStatus("NO NEW USE"), new TerStatus("RESTRICTED USE"), new TerStatus("DISCONTINUED"), new TerStatus("DISCONTINUANCE PENDING"));
                terStatusRepository.saveAll(defaultTerStatuses);
            }
        };
    }
}