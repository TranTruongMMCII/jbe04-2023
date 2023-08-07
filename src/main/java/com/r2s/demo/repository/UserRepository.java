package com.r2s.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.r2s.demo.entity.User;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//	HQL: viet code java -> thong qua hibernate -> dich sang sql query

	Optional<User> findById(Long id);

//	List<User> findByName(String name);

	List<User> findByNameContaining(String name);

	List<User> findByNameLike(String name);

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "select id, name, age from `user` u where u.name like :name")
	List<User> findByName(String name);

	List<User> findByIsDeleted(boolean isDeleted);

	Optional<User> findByUsername(String username);
}
