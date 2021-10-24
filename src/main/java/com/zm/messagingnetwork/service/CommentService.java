package com.zm.messagingnetwork.service;

import com.zm.messagingnetwork.entity.Comment;
import com.zm.messagingnetwork.entity.User;
import com.zm.messagingnetwork.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment create(Comment comment, User user) {
        comment.setAuthor(user);
        commentRepository.save(comment);

        return comment;
    }
}
