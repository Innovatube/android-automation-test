package com.innovatube.boilerplate.data.remote;


import com.innovatube.boilerplate.data.model.Repository;
import com.innovatube.boilerplate.data.model.User;

import java.util.List;

import rx.Observable;

/**
 * Created by quanlt on 27/12/2016.
 */

public interface RemoteDataSource {
    Observable<User> getUser(String username);

    Observable<List<Repository>> getUsersRepositories(String username);
}
