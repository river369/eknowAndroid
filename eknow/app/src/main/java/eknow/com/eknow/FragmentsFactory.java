package eknow.com.eknow;

import android.app.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

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

    public static void setFragment(FragmentActivity activity, Fragment oldFragment, Fragment newFragment, Bundle bundle){
        FragmentManager fm = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        if (bundle != null) {
            newFragment.setArguments(bundle);
        }

        if (null != oldFragment){
            transaction.hide(oldFragment);
        }
        transaction.add(R.id.id_eknow_main_content, newFragment);
        if (null != oldFragment){
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }

    public static void setMainFragment(FragmentActivity activity){
        if (main == null){
            main =  new MainFragment();
        }
        setFragment(activity, null, main, null);
    }

    public static void setServicesListFragment(FragmentActivity activity, Fragment oldFragment, Bundle bundle){
        if (slf == null){
            slf = new ServicesListFragment();
        }
        setFragment(activity, oldFragment, slf , bundle);
    }

    public static void setServiceDetailsFragment(FragmentActivity activity, Fragment oldFragment, Bundle bundle){
        if (sdf == null){
            sdf = new ServiceDetailsFragment();
        }
        setFragment(activity, oldFragment, sdf, bundle);
    }


}
