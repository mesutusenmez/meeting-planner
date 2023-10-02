package com.demo.meetingplanner.request;

import com.demo.meetingplanner.dto.EventDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventRequest {
    private List<EventDTO> events;
}
