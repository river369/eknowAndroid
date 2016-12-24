package eknow.com.eknow.service;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Map;

import eknow.com.eknow.EnvConstants;
import eknow.com.eknow.KeyConstants;
import eknow.com.eknow.R;
import eknow.com.eknow.common.BaseFragment;
import eknow.com.eknow.common.EknowException;
import eknow.com.eknow.common.ui.SlideShowView;

/**
 * Created by jianguog on 16/11/28.
 *
 * Reference from
 * http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0731/3247.html
 */

public class ServiceDetailsFragment extends BaseFragment {

    View view;

    SlideShowView ssv;

    private ServiceDetailsAdapter pagerAdapter;

    private ViewPager viewPager;

    private TabLayout tabLayout;

    private RequestQueue queue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((ServicesActivity)getActivity()).setToolbarTitle(R.string.serviceDetails);
        queue = Volley.newRequestQueue(getActivity());
        view = inflater.inflate(R.layout.service_details_fragment, container, false);

        final ServiceInfo si = (ServiceInfo)getArguments().getSerializable(KeyConstants.sellerInfo);
        ssv = (SlideShowView) view.findViewById(R.id.serviceDetailSlideshowView);
        getServicePictures(si.getSellerId(), si.getServiceId());

        pagerAdapter = new ServiceDetailsAdapter(getActivity().getSupportFragmentManager());


        viewPager = (ViewPager) view.findViewById(R.id.serviceDetailViewpager);
        viewPager.setAdapter(pagerAdapter);
        final int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        final int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        ViewTreeObserver vto = viewPager.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
            viewPager.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            View view = viewPager.getChildAt(viewPager.getCurrentItem());
            if (view != null) {
                view.measure(w, h);
                ViewGroup.LayoutParams params = viewPager.getLayoutParams();
                params.height = view.getMeasuredHeight()+800;
                //System.out.println(params.height);
                viewPager.setLayoutParams(params);
            }
            }
        });
        tabLayout = (TabLayout) view.findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
        //tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        //pagerAdapter.setServiceInfo(si);
        //getServiceInfoById(serviceId);
        getAggregatedServiceDetails(si);


        return view;
    }

    public void getServicePictures(String sellerId, String serviceId) {
        String url = EnvConstants.API_URL;
        ServicesRequestBuilder srb = new ServicesRequestBuilder();
        Map<String, String> params = srb.buildServicePicturesRequestParameters(sellerId, serviceId);

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
                            String[] imageUrls = sjp.getServicePictures();
                            //System.out.println(String.valueOf(imageUrls.length));
                            ssv.setData(imageUrls);
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

//    public void getServiceInfoById(String serviceId) {
//        String url = EnvConstants.API_URL;
//        ServicesRequestBuilder srb = new ServicesRequestBuilder();
//        Map<String, String> params = srb.buildServiceInfoByIdRequestParameters(serviceId);
//
//        // Request a string response from the provided URL.
//        JsonObjectRequest jsonRequest = new JsonObjectRequest(
//                Request.Method.POST, url,
//                new JSONObject(params),
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        //System.out.println(response.toString());
//                        try {
//                            ServicesResponseJsonParser sjp = new ServicesResponseJsonParser(response);
//                            ServiceInfo si = sjp.getServiceInfoById();
//                            pagerAdapter.setServiceInfo(si);
//                        } catch (EknowException e) {
//                            e.printStackTrace();
//                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        error.printStackTrace();
//                    }
//                }
//        );
//        queue.add(jsonRequest);
//    }

    public void getAggregatedServiceDetails(final ServiceInfo si) {
        String url = EnvConstants.API_URL;
        ServicesRequestBuilder srb = new ServicesRequestBuilder();
        Map<String, String> params = srb.buildAggregatedServiceDetailsRequestParameters(si.getSellerId(), si.getServiceId());

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
                            AggregatedServiceDetailInfo ai = sjp.getAggregatedServiceDetails();
                            pagerAdapter.setServiceDetailsInfo(si, ai.getSeller(), ai.getComments());
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
}