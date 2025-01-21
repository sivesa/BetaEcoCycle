package com.enviro.assessment.inter001.sivesandla.model;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "bin")
public class Bin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long binId;

    @Column(nullable = false)
    private String wasteType;

    @Column(nullable = false)
    private Integer capacity;

    @Column(nullable = false)
    private String status; // Full/Empty

    // Add relationship with Household
    @ManyToOne(fetch = FetchType.LAZY, optional = false) 
    @JoinColumn(name = "household_id", nullable = false)
    @JsonIgnore // TODO: use DTO instead
    private Household household;

    // Getters and Setters

    public Long getBinId() {
        return binId;
    }

    public void setBinId(Long binId) {
        this.binId = binId;
    }

    public String getWasteType() {
        return wasteType;
    }

    public void setWasteType(String wasteType) {
        this.wasteType = wasteType;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Household getHousehold() {
        return household;
    }

    public void setHousehold(Household household) {
        this.household = household;
    }
}

