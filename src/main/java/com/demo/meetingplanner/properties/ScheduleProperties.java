package com.demo.meetingplanner.properties;
import com.demo.meetingplanner.enums.ScheduleType;
import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Component
@ConfigurationProperties(prefix = "schedule")
public class ScheduleProperties {
    private ScheduleType type;

    public void setType(String type) {
        this.type = ScheduleType.valueOf(type);
    }
}
