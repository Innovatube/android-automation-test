package com.innovatube.boilerplate;

import android.app.Application;
import android.content.Context;
import android.support.annotation.VisibleForTesting;

import com.innovatube.boilerplate.injection.component.ApplicationComponent;
import com.innovatube.boilerplate.injection.component.DaggerApplicationComponent;
import com.innovatube.boilerplate.injection.module.ApplicationModule;

/**
 * Created by TOIDV on 4/4/2016.
 */
public class InnovatubeApplication extends Application {
    private ApplicationComponent mApplicationComponent;

    public static InnovatubeApplication get(Context context) {
        return (InnovatubeApplication) context.getApplicationContext();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public synchronized ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;

    }

}
