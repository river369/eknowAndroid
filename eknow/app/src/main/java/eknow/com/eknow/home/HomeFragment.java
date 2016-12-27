package eknow.com.eknow.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import eknow.com.eknow.FragmentsFactory;
import eknow.com.eknow.MainActivity;
import eknow.com.eknow.R;
import eknow.com.eknow.common.BaseFragment;
import eknow.com.eknow.KeyConstants;

public class HomeFragment extends BaseFragment {
    View view;
    GridView gridview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        ((MainActivity)getActivity()).addTopMainToolbar();
        ((MainActivity)getActivity()).selectTopBarToBeVisible("home");
        view = inflater.inflate(R.layout.home_fragment, container, false);
        addCategoryGridView();
        return view;
    }

    public void addCategoryGridView() {
        gridview = (GridView) view.findViewById(R.id.categoryGridView);
        gridview.setAdapter(new HomeImageAdapter(getActivity()));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                goToServiceListFragment(position);
                //goToServiceListActivity(position);
                Toast.makeText(getActivity(), id + "  " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /** Called when the user clicks the category view */
    public void goToServiceListFragment(int position) {
        Bundle bundle = new Bundle();
        bundle.putString(KeyConstants.serviceArea, "地球");
        if (position == 0) {
            bundle.putString(KeyConstants.serviceType, "1");
        } else if (position == 1) {
            bundle.putString(KeyConstants.serviceType, "2");
        }
        FragmentsFactory.getInstance().setServicesListFragment(getActivity(), this ,bundle);
    }

    // To be deprecated
//    public void goToServiceListActivity(int position) {
//        Intent intent = new Intent(getActivity(), ServicesActivity.class);
//        intent.putExtra(KeyConstants.serviceArea, "地球");
//        if (position == 0) {
//            intent.putExtra(KeyConstants.serviceType, "1");
//        } else if (position == 1) {
//            intent.putExtra(KeyConstants.serviceType, "2");
//        }
//        startActivity(intent);
//    }
}
