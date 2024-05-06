package com.ter.proglogic.dto;

import jakarta.persistence.*;

@Entity
@Table(name = "ter_statuses")
public class TerStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    private Long status_id;

    @Column(name = "status_name")
    private String status_name;

    public TerStatus() {
    }

    public TerStatus(String status_name) {
        this.status_name = status_name;
    }

    public TerStatus(Long status_id, String status_name) {
        this.status_id = status_id;
        this.status_name = status_name;
    }

    public Long getStatus_id() {
        return status_id;
    }

    public void setStatus_id(Long status_id) {
        this.status_id = status_id;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    @Override
    public String toString() {
        return "TerStatus{" + "status_id=" + status_id + ", status_name='" + status_name + '\'' + '}';
    }
}