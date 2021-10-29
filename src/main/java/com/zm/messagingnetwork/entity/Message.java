package com.zm.messagingnetwork.entity;

import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@ApiModel(description = "data model of message entity")
@Entity
@Table
@ToString(of = {"id", "text"})
@EqualsAndHashCode(of = {"id"})
@Data
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Message {

    @ApiModelProperty(value = "id of message generated automatically")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Id.class)
    private Long id;

    @ApiModelProperty(value = "text of message")
    @JsonView(Views.IdName.class)
    private String text;

    @ApiModelProperty(value = "date of writing the message")
    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonView(Views.FullMessage.class)
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonView(Views.FullMessage.class)
    private User author;

    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL)
    @JsonView(Views.FullMessage.class)
    private List<Comment> comments;

    @ApiModelProperty(value = "link of message to external resource")
    @JsonView(Views.FullMessage.class)
    private String link;

    @ApiModelProperty(value = "link title")
    @JsonView(Views.FullMessage.class)
    private String linkTitle;

    @ApiModelProperty(value = "description of link")
    @JsonView(Views.FullMessage.class)
    private String linkDescription;

    @ApiModelProperty(value = "cover of link")
    @JsonView(Views.FullMessage.class)
    private String linkCover;

}
