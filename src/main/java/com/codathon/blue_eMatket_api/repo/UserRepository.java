package com.codathon.blue_eMatket_api.repo;

import com.codathon.blue_eMatket_api.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {



    Optional<Users> findByUserNameAndPassword(String userName,String password);
}
