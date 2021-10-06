package com.bcp.reto.exchange.rate.repository;

import com.bcp.reto.exchange.rate.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsernameAndStatus(String username, Integer status);
}

