package com.zm.messagingnetwork.entity;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@ApiModel(description = "data model of comment entity")
@Entity
@Table
@Data
@EqualsAndHashCode(of = {"id"})
public class Comment {

    @ApiModelProperty(value = "id of comment generated automatically")
    @Id
    @GeneratedValue
    @JsonView(Views.IdName.class)
    private Long id;

    @ApiModelProperty(value = "text of commit")
    @JsonView(Views.IdName.class)
    private String text;

    @ManyToOne
    @JoinColumn(name = "message_id")
    @JsonView(Views.FullComment.class)
    private Message message;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    @JsonView(Views.IdName.class)
    private User author;
}
