package com.demo.meetingplanner.service;

import com.demo.meetingplanner.exceptions.EmptyEventException;
import com.demo.meetingplanner.exceptions.InvalidSchedulePlanException;
import com.demo.meetingplanner.factory.EventFactory;
import com.demo.meetingplanner.factory.PlanFactory;
import com.demo.meetingplanner.model.Event;
import com.demo.meetingplanner.properties.ScheduleProperties;
import com.demo.meetingplanner.enums.EventType;
import com.demo.meetingplanner.model.Plan;
import com.demo.meetingplanner.service.planner.FirstComePlanner;
import com.demo.meetingplanner.service.planner.Planner;
import com.demo.meetingplanner.service.planner.RemainingTimePlanner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
public class PlanService  {
    private final EventService eventService;

    private final ScheduleProperties scheduleProperties;

    private Planner planner;

    public PlanService(EventService eventService, ScheduleProperties scheduleProperties) {
        this.eventService = eventService;
        this.scheduleProperties = scheduleProperties;
        switch (scheduleProperties.getType()) {
            case FIRST_COME -> this.planner = new FirstComePlanner();
            case REMAINING_TIME -> this.planner = new RemainingTimePlanner();
            default -> throw new InvalidSchedulePlanException("Invalid schedule type!");
        }
    }

    public Plan create(List<Event> events) {
        //validate events
        validateEvents(events);

        //create a plan
        Plan plan = PlanFactory.emptyPlan();

        //create a lunch event
        Event lunch = EventFactory.createLunchEvent();

        //create a networking event
        Event networking = EventFactory.createNetworkingEvent();

        // set missing fields
        events = eventService.normalize(events);

        log.info("{} scheduling algorithm is running.", scheduleProperties.getType());

        //schedule events
        planner.scheduleEvents(plan, events, LocalDate.now().atTime(9, 0), LocalDate.now().atTime(12, 0));
        planner.scheduleEvent(plan, lunch, LocalDate.now().atTime(12, 0));
        planner.scheduleEvents(plan, events, LocalDate.now().atTime(13, 0), LocalDate.now().atTime(17, 0));
        planner.scheduleNetworkingEvent(plan, networking, LocalDate.now().atTime(16, 0), LocalDate.now().atTime(17, 0));

        //sort by start date
        plan.setEvents(plan.getEvents().stream().sorted(Comparator.comparing(Event::getStartDate, Comparator.naturalOrder())).toList());

        return plan;
    }

    private void validateEvents(List<Event> events) {
        if (CollectionUtils.isEmpty(events)) {
            throw new EmptyEventException("Events must not be empty!");
        }
        if(events.stream().anyMatch(e-> EventType.LUNCH == e.getType())) {
            throw new IllegalArgumentException("Do not support LUNCH event type!");
        }
    }

}
