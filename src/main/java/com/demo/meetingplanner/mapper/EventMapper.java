package com.demo.meetingplanner.mapper;

import com.demo.meetingplanner.dto.EventDTO;
import com.demo.meetingplanner.dto.EventResultDTO;
import com.demo.meetingplanner.enums.EventType;
import com.demo.meetingplanner.enums.EventTypeInput;
import com.demo.meetingplanner.model.Event;
import com.demo.meetingplanner.request.EventRequest;
import com.demo.meetingplanner.response.PlanResponse;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class EventMapper {

    public static List<Event> mapToEventList(EventRequest request) {
        List<Event> events = new ArrayList<>();
        if(Objects.isNull(request) || CollectionUtils.isEmpty(request.getEvents())) {
            return events;
        }
        request.getEvents().forEach(e -> {
            Event.EventBuilder eventBuilder = Event.builder().name(e.getName());
            if(Objects.nonNull(e.getEventType())) {
                eventBuilder.type(EventType.valueOf(e.getEventType().name()));
            }
            if(Objects.nonNull(e.getDuration())) {
                eventBuilder.durationMinute(e.getDuration());
            }
            events.add(eventBuilder.build());
        });
        return events;
    }

    public static EventResultDTO mapToEventResultDTO(Event event) {
        if(Objects.isNull(event)) {
            return null;
        }

        return EventResultDTO.builder().eventType(event.getType())
                .name(event.getName())
                .duration(event.getDurationMinute())
                .startDate(event.getStartDate()).build();
    }


}
