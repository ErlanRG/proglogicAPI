package com.ter.proglogic.repository;

import com.ter.proglogic.dto.DeviceFamily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceFamilyRepository extends JpaRepository<DeviceFamily, Long> {
    @Query("SELECT d FROM DeviceFamily d WHERE d.familyName = ?1")
    Optional<DeviceFamily> findDeviceFamilyByFamilyName(String familyName);

    @Query("SELECT d FROM DeviceFamily d WHERE LEFT(?1, LENGTH(d.familyPrefix)) = d.familyPrefix")
    Optional<DeviceFamily> findDeviceFamilyByPartNumber(String partNumber);
}