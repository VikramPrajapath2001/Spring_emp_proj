package com.tavant.interview_scheduler.interview_scheduler.services;

import com.tavant.interview_scheduler.interview_scheduler.entities.Candidate;
import com.tavant.interview_scheduler.interview_scheduler.exceptions.ResourceNotFoundException;
import com.tavant.interview_scheduler.interview_scheduler.repositories.CandidateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;

    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Transactional
    public Candidate saveCandidate(String name, String email, String phone, MultipartFile resume) throws IOException {
        Candidate candidate = new Candidate();
        candidate.setName(name);
        candidate.setEmail(email);
        candidate.setPhone(phone);
        candidate.setResume(resume.getBytes()); // Convert file to byte array
        candidate.setResumeName(resume.getOriginalFilename());
        return candidateRepository.save(candidate);
    }

    @Transactional
    public Candidate saveCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    @Transactional(readOnly = true)
    public Candidate getCandidateById(Long id) {
        return candidateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found with ID: " + id));
    }

    @Transactional(readOnly = true) // Ensures LOB stream can be accessed
    public List<Candidate> searchCandidates(String name, String email, String phone) {
        return candidateRepository.findByNameOrEmailOrPhone(name, email, phone);
    }

    @Transactional(readOnly = true) // Ensures transaction remains active for LOB retrieval
    public byte[] getResume(Long id) {
        Candidate candidate = getCandidateById(id);
        if (candidate.getResume() == null) {
            throw new ResourceNotFoundException("No resume found for candidate ID: " + id);
        }
        return candidate.getResume();
    }
}

