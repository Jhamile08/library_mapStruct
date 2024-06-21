package com.riwi.library.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.library.domain.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
}
