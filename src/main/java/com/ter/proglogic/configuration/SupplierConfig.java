package com.ter.proglogic.configuration;

import com.ter.proglogic.dto.Supplier;
import com.ter.proglogic.repository.SupplierRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * Configuration class for setting up default suppliers in the application.
 */
@Configuration
public class SupplierConfig {

    /**
     * Bean definition for a CommandLineRunner that initializes the suppliers if none exist in the repository.
     *
     * @param supplierRepository the repository to manage Supplier entities
     * @return a CommandLineRunner that sets up the default suppliers
     */
    @Bean
    CommandLineRunner clrDefaultSuppliers(SupplierRepository supplierRepository) {
        return args -> {
            // Check if the repository is empty and populate it with default suppliers if true
            if (supplierRepository.count() == 0) {
                List<Supplier> defaultSuppliers = Arrays.asList(
                        new Supplier("INTEL"),
                        new Supplier("AMD"),
                        new Supplier("LATTICE")
                );
                supplierRepository.saveAll(defaultSuppliers);
            }
        };
    }
}
