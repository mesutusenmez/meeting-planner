package com.demo.meetingplanner.dto;

import com.demo.meetingplanner.enums.EventType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class EventResultDTO {
    private String name;
    private Integer duration;
    private EventType eventType;
    private LocalDateTime startDate;
}
