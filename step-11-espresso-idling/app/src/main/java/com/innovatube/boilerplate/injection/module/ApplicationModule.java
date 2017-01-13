package com.innovatube.boilerplate.injection.module;

import android.app.Application;
import android.content.Context;

import com.innovatube.boilerplate.data.GithubRepository;
import com.innovatube.boilerplate.data.GithubRepositoryImpl;
import com.innovatube.boilerplate.data.remote.GithubService;
import com.innovatube.boilerplate.data.remote.RemoteDataSource;
import com.innovatube.boilerplate.data.remote.RemoteDataSourceImpl;
import com.innovatube.boilerplate.injection.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * Created by TOIDV on 4/4/2016.
 */

@Module
public class ApplicationModule {
    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    protected Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    protected Context provideApplicationContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    protected GithubService provideInnovatubeService(Retrofit retrofit) {
        return retrofit.create(GithubService.class);
    }

    @Provides
    @Singleton
    protected OkHttpClient okHttpClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

    @Provides
    @Singleton
    protected Retrofit provideRetrofitInstance(OkHttpClient okHttpClient) {
        return GithubService.Creator.newRetrofitInstance(okHttpClient);
    }

    @Provides
    @Singleton
    protected RemoteDataSource provideRemoteDataSource(GithubService githubService) {
        return new RemoteDataSourceImpl(githubService);
    }

    @Provides
    @Singleton
    protected GithubRepository provideInnovatubeRepository(RemoteDataSource remoteDataSource) {
        return new GithubRepositoryImpl(remoteDataSource);
    }
}
