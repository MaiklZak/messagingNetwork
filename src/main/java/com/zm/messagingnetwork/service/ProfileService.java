package com.zm.messagingnetwork.service;

import com.zm.messagingnetwork.entity.User;
import com.zm.messagingnetwork.repository.UserDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProfileService {

    private final UserDetailsRepository userDetailsRepository;

    public ProfileService(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    public User changeSubscription(User channel, User subscriber) {
        Set<User> subscribers = channel.getSubscribers();

        if (subscribers.contains(subscriber)) {
            subscribers.remove(subscriber);
        } else {
            subscribers.add(subscriber);
        }
        return userDetailsRepository.save(channel);
    }
}
