package com.demo.meetingplanner.service.planner;

import com.demo.meetingplanner.enums.EventType;
import com.demo.meetingplanner.enums.ScheduleType;
import com.demo.meetingplanner.model.Event;
import com.demo.meetingplanner.model.Plan;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public interface Planner {

    default void scheduleEvent(Plan plan, Event event) {
        event.setStartDate(plan.getPlannedDate());
        plan.getEvents().add(event);
        plan.setPlannedDate(plan.getPlannedDate().plusMinutes(event.getDurationMinute()));
    }

    default void scheduleNetworkingEvent(Plan plan, Event event, LocalDateTime endDate) {
        int remainingMinutes =  (int) ChronoUnit.MINUTES.between(plan.getPlannedDate(), endDate);

        if(EventType.NETWORKING == event.getType() && remainingMinutes > 0) {
            event.setStartDate(plan.getPlannedDate());
            event.setDurationMinute(remainingMinutes);
            plan.getEvents().add(event);
            plan.setPlannedDate(plan.getPlannedDate().plusMinutes(event.getDurationMinute()));
        }
    }

    default void scheduleEvent(Plan plan, Event event, LocalDateTime startDate) {
        event.setStartDate(startDate);
        plan.getEvents().add(event);
        plan.setPlannedDate(startDate.plusMinutes(event.getDurationMinute()));
    }

    public void scheduleEvents(Plan plan, List<Event> events, LocalDateTime startDate, LocalDateTime endDate);
}
