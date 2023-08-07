package com.r2s.demo.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Embeddable
//class EnrollmentId {
//	@Column(name = "user_id")
//	private Long userId;
//
//	@Column(name = "course_id")
//	private Long courseId;
//}

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "enrollment")
public class Enrollment {
//	@EmbeddedId
//	private EnrollmentId id;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
//	@MapsId(value = "user_id")
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@JsonBackReference
	private User user;

	@ManyToOne
//	@MapsId(value = "course_id")
	@JoinColumn(name = "course_id", referencedColumnName = "id")
	@JsonBackReference
	private Course course;

	private Date date;
	private String note;
}
