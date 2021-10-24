package com.zm.messagingnetwork.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.zm.messagingnetwork.entity.User;
import com.zm.messagingnetwork.entity.Views;
import com.zm.messagingnetwork.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class MainController {

    private final MessageRepository messageRepository;
    private final ObjectWriter writer;


    @Value("${spring.profile.active}")
    private String profile;

    public MainController(MessageRepository messageRepository, ObjectMapper objectMapper) {
        this.messageRepository = messageRepository;

        this.writer = objectMapper
                .setConfig(objectMapper.getSerializationConfig())
                .writerWithView(Views.FullMessage.class);
    }

    @GetMapping
    public String main(Model model, @AuthenticationPrincipal User user) throws JsonProcessingException {
        Map<Object, Object> data = new HashMap<>();

        if (user != null) {
            data.put("profile", user);
            String message = writer.writeValueAsString(messageRepository.findAll());
            model.addAttribute("messages", message);
        }

        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode", "dev".equals(profile));
        return "index";
    }
}
