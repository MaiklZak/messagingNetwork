package com.zm.messagingnetwork.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.zm.messagingnetwork.entity.User;
import com.zm.messagingnetwork.entity.UserSubscription;
import com.zm.messagingnetwork.entity.Views;
import com.zm.messagingnetwork.service.ProfileService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "profile-controller", description = "Endpoints for handling and managing user related operations")
@RestController
@RequestMapping("profile")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @ApiOperation(value = "Get user by id")
    @GetMapping("{id}")
    @JsonView(Views.FullProfile.class)
    public User get(@PathVariable("id") User user) {
        return user;
    }

    @ApiOperation(value = "Subscribe current user to channel and return channel")
    @PostMapping("change-subscription/{channelId}")
    @JsonView(Views.FullProfile.class)
    public User changeSubscription(@AuthenticationPrincipal User subscriber,
                                   @PathVariable("channelId") User channel) {
        if (subscriber.equals(channel)) {
            return channel;
        } else {
            return profileService.changeSubscription(channel, subscriber);
        }
    }

    @ApiOperation(value = "Get subscribers by channel")
    @GetMapping("/get-subscribers/{channelId}")
    @JsonView(Views.IdName.class)
    public List<UserSubscription> subscribers(@PathVariable("channelId") User channel) {
        return profileService.getSubscribers(channel);
    }

    @ApiOperation(value = "Change status subscriber to current user")
    @PostMapping("/change-status/{subscriberId}")
    @JsonView(Views.IdName.class)
    public UserSubscription changeSubscriptionStatus(@AuthenticationPrincipal User channel,
                                                     @PathVariable("subscriberId") User subscriber) {
        return profileService.changeSubscriptionStatus(channel, subscriber);
    }
}
