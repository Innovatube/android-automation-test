package com.innovatube.boilerplate.injection.component;

import android.app.Application;
import android.content.Context;

import com.innovatube.boilerplate.data.GithubRepository;
import com.innovatube.boilerplate.injection.ApplicationContext;
import com.innovatube.boilerplate.injection.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by TOIDV on 4/4/2016.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();

    Application application();

    Retrofit retrofit();

    GithubRepository githubRepository();

    OkHttpClient okHttpClient();
}
