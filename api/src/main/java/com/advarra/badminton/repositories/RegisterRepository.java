package com.advarra.badminton.repositories;


import com.advarra.badminton.model.Tournament;
import com.advarra.badminton.model.UserCredentials;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface RegisterRepository extends JpaRepository<UserCredentials, Long> {
    UserCredentials findByContactInfo(String contactInfo);

    UserCredentials findBySessionId(String sessionId);
}
