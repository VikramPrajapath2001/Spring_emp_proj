package com.tavant.interview_scheduler.interview_scheduler.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tavant.interview_scheduler.interview_scheduler.entities.Interview;
import com.tavant.interview_scheduler.interview_scheduler.services.InterviewService;

import java.util.List;

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {

    private final InterviewService interviewService;

    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    // 1️⃣ API to schedule an interview for a candidate
    @PostMapping("/{candidateId}/schedule")
    public ResponseEntity<Interview> scheduleInterview(@PathVariable Long candidateId,
            @RequestBody Interview interview) {
        Interview scheduledInterview = interviewService.scheduleInterview(candidateId, interview);
        return ResponseEntity.ok(scheduledInterview);
    }

    // 2️⃣ API to get all interviews (filtered by interviewer name or status)
    @GetMapping("/list")
    public ResponseEntity<List<Interview>> getInterviews(
            @RequestParam(required = false) String interviewerName,
            @RequestParam(required = false) String interviewStatus) {

        List<Interview> interviews = interviewService.getInterviewsByCriteria(interviewerName, interviewStatus);
        return ResponseEntity.ok(interviews);
    }

    // 3️⃣ API to update interview status and feedback
    @PatchMapping("/{id}/status")
    public ResponseEntity<Interview> updateInterviewStatus(
            @PathVariable Long id,
            @RequestParam String status,
            @RequestParam(required = false) String feedback) {

        Interview updatedInterview = interviewService.updateInterviewStatus(id, status, feedback);
        return ResponseEntity.ok(updatedInterview);
    }

}
