package com.demo.meetingplanner.service;

import com.demo.meetingplanner.model.Event;
import com.demo.meetingplanner.model.Plan;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventPlannerService {

    private final static int MAX_PLAN_LIMIT = 10;
    private final PlanService planService;

    public List<Plan> schedule(List<Event> events) {
        if(CollectionUtils.isEmpty(events)) {
            return Collections.emptyList();
        }

        List<Plan> plans = new ArrayList<>();

        int planCount = 0;
        int plannedEventCount = 0;
        do {
            Plan plan = planService.create(events);
            planCount++;

            plans.add(plan);
            plannedEventCount += plan.getEvents().size();
        } while (events.size() > plannedEventCount && planCount < MAX_PLAN_LIMIT);

        return plans;
    }


}
