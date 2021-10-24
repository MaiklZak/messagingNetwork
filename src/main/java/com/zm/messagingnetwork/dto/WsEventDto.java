package com.zm.messagingnetwork.dto;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonView;
import com.zm.messagingnetwork.entity.Views;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonView(Views.Id.class)
public class WsEventDto {

    private ObjectType objectType;
    private EventType eventType;

    @JsonRawValue
    private String body;
}
