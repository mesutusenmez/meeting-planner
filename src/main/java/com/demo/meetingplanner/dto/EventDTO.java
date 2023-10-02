package com.demo.meetingplanner.dto;

import com.demo.meetingplanner.enums.EventTypeInput;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EventDTO {
    private String name;
    private Integer duration;
    private EventTypeInput eventType;

}
