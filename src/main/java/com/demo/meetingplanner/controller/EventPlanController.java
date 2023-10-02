package com.demo.meetingplanner.controller;

import com.demo.meetingplanner.mapper.EventMapper;
import com.demo.meetingplanner.mapper.PlanMapper;
import com.demo.meetingplanner.service.EventPlannerService;
import com.demo.meetingplanner.model.Plan;
import com.demo.meetingplanner.request.EventRequest;
import com.demo.meetingplanner.response.PlanResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/event-plan")
public class EventPlanController {

    private final EventPlannerService eventPlannerService;

    @PostMapping("/schedule")
    ResponseEntity<PlanResponse> schedule(@RequestBody EventRequest eventRequest) {
        List<Plan> plans =  eventPlannerService.schedule(EventMapper.mapToEventList(eventRequest));
        return ResponseEntity.ok(PlanMapper.mapToPlanResponse(plans));
    }

}
