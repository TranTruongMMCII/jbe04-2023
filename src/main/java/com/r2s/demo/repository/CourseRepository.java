package com.r2s.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.r2s.demo.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
