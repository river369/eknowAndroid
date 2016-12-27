package eknow.com.eknow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import eknow.com.eknow.home.HomeFragment;
import eknow.com.eknow.service.ServiceBuyFragment;
import eknow.com.eknow.service.ServiceDetailsFragment;
import eknow.com.eknow.service.ServicesListFragment;

/**
 * Created by jianguog on 16/12/16.
 */

public class FragmentsFactory {

    static HomeFragment home;
    static ServicesListFragment slf;
    static ServiceDetailsFragment sdf;
    static ServiceBuyFragment sbf;

    private static FragmentsFactory mInstance = null;

    private FragmentsFactory(){

    }

    public static FragmentsFactory getInstance(){
        if(mInstance == null){
            mInstance = new FragmentsFactory();
        }
        return mInstance;
    }

    public static void setFragment(FragmentActivity activity,  int containerViewId,
                                   Fragment oldFragment, Fragment newFragment, Bundle bundle){
        setFragment(activity,  containerViewId, oldFragment, newFragment,  bundle, true);
    }
    public static void setFragment(FragmentActivity activity,  int containerViewId,
                                   Fragment oldFragment, Fragment newFragment, Bundle bundle,
                                   boolean reAdd){
        FragmentManager fm = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        if (bundle != null) {
            newFragment.setArguments(bundle);
        }

        if (null != oldFragment){
            transaction.hide(oldFragment);
        }
        if (reAdd) {
            transaction.add(containerViewId, newFragment);
        }
        if (null != oldFragment){
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    public static void showFragment(FragmentActivity activity,Fragment newFragment){
        FragmentManager fm = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.show(newFragment);
        transaction.commit();
    }

    public static HomeFragment setMainFragment(FragmentActivity activity){
        if (home == null){
            home =  new HomeFragment();
        }
        setFragment(activity, R.id.id_eknow_main_content, null, home, null);
        return home;
    }

    public static void setServicesListFragment(FragmentActivity activity, Fragment oldFragment, Bundle bundle){
        if (slf == null){
            slf = new ServicesListFragment();
        }
        setFragment(activity, R.id.id_eknow_service_content, oldFragment, slf , bundle);
    }

    public static void setServiceDetailsFragment(FragmentActivity activity, Fragment oldFragment, Bundle bundle){
        //if (sdf == null){
        sdf = new ServiceDetailsFragment();
        //}
        setFragment(activity, R.id.id_eknow_service_content, oldFragment, sdf, bundle);
    }

    public static void setServiceBuyFragment(FragmentActivity activity, Fragment oldFragment, Bundle bundle){
        if (sbf == null){
            sbf = new ServiceBuyFragment();
        }
        setFragment(activity, R.id.id_eknow_service_content, oldFragment, sbf, bundle);
    }


}
