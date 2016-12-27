package eknow.com.eknow.service;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
//import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import eknow.com.eknow.EnvConstants;
import eknow.com.eknow.FragmentsFactory;
import eknow.com.eknow.MainActivity;
import eknow.com.eknow.R;
import eknow.com.eknow.common.BaseFragment;
import eknow.com.eknow.KeyConstants;
import eknow.com.eknow.common.EknowException;
import eknow.com.eknow.common.ui.DividerItemDecoration;

/**
 * Created by jianguog on 16/11/28.
 */

public class ServicesListFragment extends BaseFragment {

    View view;
    private RecyclerView recyclerView;
    private ServicesListAdapter adapter;
    private RequestQueue queue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity)getActivity()).addTopReturnToolbar();
        ((MainActivity)getActivity()).setToolbarTitle(R.string.servicesList);
        ((MainActivity)getActivity()).setBottomBarVisible(false);
        ((MainActivity)getActivity()).selectTopBarToBeVisible("return");

        view = inflater.inflate(R.layout.service_list_fragment, container, false);

        final String serviceArea =getArguments().getString(KeyConstants.serviceArea);
        final String serviceType =getArguments().getString(KeyConstants.serviceType);

        recyclerView = (RecyclerView) view.findViewById(R.id.servicesList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ServicesListAdapter(new ArrayList<ServiceInfo>());
        adapter.setOnItemClickListener(new ServicesListAdapter.OnRecyclerViewItemClickListener(){
            @Override
            public void onItemClick(View view , ServiceInfo si){
                //the data is "seller_id,service_id" that is set in ServicesListAdapter.onBindViewHolder
                goToServiceDetailsFragment(si);
            }
        });
        recyclerView.setAdapter(adapter);

        queue = Volley.newRequestQueue(getActivity());

        //RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST,
                new ColorDrawable(getResources().getColor(R.color.gray_bright)));
        itemDecoration.setHeight(15);

        recyclerView.addItemDecoration(itemDecoration);

        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                getServicesList(serviceArea, serviceType, String.valueOf(currentPage));
            }
        });

        getServicesList(serviceArea, serviceType, "0");

        return view;
    }

    public void getServicesList(String serviceArea, String serviceType, String pageIndex) {
        String url = EnvConstants.API_URL;
        //System.out.println("page index is " + pageIndex);
        ServicesRequestBuilder srb = new ServicesRequestBuilder();
        Map<String, String> params = srb.buildServicesListRequestParameters(serviceArea, serviceType, pageIndex);

        // Request a string response from the provided URL.
        JsonObjectRequest jsonRequest = new JsonObjectRequest(
                Request.Method.POST, url,
                new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //System.out.println(response.toString());
                        try {
                            ServicesResponseJsonParser sjp = new ServicesResponseJsonParser(response);
                            final List<ServiceInfo> services = sjp.getServiceInfos();
                            adapter.addMoreSerices(services);
                            adapter.notifyDataSetChanged();
                        } catch (EknowException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );
        queue.add(jsonRequest);
    }

    /** Called when the user clicks the service in list*/
    public void goToServiceDetailsFragment(ServiceInfo si) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KeyConstants.sellerInfo,si);
        FragmentsFactory.getInstance().setServiceDetailsFragment(getActivity(), this, bundle);
    }

    @Override
    public void onDestroyView() {
        ((MainActivity)getActivity()).setBottomBarVisible(true);
        ((MainActivity)getActivity()).selectTopBarToBeVisible("home");
        super.onDestroyView();
    }
}
