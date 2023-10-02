package com.demo.meetingplanner.response;

import com.demo.meetingplanner.dto.PlanDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class PlanResponse {
    private List<PlanDTO> plans;
}
