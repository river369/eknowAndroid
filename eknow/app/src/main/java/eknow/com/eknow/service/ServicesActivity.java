package eknow.com.eknow.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.widget.Toast;

import eknow.com.eknow.FragmentsFactory;
import eknow.com.eknow.KeyConstants;
import eknow.com.eknow.R;
import eknow.com.eknow.common.BackHandlerHelper;

/**
 * Created by jianguog on 16/11/28.
 */

public class ServicesActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.service_activity);
        setDefaultFragment();
    }

    void setDefaultFragment(){
        Intent intent=getIntent();
        final String serviceArea =intent.getStringExtra(KeyConstants.serviceArea);
        final String serviceType =intent.getStringExtra(KeyConstants.serviceType);

        Bundle bundle = new Bundle();
        bundle.putString(KeyConstants.serviceArea, serviceArea);
        bundle.putString(KeyConstants.serviceType, serviceType);
        FragmentsFactory.getInstance().setServicesListFragment(this, null, bundle);
    }

//    private long lastBackPress;
    @Override
    public void onBackPressed() {
        if (!BackHandlerHelper.handleBackPress(this)) {
//            if (System.currentTimeMillis() - lastBackPress < 1000) {
                super.onBackPressed();
//            } else {
//                lastBackPress = System.currentTimeMillis();
//                Toast.makeText(ServicesActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
//            }
        }
    }
}
