package com.innovatube.boilerplate.ui.repo;

import com.innovatube.boilerplate.data.GithubRepository;
import com.innovatube.boilerplate.data.model.Repository;
import com.innovatube.boilerplate.ui.base.BasePresenter;
import com.innovatube.boilerplate.utils.InnovatubeUtils;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by quanlt on 11/01/2017.
 */

public class RepositoryPresenter extends BasePresenter<RepositoryMvpView> {
    private GithubRepository mGithubRepository;
    private Retrofit mRetrofit;

    @Inject
    public RepositoryPresenter(GithubRepository mGithubRepository, Retrofit mRetrofit) {
        this.mGithubRepository = mGithubRepository;
        this.mRetrofit = mRetrofit;
    }

    public void getUsersRepositories(String username) {
        getMvpView().showProgressDialog(true);
        mGithubRepository.getUsersRepositories(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Repository>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().showProgressDialog(false);
                        getMvpView().showAlertDialog(InnovatubeUtils.getError(e, mRetrofit));
                    }

                    @Override
                    public void onNext(List<Repository> repositories) {
                        getMvpView().showProgressDialog(false);
                        getMvpView().showRepos(repositories);
                    }
                });
    }
}
