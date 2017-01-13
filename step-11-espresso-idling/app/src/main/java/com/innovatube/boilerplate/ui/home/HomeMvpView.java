package com.innovatube.boilerplate.ui.home;

import com.innovatube.boilerplate.data.model.User;
import com.innovatube.boilerplate.ui.base.BaseMvpView;

/**
 * Created by quanlt on 11/01/2017.
 */

public interface HomeMvpView extends BaseMvpView {
    void viewRepository(String username);
    void showUser(User user);
}
