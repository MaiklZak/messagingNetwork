package com.zm.messagingnetwork.entity;

import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@ApiModel(description = "data model of user entity")
@Entity
@Table(name = "users")
@EqualsAndHashCode(of = {"id"})
@Data
@ToString(of = {"id", "name"})
public class User implements Serializable {

    @ApiModelProperty(value = "user id")
    @Id
    @JsonView(Views.IdName.class)
    private String id;

    @ApiModelProperty(value = "name of user", example = "John Johnson", required = true)
    @JsonView(Views.IdName.class)
    private String name;

    @ApiModelProperty(value = "user's picture")
    @JsonView(Views.IdName.class)
    private String userpic;

    @ApiModelProperty(value = "email of user", required = true, example = "john@mail.org")
    private String email;

    @ApiModelProperty(value = "gender of user")
    @JsonView(Views.FullProfile.class)
    private String gender;

    @ApiModelProperty(value = "locale of user")
    @JsonView(Views.FullProfile.class)
    private String locale;

    @ApiModelProperty(value = "date of user's last visit")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonView(Views.FullProfile.class)
    private LocalDateTime lastVisit;

    @ApiModelProperty(value = "list of user subscriptions")
    @JsonView(Views.FullProfile.class)
    @OneToMany(mappedBy = "subscriber", orphanRemoval = true)
    private Set<UserSubscription> subscriptions = new HashSet<>();


    @ApiModelProperty(value = "list of user subscribers")
    @JsonView(Views.FullProfile.class)
    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL)
    private Set<UserSubscription> subscribers = new HashSet<>();
}
