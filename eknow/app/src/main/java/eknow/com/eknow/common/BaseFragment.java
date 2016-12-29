package eknow.com.eknow.common;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment implements FragmentBackHandler {
    public BaseFragment() {
    }

    @Override
    public final boolean onBackPressed() {
          return BackHandlerHelper.handleBackPress(this);
    }

    public String getMyTag() {
        return null;
    };


}
