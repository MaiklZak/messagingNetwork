package com.zm.messagingnetwork.repository;

import com.zm.messagingnetwork.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<Comment, Long> {
}
