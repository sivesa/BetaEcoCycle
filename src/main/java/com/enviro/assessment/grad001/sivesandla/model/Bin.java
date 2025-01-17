package com.enviro.assessment.grad001.sivesandla.model;

import javax.persistence.*;

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
}
