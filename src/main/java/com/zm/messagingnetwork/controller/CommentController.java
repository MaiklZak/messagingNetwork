package com.zm.messagingnetwork.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.zm.messagingnetwork.entity.Comment;
import com.zm.messagingnetwork.entity.User;
import com.zm.messagingnetwork.entity.Views;
import com.zm.messagingnetwork.service.CommentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "comment-controller", description = "Endpoints for handling and managing comment related operations")
@RestController
@RequestMapping("comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @ApiOperation(value = "Create comment and return created comment")
    @PostMapping
    @JsonView(Views.FullComment.class)
    public Comment create(@RequestBody Comment comment, @AuthenticationPrincipal User user) {
        return commentService.create(comment, user);
    }
}
