package eknow.com.eknow.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import eknow.com.eknow.FragmentsFactory;
import eknow.com.eknow.KeyConstants;
import eknow.com.eknow.MainActivity;
import eknow.com.eknow.R;
import eknow.com.eknow.common.BackHandlerHelper;

/**
 * Created by jianguog on 16/11/28.
 */

public class ServicesActivity extends FragmentActivity {
    ImageButton imageButton;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.service_activity);
        addImageButton();
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

    public void addImageButton() {
        imageButton = (ImageButton) findViewById(R.id.serviceToolbarBtn);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                onBackPressed();
                //Toast.makeText(ServicesActivity.this, "ImageButton is clicked!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setToolbarTitle(int textId) {
        title = (TextView)  findViewById(R.id.serviceToolbarTitle);
        title.setText(textId);
    }

    @Override
    public void onBackPressed() {
        if (!BackHandlerHelper.handleBackPress(this)) {
                super.onBackPressed();
        }
    }
}
