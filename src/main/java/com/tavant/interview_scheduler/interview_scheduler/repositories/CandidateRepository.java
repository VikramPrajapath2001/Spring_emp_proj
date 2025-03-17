package com.tavant.interview_scheduler.interview_scheduler.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tavant.interview_scheduler.interview_scheduler.entities.Candidate;

import java.util.List;

@Repository

// Optional<Candidate> findByEmail(String email);

// Optional<Candidate> findByPhone(String phone);

// Optional<Candidate> findByName(String name);
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    List<Candidate> findByNameOrEmailOrPhone(String name, String email, String phone);
}
