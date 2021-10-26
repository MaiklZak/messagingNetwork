package com.zm.messagingnetwork.repository;

import com.zm.messagingnetwork.entity.User;
import com.zm.messagingnetwork.entity.UserSubscription;
import com.zm.messagingnetwork.entity.UserSubscriptionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSubscriptionRepository extends JpaRepository<UserSubscription, UserSubscriptionId> {

    List<UserSubscription> findBySubscriber(User user);
}
