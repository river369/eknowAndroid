package eknow.com.eknow.service;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import eknow.com.eknow.R;
import eknow.com.eknow.common.BaseFragment;

/**
 * Created by jianguog on 16/11/28.
 *
 * Reference from
 * http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0731/3247.html
 */

public class ServiceDetailsFragment extends BaseFragment {

    View view;

    private ServiceDetailsAdapter pagerAdapter;

    private ViewPager viewPager;

    private TabLayout tabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((ServicesActivity)getActivity()).setToolbarTitle(R.string.serviceDetails);
        view = inflater.inflate(R.layout.service_details_fragment, container, false);
        pagerAdapter = new ServiceDetailsAdapter(getActivity().getSupportFragmentManager(), getActivity());
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager.setAdapter(pagerAdapter);
        tabLayout = (TabLayout) view.findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        return view;
    }
}