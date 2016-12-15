package eknow.com.eknow;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import eknow.com.eknow.service.ServicesListActivity;
import eknow.com.eknow.utils.KeyConstants;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    GridView gridview;
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addToolbar();
        addCategoryGridView();
        addImageButton();
    }

    void addToolbar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
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
                    Toast.makeText(MainActivity.this , "action_search" , Toast.LENGTH_SHORT).show();

                } else if (menuItemId == R.id.setting) {
                    Toast.makeText(MainActivity.this , "setting" , Toast.LENGTH_SHORT).show();

                } else if (menuItemId == R.id.changeRoleToSeller) {
                    Toast.makeText(MainActivity.this , "changeRoleToSeller" , Toast.LENGTH_SHORT).show();

                }
                return true;
            }
        });
    }

    public void addCategoryGridView() {
        gridview = (GridView) findViewById(R.id.categoryGridView);
        gridview.setAdapter(new MainImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                sendMessage(position);
                Toast.makeText(MainActivity.this, id + "  " + position, Toast.LENGTH_SHORT).show();
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

    /** Called when the user clicks the Send button */
    public void sendMessage(int position) {
        Intent intent = new Intent(this, ServicesListActivity.class);
        intent.putExtra(KeyConstants.serviceArea, R.string.world);
        if (position == 0) {
            intent.putExtra(KeyConstants.serviceType, "1");
        } else if (position == 1) {
            intent.putExtra(KeyConstants.serviceType, "2");
        }
        startActivity(intent);
    }
}
