package com.demo.meetingplanner.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@ToString
@Data
public class Plan {
    private List<Event> events;
    private LocalDateTime plannedDate;

}
