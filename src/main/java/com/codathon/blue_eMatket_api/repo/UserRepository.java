package com.codathon.blue_eMatket_api.repo;

import com.codathon.blue_eMatket_api.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, String> {
}
