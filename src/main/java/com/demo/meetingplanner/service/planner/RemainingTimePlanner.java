package com.demo.meetingplanner.service.planner;

import com.demo.meetingplanner.model.Event;
import com.demo.meetingplanner.model.Plan;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class RemainingTimePlanner implements Planner {

    @Override
    public void scheduleEvents(Plan plan, List<Event> events, LocalDateTime startDate, LocalDateTime endDate) {
        plan.setPlannedDate(startDate);

        AtomicLong remainingMinutes = new AtomicLong(ChronoUnit.MINUTES.between(startDate, endDate));

        List<Event> plannedEvents = new ArrayList<>();

        List<Event> nonPlannedEvents = new ArrayList<>(events.stream().filter(e -> e.getStartDate() == null).toList());

        nonPlannedEvents.forEach(e -> {
            if (e.getDurationMinute() < remainingMinutes.get()) {
                plannedEvents.add(e);
                remainingMinutes.addAndGet(-e.getDurationMinute());
            }
        });

        List<Event> lastPlannedEvents = reSchedule(nonPlannedEvents.stream()
                        .filter(n -> !plannedEvents.contains(n)).toList(),
                plannedEvents,
                remainingMinutes.get());

        lastPlannedEvents.stream().forEach(l -> {
            l.setStartDate(plan.getPlannedDate());
            plan.getEvents().add(l);
            plan.setPlannedDate(plan.getPlannedDate().plusMinutes(l.getDurationMinute()));
        });
    }

    private List<Event> reSchedule(List<Event> nonPlannedEvents, List<Event> plannedEvents, long remainingMinutes) {

        AtomicReference<Event> removedEvent = new AtomicReference<>();
        AtomicReference<Event> addedEvent = new AtomicReference<>();

        if (remainingMinutes > 0) {
            plannedEvents.forEach(p -> {
                nonPlannedEvents.forEach(n -> {
                    if (n.getDurationMinute() > p.getDurationMinute() && n.getDurationMinute() - p.getDurationMinute() < remainingMinutes) {
                        removedEvent.set(p);
                        addedEvent.set(n);
                    }
                });
            });
        }

        if (Objects.nonNull(removedEvent.get()) && Objects.nonNull(addedEvent.get())) {
            plannedEvents.remove(removedEvent.get());
            plannedEvents.add(addedEvent.get());
        }
        return plannedEvents;
    }

}
