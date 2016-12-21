package eknow.com.eknow.service;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import eknow.com.eknow.user.UserInfo;

/**
 * Created by jianguog on 16/12/15.
 */



public class ServiceDetailsAdapter extends FragmentStatePagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[]{"服务信息","服务评论","卖家信息"};

    ServiceInfo serviceInfo;
    UserInfo sellerInfo;
    List<CommentInfo> comments;

    private ArrayList<Fragment> mFragmentList;
    public ServiceDetailsAdapter(FragmentManager fm) {
        super(fm);
        updateData();
    }

    public void setServiceDetailsInfo(ServiceInfo serviceInfo, UserInfo sellerInfo, List<CommentInfo> comments) {
        this.serviceInfo = serviceInfo;
        this.sellerInfo = sellerInfo;
        this.comments = comments;
        updateData();
    }

    public void updateData() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i< tabTitles.length; i++) {
            fragments.add(ServiceDetailsContentFragment.newInstance(i, this));
        }

        if(this.mFragmentList != null){
            mFragmentList.clear();
        }
        this.mFragmentList = fragments;
        notifyDataSetChanged();

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }


    @Override
    public int getCount() {
        return tabTitles.length;
    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    public ServiceInfo getServiceInfo() {
        return serviceInfo;
    }

    public UserInfo getSellerInfo() {
        return sellerInfo;
    }

    public List<CommentInfo> getComments() {
        return comments;
    }
}
