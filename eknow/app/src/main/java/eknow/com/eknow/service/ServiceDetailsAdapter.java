package eknow.com.eknow.service;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jianguog on 16/12/15.
 */



public class ServiceDetailsAdapter extends FragmentStatePagerAdapter {

    final int PAGE_COUNT = 4;
    private String tabTitles[] = new String[]{"服务信息","服务攻略","服务评论","卖家信息"};

    ServiceInfo serviceInfo;

    private ArrayList<Fragment> mFragmentList;
    public ServiceDetailsAdapter(FragmentManager fm) {
        super(fm);
        updateData();
    }

    public void updateData() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i< tabTitles.length; i++) {
            fragments.add(ServiceDetailsContentFragment.newInstance(i, serviceInfo));
        }
        setFragmentList(fragments);
    }


//    @Override
//    public android.support.v4.app.Fragment getItem(int position) {
//        ServiceDetailsContentFragment sdcf = ServiceDetailsContentFragment.newInstance(position + 1, serviceInfo);
//        return sdcf;
//    }

//    @Override
//    public int getCount() {
//        return PAGE_COUNT;
//    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    public void setServiceInfo(ServiceInfo si) {
        this.serviceInfo = si;
        updateData();
    }


    private void setFragmentList(ArrayList<Fragment> fragmentList) {
        if(this.mFragmentList != null){
            mFragmentList.clear();
        }
        this.mFragmentList = fragmentList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this.mFragmentList.size();
    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }
}
