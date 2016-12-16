package eknow.com.eknow;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import eknow.com.eknow.main.MainFragment;
import eknow.com.eknow.service.ServiceDetailsFragment;
import eknow.com.eknow.service.ServicesListFragment;

/**
 * Created by jianguog on 16/12/16.
 */

public class FragmentsFactory {

    static MainFragment main;
    static ServicesListFragment slf;
    static ServiceDetailsFragment sdf;

    private static FragmentsFactory mInstance = null;

    private FragmentsFactory(){

    }

    public static FragmentsFactory getInstance(){
        if(mInstance == null){
            mInstance = new FragmentsFactory();
        }
        return mInstance;
    }

    public static void setFragment(Activity activity, Fragment fragment, Bundle bundle){
        FragmentManager fm = activity.getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        transaction.replace(R.id.id_eknow_main_content, fragment);
        transaction.commit();
    }

    public static void setMainFragment(Activity activity){
        main =  new MainFragment();
        setFragment(activity, main, null);
    }

    public static void setServicesListFragment(Activity activity, Bundle bundle){
        slf = new ServicesListFragment();
        setFragment(activity, slf , bundle);
    }

    public static void setServiceDetailsFragment(Activity activity, Bundle bundle){
        sdf = new ServiceDetailsFragment();
        setFragment(activity, sdf, bundle);
    }


}
