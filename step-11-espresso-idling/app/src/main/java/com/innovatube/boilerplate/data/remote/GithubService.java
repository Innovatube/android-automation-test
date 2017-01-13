package com.innovatube.boilerplate.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.innovatube.boilerplate.data.model.Repository;
import com.innovatube.boilerplate.data.model.User;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by TOIDV on 4/4/2016.
 */
public interface GithubService {

    @GET("/users/{username}")
    Observable<User> getUser(
            @Path("username") String username
    );

    @GET("/users/{username}/repos")
    Observable<List<Repository>> getUsersRepositories(
            @Path("username") String username
    );

    class Creator {
        private static final String ENDPOINT = "https://api.github.com/";

        public static Retrofit newRetrofitInstance(OkHttpClient okHttpClient) {

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:SSS'Z'")
                    .create();

            return new Retrofit.Builder()
                    .baseUrl(ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();

        }

    }


}
