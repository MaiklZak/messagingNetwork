package com.zm.messagingnetwork.repository;

import com.zm.messagingnetwork.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<User, String> {
}
