package com.demo.meetingplanner.factory;

import com.demo.meetingplanner.model.Event;
import com.demo.meetingplanner.enums.EventType;

import java.time.LocalDate;

public final class EventFactory {

    public static Event createLunchEvent() {
        return Event.builder().name("Lunch")
                .type(EventType.LUNCH)
                .durationMinute(60)
                .startDate(LocalDate.now().atTime(12, 0))
                .build();
    }

    public static Event createNetworkingEvent() {
        return Event.builder().name("Networking")
                .type(EventType.NETWORKING)
                .build();
    }
}
