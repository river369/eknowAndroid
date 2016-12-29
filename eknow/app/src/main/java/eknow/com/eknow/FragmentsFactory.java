package eknow.com.eknow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import eknow.com.eknow.common.BaseFragment;
import eknow.com.eknow.home.HomeFragment;
import eknow.com.eknow.service.ServiceBuyFragment;
import eknow.com.eknow.service.ServiceDetailsFragment;
import eknow.com.eknow.service.ServicesListFragment;
import eknow.com.eknow.user.SignInFragment;
import eknow.com.eknow.user.SignOnFragment;

/**
 * Created by jianguog on 16/12/16.
 */

public class FragmentsFactory {

    static HomeFragment home;
    static ServicesListFragment slf;
    static ServiceDetailsFragment sdf;
    static ServiceBuyFragment sbf;
    static SignInFragment signIn;
    static SignOnFragment signOn;
    static int containerViewId = R.id.id_eknow_main_content;

    private static FragmentsFactory mInstance = null;

    private FragmentsFactory(){

    }

    public static FragmentsFactory getInstance(){
        if(mInstance == null){
            mInstance = new FragmentsFactory();
        }
        return mInstance;
    }

    public static void setFragment(FragmentActivity activity,
                                   Fragment oldFragment, Fragment newFragment, Bundle bundle){
        setFragment(activity, oldFragment, newFragment,  bundle, true);
    }
    public static void setFragment(FragmentActivity activity,
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
            transaction.addToBackStack(((BaseFragment)oldFragment).getMyTag());
        }
        transaction.commit();
    }
    public static void setFragmentTo(FragmentActivity activity, Fragment oldFragment){
        FragmentManager fm = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        fm.popBackStackImmediate(((BaseFragment)oldFragment).getMyTag(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
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
        setFragment(activity, null, home, null);
        return home;
    }

    public static void setServicesListFragment(FragmentActivity activity, Fragment oldFragment, Bundle bundle){
        if (slf == null){
            slf = new ServicesListFragment();
        }
        setFragment(activity, oldFragment, slf , bundle);
    }

    public static void setServiceDetailsFragment(FragmentActivity activity, Fragment oldFragment, Bundle bundle){
        //if (sdf == null){
        sdf = new ServiceDetailsFragment();
        //}
        setFragment(activity, oldFragment, sdf, bundle);
    }

    public static void setServiceBuyFragment(FragmentActivity activity, Fragment oldFragment, Bundle bundle){
        if (sbf == null){
            sbf = new ServiceBuyFragment();
        }
        setFragment(activity, oldFragment, sbf, bundle);
    }

    public static void setSignInFragment(FragmentActivity activity, Fragment oldFragment, Bundle bundle){
        if (signIn == null){
            signIn = new SignInFragment();
        }
        setFragment(activity, oldFragment, signIn, bundle);
    }
    public static void setSignOnFragment(FragmentActivity activity, Fragment oldFragment, Bundle bundle){
        if (signOn == null){
            signOn = new SignOnFragment();
        }
        setFragment(activity, oldFragment, signOn, bundle);
    }
}
