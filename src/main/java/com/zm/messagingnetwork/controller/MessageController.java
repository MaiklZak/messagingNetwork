package com.zm.messagingnetwork.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.zm.messagingnetwork.dto.MessagePageDto;
import com.zm.messagingnetwork.entity.Message;
import com.zm.messagingnetwork.entity.User;
import com.zm.messagingnetwork.entity.Views;
import com.zm.messagingnetwork.service.MessageService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Tag(name = "message-controller", description = "Endpoints for handling and managing message related operations")
@RestController
@RequestMapping("message")
public class MessageController {

    public static final int MESSAGE_PER_PAGE = 3;

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }


    @ApiOperation(value = "Get page of messages", response = MessagePageDto.class)
    @GetMapping
    @JsonView(Views.FullMessage.class)
    public MessagePageDto list(@AuthenticationPrincipal User user,
                               @PageableDefault(size = MESSAGE_PER_PAGE, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return messageService.findForUser(pageable, user);
    }

    @ApiOperation(value = "Get message by id")
    @GetMapping("{id}")
    @JsonView(Views.FullMessage.class)
    public Message getOne(@PathVariable("id") Message message) {
        return message;
    }

    @ApiOperation(value = "Create message and return created message")
    @PostMapping
    @JsonView(Views.FullMessage.class)
    public Message create(@RequestBody Message message,
                          @AuthenticationPrincipal User user) throws IOException {
        return messageService.create(message, user);
    }

    @ApiOperation(value = "Update message by id and return updated message")
    @PutMapping("{id}")
    @JsonView(Views.FullMessage.class)
    public Message update(@PathVariable("id") Message messageFromDb,
                          @RequestBody Message message,
                          @AuthenticationPrincipal User user) throws IOException {
        return messageService.update(messageFromDb, message, user);
    }

    @ApiOperation(value = "Delete message by id ")
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message) {
        messageService.delete(message);
    }
}
