package com.r2s.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.r2s.demo.DTO.EnrollmentRequestDTO;
import com.r2s.demo.constants.ResponseCode;
import com.r2s.demo.entity.User;
import com.r2s.demo.exceptions.InvalidValueException;
import com.r2s.demo.repository.CourseRepository;
import com.r2s.demo.repository.EnrollmentRepository;
import com.r2s.demo.repository.UserRepository;
import com.r2s.demo.service.EnrollmentService;

@Service(value = "EnrollmentServiceImpl")
public class EnrollmentServiceImpl extends BaseServiceImpl implements EnrollmentService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private EnrollmentRepository enrollmentRepository;

	@Override
	public ResponseEntity<?> createEnrollment(EnrollmentRequestDTO enrollmentRequestDTO) {
		try {
			// validate user
			Long userId = enrollmentRequestDTO.getUserId();
			User foundUser = this.userRepository.findById(userId).orElse(null);
			if (ObjectUtils.isEmpty(foundUser)) {
				throw new InvalidValueException();
			}
			
			return success(foundUser);

			// validate course
//			Long courseId = enrollmentRequestDTO.getCourseId();
//			Course foundCourse = this.courseRepository.findById(courseId).orElse(null);
//			if (ObjectUtils.isEmpty(foundCourse)) {
//				throw new InvalidValueException();
//			}

			// validate previous enrollment
			// tim ton tai enrollment co user va course duoc truyen vao chua

			// insert enrollment
			// 1. insert vao enrollment
//			Enrollment enrollment = new Enrollment();
//			enrollment.setUser(foundUser);
//			enrollment.setCourse(foundCourse);
//			enrollment.setDate(new Date());
//			enrollment.setNote("hello world");
//			this.enrollmentRepository.save(enrollment);

			// 2. insert thong qua user
//			foundUser.getEnrollments().add(enrollment);
//			this.userRepository.save(foundUser);

			// 3. insert thong qua course
//			foundCourse.getEnrollments().add(enrollment);
//			this.courseRepository.save(foundCourse);

//			return success(enrollment);
		} catch (InvalidValueException e) {
			return error(ResponseCode.INVALID_VALUE.getCode(), ResponseCode.INVALID_VALUE.getMessage());
		}
	}

}
