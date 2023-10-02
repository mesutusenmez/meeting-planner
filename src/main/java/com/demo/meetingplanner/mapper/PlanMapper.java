package com.demo.meetingplanner.mapper;

import com.demo.meetingplanner.dto.PlanDTO;
import com.demo.meetingplanner.model.Plan;
import com.demo.meetingplanner.response.PlanResponse;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public final class PlanMapper {

    public static PlanResponse mapToPlanResponse(List<Plan> plans) {
        if(CollectionUtils.isEmpty(plans)) {
            PlanResponse.builder().plans(new ArrayList<>()).build();
        }

        List<PlanDTO> planDTOs = new ArrayList<>();

        plans.forEach(p -> {
            planDTOs.add(PlanDTO.builder().events(p.getEvents().stream().map(e -> EventMapper.mapToEventResultDTO(e)).toList()).build());
        });

        return PlanResponse.builder().plans(planDTOs).build();
    }


}
