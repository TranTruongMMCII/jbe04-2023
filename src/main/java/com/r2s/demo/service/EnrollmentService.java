package com.r2s.demo.service;

import org.springframework.http.ResponseEntity;

import com.r2s.demo.DTO.EnrollmentRequestDTO;

public interface EnrollmentService {
	public ResponseEntity<?> createEnrollment(EnrollmentRequestDTO enrollmentRequestDTO);
}
