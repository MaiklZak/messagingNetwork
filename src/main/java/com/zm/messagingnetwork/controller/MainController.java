package com.zm.messagingnetwork.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.zm.messagingnetwork.dto.MessagePageDto;
import com.zm.messagingnetwork.entity.User;
import com.zm.messagingnetwork.entity.Views;
import com.zm.messagingnetwork.repository.UserDetailsRepository;
import com.zm.messagingnetwork.service.MessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    private final MessageService messageService;
    private final UserDetailsRepository userDetailsRepository;
    private final ObjectWriter messageWriter;
    private final ObjectWriter profileWriter;


    @Value("${spring.profile.active:prod}")
    private String profile;

    public MainController(MessageService messageService, UserDetailsRepository userDetailsRepository, ObjectMapper objectMapper) {
        this.messageService = messageService;
        this.userDetailsRepository = userDetailsRepository;

        ObjectMapper mapper = objectMapper
                .setConfig(objectMapper.getSerializationConfig());

        this.messageWriter = mapper
                .writerWithView(Views.FullMessage.class);
        this.profileWriter = mapper
                .writerWithView(Views.FullProfile.class);
    }

    @GetMapping
    public String main(Model model, @AuthenticationPrincipal User user) throws JsonProcessingException {
        Map<Object, Object> data = new HashMap<>();

        if (user != null) {
            User userFromDb = userDetailsRepository.findById(user.getId()).get();
            String serializedProfile = profileWriter.writeValueAsString(userFromDb);
            model.addAttribute("profile", serializedProfile);

            Sort sort = Sort.by(Sort.Direction.DESC, "id");
            PageRequest pageRequest = PageRequest.of(0, MessageController.MESSAGE_PER_PAGE, sort);
            MessagePageDto messagePageDto = messageService.findForUser(pageRequest, user);

            String message = messageWriter.writeValueAsString(messagePageDto.getMessages());

            model.addAttribute("messages", message);
            data.put("currentPage", messagePageDto.getCurrentPage());
            data.put("totalPages", messagePageDto.getTotalPages());
        } else {
            model.addAttribute("messages", "[]");
            model.addAttribute("profile", "null");
        }

        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode", "dev".equals(profile));
        return "index";
    }
}
