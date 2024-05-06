package com.ter.proglogic.dto;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "device_families")
public class DeviceFamily {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deviceFamilyId;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "supplier_id", referencedColumnName = "supplier_id")
    private Supplier supplier;

    private String familyName;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "pld_type_id", referencedColumnName = "pld_type_id")
    private PldType pldType;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "lifecycle_phase_id", referencedColumnName = "lifecycle_phase_id")
    private LifecyclePhase lifecyclePhase;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "status_id", referencedColumnName = "status_id")
    private TerStatus status;

    private Integer yearIntroduced;

    @Transient
    private Integer yearsInProduction;

    private Integer nextAnnualReview;

    @Transient
    private Integer yearsToEol;

    private String notes;

    public DeviceFamily() {
    }

    public DeviceFamily(Long deviceFamilyId, Supplier supplier, String familyName, PldType pldType, LifecyclePhase lifecyclePhase, TerStatus status, Integer yearIntroduced, Integer nextAnnualReview, String notes) {
        this.deviceFamilyId = deviceFamilyId;
        this.supplier = supplier;
        this.familyName = familyName;
        this.pldType = pldType;
        this.lifecyclePhase = lifecyclePhase;
        this.status = status;
        this.yearIntroduced = yearIntroduced;
        this.nextAnnualReview = nextAnnualReview;
        this.notes = notes;
    }

    public Long getDeviceFamilyId() {
        return deviceFamilyId;
    }

    public void setDeviceFamilyId(Long deviceFamilyId) {
        this.deviceFamilyId = deviceFamilyId;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public PldType getPldType() {
        return pldType;
    }

    public void setPldType(PldType pldType) {
        this.pldType = pldType;
    }

    public LifecyclePhase getLifecyclePhase() {
        return lifecyclePhase;
    }

    public void setLifecyclePhase(LifecyclePhase lifecyclePhase) {
        this.lifecyclePhase = lifecyclePhase;
    }

    public TerStatus getStatus() {
        return status;
    }

    public void setStatus(TerStatus status) {
        this.status = status;
    }

    public Integer getYearIntroduced() {
        return yearIntroduced;
    }

    public void setYearIntroduced(Integer yearIntroduced) {
        this.yearIntroduced = yearIntroduced;
    }

    public Integer getYearsInProduction() {
        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();
        return currentYear - this.yearIntroduced;
    }

    public void setYearsInProduction(Integer yearsInProduction) {
        this.yearsInProduction = yearsInProduction;
    }

    public Integer getNextAnnualReview() {
        return nextAnnualReview;
    }

    public void setNextAnnualReview(Integer nextAnnualReview) {
        this.nextAnnualReview = nextAnnualReview;
    }

    public Integer getYearsToEol() {
        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();
        return this.nextAnnualReview - currentYear;
    }

    public void setYearsToEol(Integer yearsToEol) {
        this.yearsToEol = yearsToEol;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "DeviceFamily{" + "deviceFamilyId=" + deviceFamilyId + ", supplier=" + supplier + ", familyName='" + familyName + '\'' + ", pldType=" + pldType + ", lifecyclePhase=" + lifecyclePhase + ", status=" + status + ", yearIntroduced=" + yearIntroduced + ", yearsInProduction=" + yearsInProduction + ", nextAnnualReview=" + nextAnnualReview + ", yearsToEol=" + yearsToEol + ", notes='" + notes + '\'' + '}';
    }
}