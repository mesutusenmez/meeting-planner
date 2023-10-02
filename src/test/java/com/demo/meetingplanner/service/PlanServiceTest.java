package com.demo.meetingplanner.service;

import com.demo.meetingplanner.enums.EventType;
import com.demo.meetingplanner.enums.ScheduleType;
import com.demo.meetingplanner.model.Event;
import com.demo.meetingplanner.model.Plan;
import com.demo.meetingplanner.properties.ScheduleProperties;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PlanServiceTest {

    private PlanService planService;

    @Mock
    ScheduleProperties scheduleProperties;

    @Test
    public void test_WhenFirstComeScheduling() {
        when(scheduleProperties.getType()).thenReturn(ScheduleType.FIRST_COME);
        planService = new PlanService(new EventService(), scheduleProperties);
        Plan plan =  planService.create(getEvents());
        assertNotNull(plan);
        assertNotNull(plan.getEvents());
        assertNotNull(plan.getPlannedDate());
        assertEquals(9, plan.getEvents().size());
        assertEquals(false, plan.getEvents().stream().anyMatch(e -> Objects.isNull(e.getStartDate())));
        assertEquals(LocalDate.now().atTime(9, 0), plan.getEvents().stream().findFirst().get().getStartDate());
        assertEquals(EventType.LUNCH, plan.getEvents().stream().filter(e -> e.getStartDate().equals(LocalDate.now().atTime(12, 0))).findFirst().get().getType());
        assertEquals(false, plan.getEvents().stream().anyMatch(e -> e.getStartDate().isAfter(LocalDate.now().atTime(17, 0))));
        System.out.println("-- First Come Scheduling --");
        plan.getEvents().stream().forEach(e -> System.out.println(e));
    }


    @Test
    public void test_WhenRemainingTimeScheduling() {
        when(scheduleProperties.getType()).thenReturn(ScheduleType.REMAINING_TIME);
        planService = new PlanService(new EventService(), scheduleProperties);
        Plan plan =  planService.create(getEvents());
        assertNotNull(plan);
        assertNotNull(plan.getEvents());
        assertNotNull(plan.getPlannedDate());
        assertEquals(9, plan.getEvents().size());
        assertEquals(false, plan.getEvents().stream().anyMatch(e -> Objects.isNull(e.getStartDate())));
        assertEquals(LocalDate.now().atTime(9, 0), plan.getEvents().stream().findFirst().get().getStartDate());
        assertEquals(EventType.LUNCH, plan.getEvents().stream().filter(e -> e.getStartDate().equals(LocalDate.now().atTime(12, 0))).findFirst().get().getType());
        assertEquals(false, plan.getEvents().stream().anyMatch(e -> e.getStartDate().isAfter(LocalDate.now().atTime(17, 0))));
        System.out.println("-- Remaining Time Scheduling --");
        plan.getEvents().stream().forEach(e -> System.out.println(e));
    }

    private List<Event> getEvents() {
        List<Event> events = new ArrayList<>();
        Event event1 = Event.builder().name("Event 1").durationMinute(75).build();
        Event event2 = Event.builder().name("Event 2").durationMinute(60).type(EventType.PRESENTATION).build();
        Event event3 = Event.builder().name("Event 3").durationMinute(70).build();
        Event event4 = Event.builder().name("Event 4").type(EventType.LIGHTNING).build();
        Event event5 = Event.builder().name("Event 5").durationMinute(75).build();
        Event event6 = Event.builder().name("Event 6").durationMinute(45).build();
        Event event7 = Event.builder().name("Event 7").durationMinute(60).build();
        Event event8 = Event.builder().name("Event 8").durationMinute(60).build();
        Event event9 = Event.builder().name("Event 9").durationMinute(45).build();
        events.addAll(Arrays.asList(event1, event2, event3, event4, event5, event6, event7, event8, event9));
        return events;
    }
}
