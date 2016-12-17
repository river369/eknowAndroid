package eknow.com.eknow;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.Toast;

import eknow.com.eknow.common.BackHandlerHelper;

/**
 * Created by jianguog on 16/11/28.
 */

public class MainActivity extends FragmentActivity {
    Toolbar toolbar;
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_activity);
        addToolbar();
        addImageButton();
        FragmentsFactory.getInstance().setMainFragment(this);
    }

        void addToolbar() {
        toolbar = (Toolbar) findViewById(R.id.mainToolBar);
        toolbar.inflateMenu(R.menu.activity_main_toolbar);//设置右上角的填充菜单
        //toolbar.setNavigationIcon(R.mipmap.ic_launcher);//设置导航栏图标
        //toolbar.setLogo(R.mipmap.ic_launcher);//设置app logo
        //toolbar.setTitle("Title");//设置主标题
        //toolbar.setSubtitle("Subtitle");//设置子标题
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int menuItemId = item.getItemId();
                if (menuItemId == R.id.action_search) {
                    Toast.makeText(MainActivity.this, "action_search" , Toast.LENGTH_SHORT).show();

                } else if (menuItemId == R.id.setting) {
                    Toast.makeText(MainActivity.this , "setting" , Toast.LENGTH_SHORT).show();

                } else if (menuItemId == R.id.changeRoleToSeller) {
                    Toast.makeText(MainActivity.this , "changeRoleToSeller" , Toast.LENGTH_SHORT).show();

                }
                return true;
            }
        });
    }

    public void addImageButton() {
        imageButton = (ImageButton) findViewById(R.id.homeBtn);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Toast.makeText(MainActivity.this, "ImageButton is clicked!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private long lastBackPress;
    @Override
    public void onBackPressed() {
        if (!BackHandlerHelper.handleBackPress(this)) {
            if (System.currentTimeMillis() - lastBackPress < 1000) {
                super.onBackPressed();
            } else {
                lastBackPress = System.currentTimeMillis();
                Toast.makeText(MainActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
