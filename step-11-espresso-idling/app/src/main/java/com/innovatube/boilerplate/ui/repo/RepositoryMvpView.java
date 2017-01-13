package com.innovatube.boilerplate.ui.repo;

import com.innovatube.boilerplate.data.model.Repository;
import com.innovatube.boilerplate.ui.base.BaseMvpView;

import java.util.List;

/**
 * Created by quanlt on 11/01/2017.
 */

public interface RepositoryMvpView extends BaseMvpView {
    void showRepos(List<Repository> repositories);
}
