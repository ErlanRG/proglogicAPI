package com.ter.proglogic.configuration;

import com.ter.proglogic.dto.Supplier;
import com.ter.proglogic.repository.SupplierRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SupplierConfig {
    @Bean
    CommandLineRunner clrDefaultSuppliers(SupplierRepository supplierRepository) {
        return args -> {
            if (supplierRepository.count() == 0) {
                List<Supplier> defaultSuppliers = Arrays.asList(new Supplier("INTEL"), new Supplier("AMD"), new Supplier("LATTICE"));
                supplierRepository.saveAll(defaultSuppliers);
            }
        };
    }
}
