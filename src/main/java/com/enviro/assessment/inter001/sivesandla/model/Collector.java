package com.enviro.assessment.inter001.sivesandla.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "collector")
public class Collector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = true, unique = true)
    private String email;
    
    @Column(nullable = true, unique = true)
    private String phoneNumber;
    
    @Column(nullable = false)
    private int points;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true, length = 255)
    private String username;
    
    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Override equals() and hashCode() based on membershipNumber
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Collector)) return false;
        Collector collector = (Collector) o;
        return Objects.equals(username, collector.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
