package com.caribejobs.dao;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.caribejobs.AddUserReferenceMutation;
import com.caribejobs.apidatabinding.GraphQLConnector;
import com.caribejobs.model.Reference;
import com.caribejobs.model.UserLogin;
import com.caribejobs.type.ReferenceInput;

import org.jetbrains.annotations.NotNull;

public class ReferenceDAO {

    public void saveReferences(final Reference reference) {
        GraphQLConnector.setApolloClient().mutate(
                AddUserReferenceMutation.builder()
                        .reference(ReferenceInput.builder()
                                .username(UserLogin.getUserLogged().getUsername())
                                .lastjob(reference.getLastJob())
                                .firstname(reference.getFirstname())
                                .lastname(reference.getLastname())
                                .lastname2(reference.getLastname2())
                                .phonenumber(reference.getPhonenumber())
                                .build())
                        .build()
        ).enqueue(new ApolloCall.Callback<AddUserReferenceMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<AddUserReferenceMutation.Data> response) {
                if (response.data() != null) {
                    response.data().addUserReference();
                    reference.setReferenceid(response.data().addUserReference().resultData());
                    System.out.println(response.data().addUserReference().errorCode());
                }
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {

            }
        });
    }
}
