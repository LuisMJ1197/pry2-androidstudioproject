package com.caribejobs.dao;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.caribejobs.AddUserWorkZoneMutation;
import com.caribejobs.apidatabinding.GraphQLConnector;
import com.caribejobs.model.UserLogin;
import com.caribejobs.model.WorkZone;
import com.caribejobs.type.WorkZoneInput;

import org.jetbrains.annotations.NotNull;

public class WorkZoneDAO {

    public void saveWorkZone(WorkZone workZone) {
        GraphQLConnector.setApolloClient().mutate(
                AddUserWorkZoneMutation.builder()
                        .workZone(WorkZoneInput.builder()
                                .username(UserLogin.getUserLogged().getUsername())
                                .provincia(workZone.getProvincia())
                                .canton(workZone.getCanton())
                                .build())
                        .build()
        ).enqueue(new ApolloCall.Callback<AddUserWorkZoneMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<AddUserWorkZoneMutation.Data> response) {

            }

            @Override
            public void onFailure(@NotNull ApolloException e) {

            }
        });
    }
}
