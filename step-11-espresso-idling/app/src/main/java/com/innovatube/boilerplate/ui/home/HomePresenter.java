package com.innovatube.boilerplate.ui.home;

import com.innovatube.boilerplate.data.GithubRepository;
import com.innovatube.boilerplate.data.model.User;
import com.innovatube.boilerplate.ui.base.BasePresenter;
import com.innovatube.boilerplate.utils.InnovatubeUtils;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by quanlt on 11/01/2017.
 */

public class HomePresenter extends BasePresenter<HomeMvpView> {
    private GithubRepository mGithubRepository;
    private Subscription mSubscription;
    private Retrofit mRetrofit;

    @Inject
    public HomePresenter(GithubRepository mGithubRepository, Retrofit mRetrofit) {
        this.mGithubRepository = mGithubRepository;
        this.mRetrofit = mRetrofit;
    }

    public void getUser(String username) {
        getMvpView().showProgressDialog(true);
        //EspressoIdlingResource.increment();
        mSubscription = mGithubRepository.getUser(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        //EspressoIdlingResource.decrement();
                        getMvpView().showProgressDialog(false);
                        getMvpView().showAlertDialog(InnovatubeUtils.getError(e, mRetrofit));
                    }

                    @Override
                    public void onNext(User user) {
                        //EspressoIdlingResource.decrement();
                        getMvpView().showProgressDialog(false);
                        getMvpView().showUser(user);
                    }
                });
    }

    public void viewRepository(String username) {
        getMvpView().viewRepository(username);
    }

    @Override
    public void detachView() {
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
        super.detachView();
    }


}
