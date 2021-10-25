package com.zm.messagingnetwork.service;

import com.zm.messagingnetwork.dto.EventType;
import com.zm.messagingnetwork.dto.ObjectType;
import com.zm.messagingnetwork.entity.Comment;
import com.zm.messagingnetwork.entity.User;
import com.zm.messagingnetwork.entity.Views;
import com.zm.messagingnetwork.repository.CommentRepository;
import com.zm.messagingnetwork.util.WsSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.BiConsumer;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final BiConsumer<EventType, Comment> wsSender;

    public CommentService(CommentRepository commentRepository, WsSender wsSender) {
        this.commentRepository = commentRepository;
        this.wsSender = wsSender.getSender(ObjectType.COMMENT, Views.FullComment.class);
    }

    public Comment create(Comment comment, User user) {
        comment.setAuthor(user);
        Comment commentFromDb = commentRepository.save(comment);

        wsSender.accept(EventType.CREATE, commentFromDb);

        return commentFromDb;
    }
}
