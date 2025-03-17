package com.fahimshop.repository;


import com.fahimshop.model.UserD;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserD, Long> {

}