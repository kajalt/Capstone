package com.capstone.UserAuthenticationService.repositories;


import com.capstone.UserAuthenticationService.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session,Long> {
}