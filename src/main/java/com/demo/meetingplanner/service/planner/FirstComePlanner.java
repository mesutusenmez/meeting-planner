package com.demo.meetingplanner.service.planner;

import com.demo.meetingplanner.model.Event;
import com.demo.meetingplanner.model.Plan;

import java.time.LocalDateTime;
import java.util.List;

public class FirstComePlanner implements Planner {

    public void scheduleEvents(Plan plan, List<Event> events, LocalDateTime startDate, LocalDateTime endDate) {
        plan.setPlannedDate(startDate);
        events.stream().filter(e -> e.getStartDate() == null).forEach(e -> {
            if (plan.getPlannedDate().isBefore(endDate) && plan.getPlannedDate().plusMinutes(e.getDurationMinute()).isBefore(endDate))
                scheduleEvent(plan, e);
        });
    }

}
