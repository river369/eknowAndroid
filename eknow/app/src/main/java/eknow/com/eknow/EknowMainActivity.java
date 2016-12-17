package eknow.com.eknow;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.Toast;

import eknow.com.eknow.common.BackHandlerHelper;

/**
 * Created by jianguog on 16/11/28.
 */

public class EknowMainActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.eknow_main_activity);
        FragmentsFactory.getInstance().setMainFragment(this);
    }

    private long lastBackPress;
    @Override
    public void onBackPressed() {
        if (!BackHandlerHelper.handleBackPress(this)) {
            if (System.currentTimeMillis() - lastBackPress < 1000) {
                super.onBackPressed();
            } else {
                lastBackPress = System.currentTimeMillis();
                Toast.makeText(EknowMainActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
