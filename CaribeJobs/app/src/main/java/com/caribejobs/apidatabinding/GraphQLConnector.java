package com.caribejobs.apidatabinding;
import androidx.appcompat.app.AppCompatActivity;
import com.caribejobs.R;
import com.apollographql.apollo.ApolloClient;
import okhttp3.OkHttpClient;


public class GraphQLConnector {
    private static final String BASE_URL = "http://192.168.0.4:4000/caribejobs";

    public GraphQLConnector() {

    }

    public static ApolloClient setApolloClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        return ApolloClient.builder().serverUrl(BASE_URL).okHttpClient(okHttpClient).build();
    }
}
