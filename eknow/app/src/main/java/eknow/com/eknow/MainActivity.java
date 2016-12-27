package eknow.com.eknow;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import eknow.com.eknow.common.BackHandlerHelper;
import eknow.com.eknow.home.HomeFragment;

/**
 * Created by jianguog on 16/11/28.
 */

public class MainActivity extends FragmentActivity{
    // 2 kinds of top tool bar
    Toolbar homeToolbar;
    Toolbar returnToolbar;

    // the bottom tool bar
    private TextView tabDiscover;
    private TextView tabCreate;
    private TextView tabMine;
    HomeFragment home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_activity);
        homeToolbar = (Toolbar) findViewById(R.id.main_top_menu);
        returnToolbar = (Toolbar) findViewById(R.id.return_top_menu);
        bindBottomView();
        home = FragmentsFactory.getInstance().setMainFragment(this);
    }

    // Build Top Tool Bar
    public void selectTopBarToBeVisible(String type) {
        homeToolbar.setVisibility(View.INVISIBLE);
        returnToolbar.setVisibility(View.INVISIBLE);
        if (type.equalsIgnoreCase("home")){
            homeToolbar.setVisibility(View.VISIBLE);
        } else if (type.equalsIgnoreCase("return")) {
            returnToolbar.setVisibility(View.VISIBLE);
        }
    }
    public void addTopMainToolbar() {
        homeToolbar.inflateMenu(R.menu.activity_main_toolbar);//设置右上角的填充菜单
        //toolbar.setNavigationIcon(R.mipmap.ic_launcher);//设置导航栏图标
        //toolbar.setLogo(R.mipmap.ic_launcher);//设置app logo
        //toolbar.setTitle("Title");//设置主标题
        //toolbar.setSubtitle("Subtitle");//设置子标题
        homeToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
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

        ImageButton imageButton = (ImageButton) findViewById(R.id.homeBtn);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Toast.makeText(MainActivity.this, "ImageButton is clicked!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addTopReturnToolbar() {
        ImageButton imageButton = (ImageButton) findViewById(R.id.serviceToolbarBtn);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                onBackPressed();
                //Toast.makeText(ServicesActivity.this, "ImageButton is clicked!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void setToolbarTitle(int textId) {
        TextView title = (TextView) findViewById(R.id.serviceToolbarTitle);
        title.setText(textId);
    }

    // Build bottom Bar
    public void setBottomBarVisible(boolean visable) {
        LinearLayout ll = (LinearLayout) findViewById(R.id.main_bottom_menu);
        if (visable) {
            ll.setVisibility(View.VISIBLE);
        } else {
            ll.setVisibility(View.INVISIBLE);
        }
    }

    //UI组件初始化与事件绑定
    private void bindBottomView() {
        tabDiscover = (TextView)this.findViewById(R.id.tab_discover);
        tabDiscover.setSelected(true);
        tabCreate = (TextView)this.findViewById(R.id.tab_create);
        tabMine = (TextView)this.findViewById(R.id.tab_mine);

        tabDiscover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                selected();
                tabDiscover.setSelected(true);
                if(home ==null){
                    home = FragmentsFactory.getInstance().setMainFragment(MainActivity.this);
                }else{
                    FragmentsFactory.getInstance().showFragment(MainActivity.this, home);
                }
                Toast.makeText(MainActivity.this, "tabDiscover is clicked!", Toast.LENGTH_SHORT).show();
            }
        });
        tabCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                selected();
                tabCreate.setSelected(true);
                Toast.makeText(MainActivity.this, "tabCreate is clicked!", Toast.LENGTH_SHORT).show();
            }
        });
        tabMine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                selected();
                tabMine.setSelected(true);
                Toast.makeText(MainActivity.this, "tabMine is clicked!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //重置所有文本的选中状态
    void selected(){
        tabDiscover.setSelected(false);
        tabCreate.setSelected(false);
        tabMine.setSelected(false);
    }

    //隐藏所有Fragment
    void hideAllFragment(FragmentTransaction transaction){
        if(home!=null){
            transaction.hide(home);
        }
    }

    // Handle return actions
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
