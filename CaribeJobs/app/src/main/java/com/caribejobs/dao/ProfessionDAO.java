package com.caribejobs.dao;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.caribejobs.AddUserProfessionMutation;
import com.caribejobs.GetProfessionsQuery;
import com.caribejobs.apidatabinding.GraphQLConnector;
import com.caribejobs.apidatabinding.ModelClassBuilder;
import com.caribejobs.model.Profession;
import com.caribejobs.model.UserLogin;
import com.caribejobs.model.UserProfession;
import com.caribejobs.type.UserProfessionInput;

import org.jetbrains.annotations.NotNull;

public class ProfessionDAO {

    public void saveProfession(UserProfession profession) {
        GraphQLConnector.setApolloClient().mutate(
                AddUserProfessionMutation.builder().professionInput(
                        UserProfessionInput.builder()
                                .username(UserLogin.getUserLogged().getUsername())
                                .professionid(profession.getProfession().getProfessionid())
                                .experienceyears(profession.getExperienceyears())
                                .details(profession.getDetails())
                                .costperhour(profession.isaNegociar() ? "A negociar" : Double.toString(profession.getCostperhour()))
                                .build()
                ).build()
        ).enqueue(new ApolloCall.Callback<AddUserProfessionMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<AddUserProfessionMutation.Data> response) {

            }

            @Override
            public void onFailure(@NotNull ApolloException e) {

            }
        });
    }

    public void loadProfessions() {
        GraphQLConnector.setApolloClient().query(
                GetProfessionsQuery.builder()
                .build()
        ).enqueue(new ApolloCall.Callback<GetProfessionsQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<GetProfessionsQuery.Data> response) {
                if (response != null && response.data() != null)
                    Profession.professionsUtil = ModelClassBuilder.createProfessions(response.data().getProfessions());
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {

            }
        });
    }
}
