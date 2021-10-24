package com.zm.messagingnetwork.repository;

import com.zm.messagingnetwork.entity.Message;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {


    @EntityGraph(attributePaths = {"comments"})
    List<Message> findAll();
}
