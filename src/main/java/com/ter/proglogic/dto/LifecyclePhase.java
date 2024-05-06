package com.ter.proglogic.dto;

import jakarta.persistence.*;

@Entity
@Table(name = "lifecycle_phases")
public class LifecyclePhase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lifecycle_phase_id")
    private Long lifecyclePhaseId;

    @Column(name = "lifecycle_name")
    private String lifecycleName;

    public LifecyclePhase() {
    }

    public LifecyclePhase(String lifecycleName) {
        this.lifecycleName = lifecycleName;
    }

    public LifecyclePhase(Long lifecyclePhaseId, String lifecycleName) {
        this.lifecyclePhaseId = lifecyclePhaseId;
        this.lifecycleName = lifecycleName;
    }

    public Long getLifecyclePhaseId() {
        return lifecyclePhaseId;
    }

    public void setLifecyclePhaseId(Long lifecyclePhaseId) {
        this.lifecyclePhaseId = lifecyclePhaseId;
    }

    public String getLifecycleName() {
        return lifecycleName;
    }

    public void setLifecycleName(String lifecycleName) {
        this.lifecycleName = lifecycleName;
    }

    @Override
    public String toString() {
        return "LifecyclePhase{" + "lifecyclePhaseId=" + lifecyclePhaseId + ", lifecycleName='" + lifecycleName + '\'' + '}';
    }
}
