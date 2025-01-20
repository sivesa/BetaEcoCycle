package com.enviro.assessment.inter001.sivesandla.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "household")
public class Household {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long householdId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String address;
    
    @Column(nullable = false)
    private int points;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true, length = 10)
    private String username;
    
    // To nsure the Household entity has the corresponding @OneToMany relationship
    @OneToMany(mappedBy = "household", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bin> bins = new ArrayList<>();


    // Getters and Setters
    public Long getHouseholdId() {
        return householdId;
    }

    public void setHouseholdId(Long householdId) {
        this.householdId = householdId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    // Override equals() and hashCode() based on membershipNumber
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Household)) return false;
        Household household = (Household) o;
        return Objects.equals(username, household.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}

