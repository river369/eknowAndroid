package eknow.com.eknow.utils;

/**
 * Created by jianguog on 16/12/11.
 */

import android.app.Application;
import android.content.Context;

import java.util.List;

public class ImageApplication extends Application {
    private static ImageApplication mInstance;
    private static Context mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        this.setAppContext(getApplicationContext());
    }

    public static ImageApplication getInstance(){
        return mInstance;
    }
    public static Context getAppContext() {
        return mAppContext;
    }
    public void setAppContext(Context mAppContext) {
        this.mAppContext = mAppContext;
    }
}