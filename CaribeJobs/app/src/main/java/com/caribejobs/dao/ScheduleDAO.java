package com.caribejobs.dao;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.caribejobs.ClearUserAvailableScheduleMutation;
import com.caribejobs.LoginQuery;
import com.caribejobs.UpdateScheduleMutation;
import com.caribejobs.apidatabinding.GraphQLConnector;
import com.caribejobs.model.DaySchedule;
import com.caribejobs.model.Schedule;
import com.caribejobs.model.UserLogin;
import com.caribejobs.type.AvailableScheduleInput;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ScheduleDAO {
    public void saveSchedule(final Schedule schedule) {
        System.out.println(schedule.toString());
        GraphQLConnector.setApolloClient().mutate(
                ClearUserAvailableScheduleMutation.builder().username(UserLogin.getUserLogged().getUsername()).build()
        ).enqueue(new ApolloCall.Callback<ClearUserAvailableScheduleMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<ClearUserAvailableScheduleMutation.Data> response) {
                if (response.data() != null) {
                    if (response.data().clearUserAvailableScheudle().errorCode() == null) {
                        System.out.println("Here deleted");
                        saveScheduleAux(schedule);
                    }
                }
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {

            }
        });
    }

    public AvailableScheduleInput createDaySchedule(DaySchedule daySchedule) {
        AvailableScheduleInput availableScheduleInput = AvailableScheduleInput.builder()
                .day(daySchedule.getDayNumber())
                .startTime(daySchedule.getStartTime())
                .endTime(daySchedule.getEndTime())
                .build();
        return  availableScheduleInput;
    }

    public void saveScheduleAux(Schedule schedule) {
        ArrayList<AvailableScheduleInput> schedule1 = new ArrayList<>();
        if (schedule.isSet()) schedule1.add(createDaySchedule(schedule));
        if (schedule.getMonday().isSet()) schedule1.add(createDaySchedule(schedule.getMonday()));
        if (schedule.getTuesday().isSet()) schedule1.add(createDaySchedule(schedule.getTuesday()));
        if (schedule.getWednesday().isSet()) schedule1.add(createDaySchedule(schedule.getWednesday()));
        if (schedule.getThursday().isSet()) schedule1.add(createDaySchedule(schedule.getThursday()));
        if (schedule.getFriday().isSet()) schedule1.add(createDaySchedule(schedule.getFriday()));
        if (schedule.getSaturday().isSet()) schedule1.add(createDaySchedule(schedule.getSaturday()));
        if (schedule.getSunday().isSet()) schedule1.add(createDaySchedule(schedule.getSunday()));
        GraphQLConnector.setApolloClient().mutate(
                UpdateScheduleMutation.builder()
                        .username(UserLogin.getUserLogged().getUsername())
                        .schedule(
                                schedule1
                        )
                        .build()
        ).enqueue(new ApolloCall.Callback<UpdateScheduleMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<UpdateScheduleMutation.Data> response) {
                System.out.println();
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {

            }
        });
    }
}
