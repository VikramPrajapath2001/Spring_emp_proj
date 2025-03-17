package com.tavant.interview_scheduler.interview_scheduler.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "interviews")
public class Interview {

    public Interview() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "candidate_id", nullable = false)
    private Candidate candidate;

    @Column(nullable = false)
    private LocalDateTime interviewDate;

    @Column(nullable = false)
    private int round;

    @Column(nullable = false)
    private String interviewerName;

    @Column(nullable = false)
    private String interviewerDesignation;

    @Column(nullable = false)
    private String interviewerEmail;

    private String feedback = "Pending";

    @Column(nullable = false)
    private String interviewStatus = "Scheduled"; // Selected, Rejected, On Hold

    public Interview(Long id, Candidate candidate, LocalDateTime interviewDate, int round, String interviewerName,
            String interviewerDesignation, String interviewerEmail, String feedback, String interviewStatus) {
        this.id = id;
        this.candidate = candidate;
        this.interviewDate = interviewDate;
        this.round = round;
        this.interviewerName = interviewerName;
        this.interviewerDesignation = interviewerDesignation;
        this.interviewerEmail = interviewerEmail;
        this.feedback = feedback;
        this.interviewStatus = interviewStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public LocalDateTime getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(LocalDateTime interviewDate) {
        this.interviewDate = interviewDate;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public String getInterviewerName() {
        return interviewerName;
    }

    public void setInterviewerName(String interviewerName) {
        this.interviewerName = interviewerName;
    }

    public String getInterviewerDesignation() {
        return interviewerDesignation;
    }

    public void setInterviewerDesignation(String interviewerDesignation) {
        this.interviewerDesignation = interviewerDesignation;
    }

    public String getInterviewerEmail() {
        return interviewerEmail;
    }

    public void setInterviewerEmail(String interviewerEmail) {
        this.interviewerEmail = interviewerEmail;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getInterviewStatus() {
        return interviewStatus;
    }

    public void setInterviewStatus(String interviewStatus) {
        this.interviewStatus = interviewStatus;
    }
}
