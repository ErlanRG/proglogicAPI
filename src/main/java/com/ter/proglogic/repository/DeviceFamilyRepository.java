package com.ter.proglogic.repository;

import com.ter.proglogic.dto.DeviceFamily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceFamilyRepository extends JpaRepository<DeviceFamily, Long> {

    /**
     * Custom query to find a DeviceFamily by its family name.
     *
     * @param familyName the name of the family to search for
     * @return an Optional containing the DeviceFamily if found, or empty otherwise
     */
    @Query("SELECT d FROM DeviceFamily d WHERE d.familyName = ?1")
    Optional<DeviceFamily> findDeviceFamilyByFamilyName(String familyName);

    /**
     * Custom query to find a DeviceFamily by its part number, using a substring match based on familyPrefix.
     *
     * @param partNumber the part number to search for
     * @return an Optional containing the DeviceFamily if found, or empty otherwise
     */
    @Query("SELECT d FROM DeviceFamily d WHERE LEFT(?1, LENGTH(d.familyPrefix)) = d.familyPrefix")
    Optional<DeviceFamily> findDeviceFamilyByPartNumber(String partNumber);
}
