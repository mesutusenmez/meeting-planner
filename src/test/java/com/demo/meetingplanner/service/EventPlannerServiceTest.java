package com.demo.meetingplanner.service;


import com.demo.meetingplanner.enums.EventType;
import com.demo.meetingplanner.model.Event;
import com.demo.meetingplanner.model.Plan;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EventPlannerServiceTest {

    @InjectMocks
    private EventPlannerService eventPlannerService;

    @Mock
    private PlanService planService;

    @Before
    public void setUp() {
        when(planService.create(anyList())).thenReturn(getPlan());
    }

    @Test
    public void test_WhenPlanCountMax() {
        List<Plan> plans = eventPlannerService.schedule(getAllEvents());
        assertEquals(10, plans.size());
    }

    @Test
    public void test_WhenNoPlan() {
        List<Plan> plans = eventPlannerService.schedule(new ArrayList<>());
        assertEquals(0, plans.size());
    }

    private Plan getPlan() {
        return Plan.builder().events(getPlanEvents()).build();
    }

    private List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            Event event = Event.builder().name("Event n").durationMinute(30).type(EventType.PRESENTATION).build();
            events.add(event);
        }
        return events;
    }

    private List<Event> getPlanEvents() {
        List<Event> events = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Event event = Event.builder().name("Event n").durationMinute(30).type(EventType.PRESENTATION).build();
            events.add(event);
        }
        return events;
    }

}
