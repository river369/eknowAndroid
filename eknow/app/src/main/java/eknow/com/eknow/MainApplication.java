package eknow.com.eknow;

/**
 * Created by jianguog on 16/12/11.
 */

import android.app.Application;
import android.content.Context;

public class MainApplication extends Application {
    private static MainApplication mInstance;
    private static Context mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        this.setAppContext(getApplicationContext());
    }

    public static MainApplication getInstance(){
        return mInstance;
    }
    public static Context getAppContext() {
        return mAppContext;
    }
    public void setAppContext(Context mAppContext) {
        this.mAppContext = mAppContext;
    }
}