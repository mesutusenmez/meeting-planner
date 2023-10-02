package com.demo.meetingplanner.factory;

import com.demo.meetingplanner.model.Plan;

import java.util.ArrayList;

public final class PlanFactory {

    public static Plan emptyPlan() {
        return Plan.builder().events(new ArrayList<>()).build();
    }
}
