package eknow.com.eknow.service;

/**
 * Created by jianguog on 16/12/15.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import eknow.com.eknow.R;

public class ServiceDetailsContentFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    public static ServiceDetailsContentFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        ServiceDetailsContentFragment serviceFragment = new ServiceDetailsContentFragment();
        serviceFragment.setArguments(args);
        return serviceFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.service_details_content, container, false);
        TextView textView = (TextView) view;
        textView.setText("Fragment #" + mPage);
        return view;
    }

}