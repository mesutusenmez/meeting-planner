package com.demo.meetingplanner.service;

import com.demo.meetingplanner.model.Event;
import com.demo.meetingplanner.enums.EventType;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@Service
public class EventService {

    public List<Event> normalize(List<Event> events) {
        if (CollectionUtils.isEmpty(events)) {
            return Collections.emptyList();
        }

        List<Event> presentationEvents = events.stream().filter(e -> EventType.NETWORKING != e.getType() && EventType.LIGHTNING != e.getType()).toList();
        List<Event> lightningEvents = events.stream().filter(e -> EventType.LIGHTNING == e.getType()).toList();
        List<Event> networkingEvents = events.stream().filter(e -> EventType.NETWORKING == e.getType()).toList();

        //set type of presentation events
        presentationEvents.forEach(p -> {
            p.setType(EventType.PRESENTATION);
        });

        lightningEvents.forEach(l -> {
            l.setDurationMinute(5);
        });

        return Stream.of(presentationEvents, lightningEvents, networkingEvents).flatMap(Collection::stream).toList();
    }


}
