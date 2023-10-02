package com.demo.meetingplanner.service;

import com.demo.meetingplanner.enums.EventType;
import com.demo.meetingplanner.model.Event;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceTest {
    @InjectMocks
    private EventService eventService;

    @Test
    public void test() {
        List<Event> events = eventService.normalize(getEvents());
        assertNotNull(events);
        assertEquals(4, events.size());
        assertEquals(EventType.PRESENTATION, events.stream().filter(e -> e.getName().equals("Event 1")).findFirst().get().getType());
        assertEquals(5, events.stream().filter(e -> e.getName().equals("Event 3")).findFirst().get().getDurationMinute());
    }

    private List<Event> getEvents() {
        List<Event> events = new ArrayList<>();
        Event event1 = Event.builder().name("Event 1").durationMinute(75).build();
        Event event2 = Event.builder().name("Event 2").durationMinute(60).type(EventType.PRESENTATION).build();
        Event event3 = Event.builder().name("Event 3").durationMinute(60).type(EventType.LIGHTNING).build();
        Event event4 = Event.builder().name("Event 4").durationMinute(45).type(EventType.NETWORKING).build();
        events.addAll(Arrays.asList(event1, event2, event3, event4));
        return events;
    }
}
