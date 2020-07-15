package com.caribejobs.dao;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.caribejobs.GetDireccionesQuery;
import com.caribejobs.apidatabinding.GraphQLConnector;
import com.caribejobs.apidatabinding.ModelClassBuilder;
import com.caribejobs.model.UserLogin;
import com.caribejobs.model.address.Utility;

import org.jetbrains.annotations.NotNull;

public class AddressesUtilDAO {

    public void loadAddresses() {
        GraphQLConnector.setApolloClient().query(
                GetDireccionesQuery.builder().build()
        ).enqueue(new ApolloCall.Callback<GetDireccionesQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<GetDireccionesQuery.Data> response) {
                if (response.data() != null) {
                    if (response.data().getDirecciones() != null) {
                        Utility.provincias = ModelClassBuilder.createDirecciones(response.data().getDirecciones());
                    }
                }
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {

            }
        });
    }
}
