package com.innovatube.boilerplate.data.remote;


import com.innovatube.boilerplate.data.model.Repository;
import com.innovatube.boilerplate.data.model.User;

import java.util.List;

import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by quanlt on 27/12/2016.
 */


public class RemoteDataSourceImpl implements RemoteDataSource {
    private final GithubService mGithubService;

    public RemoteDataSourceImpl(GithubService mGithubService) {
        this.mGithubService = mGithubService;
    }


    @Override
    public Observable<User> getUser(@Path("username") String username) {
        return mGithubService.getUser(username);
    }

    @Override
    public Observable<List<Repository>> getUsersRepositories(@Path("username") String username) {
        return mGithubService.getUsersRepositories(username);
    }
}
