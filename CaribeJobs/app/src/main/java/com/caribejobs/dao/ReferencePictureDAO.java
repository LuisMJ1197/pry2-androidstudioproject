package com.caribejobs.dao;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.caribejobs.AddReferencePictureMutation;
import com.caribejobs.apidatabinding.GraphQLConnector;
import com.caribejobs.model.ReferencePicture;
import com.caribejobs.model.UserLogin;
import com.caribejobs.type.ReferencePictureInput;

import org.jetbrains.annotations.NotNull;

public class ReferencePictureDAO {

    public void saveReferencePicture(ReferencePicture referencePicture, int professionid) {
        GraphQLConnector.setApolloClient().mutate(
                AddReferencePictureMutation.builder()
                        .referencePicture(
                                ReferencePictureInput.builder()
                                        .imageURL(referencePicture.getImageURL())
                                        .professionid(professionid)
                                        .username(UserLogin.getUserLogged().getUsername())
                                        .build()
                        )
                        .build()
        ).enqueue(new ApolloCall.Callback<AddReferencePictureMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<AddReferencePictureMutation.Data> response) {
                
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {

            }
        });
    }
}
