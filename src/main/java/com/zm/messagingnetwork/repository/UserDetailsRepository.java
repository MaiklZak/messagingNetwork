package com.zm.messagingnetwork.repository;

import com.zm.messagingnetwork.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<User, String> {

    @EntityGraph(attributePaths = {"subscriptions", "subscribers"})
    Optional<User> findById(String s);
}
