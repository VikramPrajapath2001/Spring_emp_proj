package com.tavant.interview_scheduler.interview_scheduler.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tavant.interview_scheduler.interview_scheduler.entities.*;
import com.tavant.interview_scheduler.interview_scheduler.exceptions.ResourceNotFoundException;
import com.tavant.interview_scheduler.interview_scheduler.repositories.*;

import java.util.List;

@Service
public class InterviewService {

    private final InterviewRepository interviewRepository;
    private final CandidateRepository candidateRepository;

    public InterviewService(InterviewRepository interviewRepository, CandidateRepository candidateRepository) {
        this.interviewRepository = interviewRepository;
        this.candidateRepository = candidateRepository;
    }

    public Interview scheduleInterview(Long candidateId, Interview interview) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found with ID: " + candidateId));

        interview.setCandidate(candidate);
        return interviewRepository.save(interview);
    }

    @Transactional(readOnly = true)
    public List<Interview> getInterviewsByCriteria(String interviewerName, String interviewStatus) {
        if (interviewerName != null) {
            return interviewRepository.findByInterviewerName(interviewerName);
        }
        if (interviewStatus != null) {
            return interviewRepository.findByInterviewStatus(interviewStatus);
        }
        return interviewRepository.findAll();
    }

    @Transactional
    public Interview updateInterviewStatus(Long interviewId, String status, String feedback) {
        Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Interview not found with ID: " + interviewId));

        interview.setInterviewStatus(status);
        interview.setFeedback(feedback);
        return interviewRepository.save(interview);
    }

}
