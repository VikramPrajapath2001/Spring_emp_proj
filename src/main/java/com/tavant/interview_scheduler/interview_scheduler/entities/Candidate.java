package com.tavant.interview_scheduler.interview_scheduler.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "candidates")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String phone;
    
    @JsonIgnore
    @Lob
    @Column(columnDefinition = "BYTEA")
    private byte[] resume;
    
    @Column(name = "resume_name")
    private String resumeName;

    @Column(nullable = false)
    private String status = "Pending"; // Selected, Rejected, On Hold

    @Column(nullable = false)
    private String offerStatus = "Pending"; // Offer Letter Released, Pending

    public Candidate(Long id, String name, String email, String phone, byte[] resume, String resumeName, String status,
            String offerStatus) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.resume = resume;
        this.resumeName = resumeName;
        this.status = status;
        this.offerStatus = offerStatus;
    }

    public String getResumeName() {
        return resumeName;
    }

    public void setResumeName(String resumeName) {
        this.resumeName = resumeName;
    }

    public Candidate() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public byte[] getResume() {
        return resume;
    }

    public void setResume(byte[] resume) {
        this.resume = resume;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(String offerStatus) {
        this.offerStatus = offerStatus;
    }
}
