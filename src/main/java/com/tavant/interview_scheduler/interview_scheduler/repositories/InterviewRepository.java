package com.tavant.interview_scheduler.interview_scheduler.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tavant.interview_scheduler.interview_scheduler.entities.Candidate;
import com.tavant.interview_scheduler.interview_scheduler.entities.Interview;

import java.util.List;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {
    List<Interview> findByCandidate(Candidate candidate);

    List<Interview> findByInterviewerName(String interviewerName);

    List<Interview> findByInterviewStatus(String interviewStatus);
}
