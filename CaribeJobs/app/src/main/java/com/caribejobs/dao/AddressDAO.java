package com.caribejobs.dao;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.caribejobs.UpdateAddressMutation;
import com.caribejobs.apidatabinding.GraphQLConnector;
import com.caribejobs.model.Address;
import com.caribejobs.model.UserLogin;
import com.caribejobs.type.AddressInput;

import org.jetbrains.annotations.NotNull;

public class AddressDAO {
    private final String TAG = getClass().getSimpleName();
    private final int TIME_OUT = 10000;

    public void execUpdateUserAddress(Address address) {
        GraphQLConnector.setApolloClient().mutate(
                UpdateAddressMutation.builder()
                        .address(AddressInput.
                                builder()
                        .username(UserLogin.getUserLogged().getUsername())
                        .provincia(address.getProvincia())
                                .canton(address.getCanton())
                                .distrito(address.getDistrito()
                                ).build()).build()
        ).enqueue(new ApolloCall.Callback<UpdateAddressMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<UpdateAddressMutation.Data> response) {
                System.out.println("Correct");
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                System.out.println("False");
            }
        });
    }
}
