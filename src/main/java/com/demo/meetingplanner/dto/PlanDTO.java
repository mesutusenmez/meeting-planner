package com.demo.meetingplanner.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PlanDTO {

    private List<EventResultDTO> events;
}
