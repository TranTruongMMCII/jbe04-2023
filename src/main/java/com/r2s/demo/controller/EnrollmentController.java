package com.r2s.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.r2s.demo.DTO.EnrollmentRequestDTO;
import com.r2s.demo.service.EnrollmentService;

@RestController
@RequestMapping(path = "enrollments")
public class EnrollmentController {
	@Autowired
	@Qualifier(value = "EnrollmentServiceImpl")
	private EnrollmentService enrollmentService;

	@PostMapping
	public ResponseEntity<?> createEnrollment(@RequestBody EnrollmentRequestDTO enrollmentRequestDTO) {
		return this.enrollmentService.createEnrollment(enrollmentRequestDTO);
	}
}
