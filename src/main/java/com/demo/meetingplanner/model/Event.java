package com.demo.meetingplanner.model;

import com.demo.meetingplanner.enums.EventType;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Event {
    private String name;
    private LocalDateTime startDate;
    private int durationMinute;
    private EventType type;
}
