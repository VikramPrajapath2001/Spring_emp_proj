package com.tavant.interview_scheduler.interview_scheduler.controllers;

import com.tavant.interview_scheduler.interview_scheduler.entities.Candidate;
import com.tavant.interview_scheduler.interview_scheduler.exceptions.ResourceNotFoundException;
import com.tavant.interview_scheduler.interview_scheduler.services.CandidateService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    // POST /api/candidates/register
    // Expects multipart/form-data request.
    @PostMapping(value = "/register", consumes = "multipart/form-data")
    public ResponseEntity<Candidate> registerCandidate(
            @RequestParam @Valid String name,
            @RequestParam @Valid String email,
            @RequestParam @Valid String phone,
            @RequestParam MultipartFile resume) throws IOException {

        // If the resume file is missing or empty, throw an exception
        if (resume.isEmpty()) {
            throw new IllegalArgumentException("Resume file is required.");
        }
        Candidate savedCandidate = candidateService.saveCandidate(name, email, phone, resume);
        return ResponseEntity.ok(savedCandidate);
    }

    // GET /api/candidates/search?name=...&email=...&phone=...
    @GetMapping("/search")
    public ResponseEntity<List<Candidate>> searchCandidates(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone) {

        List<Candidate> candidates = candidateService.searchCandidates(name, email, phone);
        if (candidates.isEmpty()) {
            throw new ResourceNotFoundException("No candidates found matching the criteria");
        }
        return ResponseEntity.ok(candidates);
    }

    // GET /api/candidates/{id}/resume
    @GetMapping("/{id}/resume")
    public ResponseEntity<byte[]> getResume(@PathVariable Long id) {
        byte[] resumeData = candidateService.getResume(id);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=resume.pdf")
                .header("Content-Type", "application/pdf")
                .body(resumeData);
    }

    // PATCH /api/candidates/{id}/offer-status?status=...
    @PatchMapping("/{id}/offer-status")
    public ResponseEntity<Candidate> updateOfferStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        Candidate candidate = candidateService.getCandidateById(id);
        candidate.setOfferStatus(status);
        Candidate updatedCandidate = candidateService.saveCandidate(candidate);
        return ResponseEntity.ok(updatedCandidate);
    }
}
