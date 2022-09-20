package com.codathon.blue_eMatket_api.repo;

import com.codathon.blue_eMatket_api.model.Policy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Integer> {
}
