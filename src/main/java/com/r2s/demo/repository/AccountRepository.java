package com.r2s.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.r2s.demo.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
