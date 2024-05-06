package com.ter.proglogic.dto;

import jakarta.persistence.*;

@Entity
@Table(name = "pld_types")
public class PldType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pld_type_id")
    private Long pldTypeId;

    @Column(name = "pld_type_name")
    private String pldTypeName;

    public PldType() {
    }

    public PldType(String pldTypeName) {
        this.pldTypeName = pldTypeName;
    }

    public PldType(Long pldTypeId, String pldTypeName) {
        this.pldTypeId = pldTypeId;
        this.pldTypeName = pldTypeName;
    }

    public Long getPldTypeId() {
        return pldTypeId;
    }

    public void setPldTypeId(Long pldTypeId) {
        this.pldTypeId = pldTypeId;
    }

    public String getPldTypeName() {
        return pldTypeName;
    }

    public void setPldTypeName(String pldTypeName) {
        this.pldTypeName = pldTypeName;
    }

    @Override
    public String toString() {
        return "PldType{" + "pldTypeId=" + pldTypeId + ", pldTypeName='" + pldTypeName + '\'' + '}';
    }
}