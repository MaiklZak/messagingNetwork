package com.zm.messagingnetwork.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.io.Serializable;

@ApiModel(description = "data model of user subscription entity")
@Entity
@Data
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@NoArgsConstructor
public class UserSubscription implements Serializable {

    @ApiModelProperty(value = "user id")
    @EmbeddedId
    @JsonIgnore
    private UserSubscriptionId id;

    @ApiModelProperty(value = "user subscription")
    @MapsId("channelId")
    @ManyToOne
    @JsonView(Views.IdName.class)
    @JsonIdentityInfo(
            property = "id",
            generator = ObjectIdGenerators.PropertyGenerator.class
    )
    private User channel;

    @ApiModelProperty(value = "user subscriber")
    @MapsId("subscriberId")
    @ManyToOne
    @JsonView(Views.IdName.class)
    @JsonIdentityInfo(
            property = "id",
            generator = ObjectIdGenerators.PropertyGenerator.class
    )
    private User subscriber;

    @ApiModelProperty(value = "is subscription active")
    @JsonView(Views.IdName.class)
    private boolean active;

    public UserSubscription(User channel, User subscriber) {
        this.channel = channel;
        this.subscriber = subscriber;
        this.id = new UserSubscriptionId(channel.getId(), subscriber.getId());
    }
}
