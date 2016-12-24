package eknow.com.eknow.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import eknow.com.eknow.R;
import eknow.com.eknow.common.BaseFragment;
import eknow.com.eknow.KeyConstants;
import eknow.com.eknow.service.ServicesActivity;

public class HomeFragment extends BaseFragment {

    View view;
    //Toolbar toolbar;
    GridView gridview;
    //ImageButton imageButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.home_fragment, container, false);
        //addToolbar();
        addCategoryGridView();
        //addImageButton();
        return view;
    }

    public void addCategoryGridView() {
        gridview = (GridView) view.findViewById(R.id.categoryGridView);
        gridview.setAdapter(new HomeImageAdapter(getActivity()));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //goToServiceListFragment(position);
                goToServiceListActivity(position);
                Toast.makeText(getActivity(), id + "  " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /** Called when the user clicks the category view */
    public void goToServiceListActivity(int position) {
        Intent intent = new Intent(getActivity(), ServicesActivity.class);
        intent.putExtra(KeyConstants.serviceArea, "地球");
        if (position == 0) {
            intent.putExtra(KeyConstants.serviceType, "1");
        } else if (position == 1) {
            intent.putExtra(KeyConstants.serviceType, "2");
        }
        startActivity(intent);
    }

//    void addToolbar() {
//        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
//        toolbar.inflateMenu(R.menu.activity_main_toolbar);//设置右上角的填充菜单
//        //toolbar.setNavigationIcon(R.mipmap.ic_launcher);//设置导航栏图标
//        //toolbar.setLogo(R.mipmap.ic_launcher);//设置app logo
//        //toolbar.setTitle("Title");//设置主标题
//        //toolbar.setSubtitle("Subtitle");//设置子标题
//        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                int menuItemId = item.getItemId();
//                if (menuItemId == R.id.action_search) {
//                    Toast.makeText(getActivity() , "action_search" , Toast.LENGTH_SHORT).show();
//
//                } else if (menuItemId == R.id.setting) {
//                    Toast.makeText(getActivity() , "setting" , Toast.LENGTH_SHORT).show();
//
//                } else if (menuItemId == R.id.changeRoleToSeller) {
//                    Toast.makeText(getActivity() , "changeRoleToSeller" , Toast.LENGTH_SHORT).show();
//
//                }
//                return true;
//            }
//        });
//    }


//    public void addImageButton() {
//        imageButton = (ImageButton) view.findViewById(R.id.homeBtn);
//        imageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//                Toast.makeText(getActivity(), "ImageButton is clicked!", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    /** Called when the user clicks the category view */
//    public void goToServiceListFragment(int position) {
//        Bundle bundle = new Bundle();
//        bundle.putString(KeyConstants.serviceArea, "地球");
//        if (position == 0) {
//            bundle.putString(KeyConstants.serviceType,"1");
//        } else if (position == 1) {
//            bundle.putString(KeyConstants.serviceType, "2");
//        }
//        FragmentsFactory.getInstance().setServicesListFragment(getActivity(), this, bundle);
//    }


}
