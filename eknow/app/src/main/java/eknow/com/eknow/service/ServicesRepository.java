package eknow.com.eknow.service;

import android.content.Context;
import android.util.Base64;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eknow.com.eknow.EknowConstants;

/**
 * Created by jianguog on 16/12/4.
 */

public class ServicesRepository {
    Context context;
    ServicesListAdapter adapter;
    RequestQueue queue;

    public ServicesRepository(Context context, ServicesListAdapter adapter) {
        this.context = context;
        this.adapter = adapter;
        queue = Volley.newRequestQueue(context);
    }
    public void getFirstPageServicesList() {
        String url = EknowConstants.API_URL;
        //System.out.println(url);

        Map<String, String> params = new HashMap<String, String>();
        params.put("method", "getServices");
        Map<String, String> requestData = new HashMap<String, String>();
        requestData.put("serviceArea", "'地球'");
        requestData.put("serviceType", "1");
        requestData.put("pageIndex''", "0");
        String dataString = Base64.encodeToString(new JSONObject(requestData).toString().getBytes(), Base64.DEFAULT);
        //System.out.println(dataString);
        params.put("data", dataString);

        // Request a string response from the provided URL.
        JsonObjectRequest jsonRequest = new JsonObjectRequest(
                Request.Method.POST, url,
                new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response.toString());
                        final List<ServiceInfo> services = new ArrayList<>();
                        for (int i=0; i<5; i++) {
                            ServiceInfo s1 = new ServiceInfo();
                            s1.setSellerName("init"+i);
                            services.add(s1);
                        }
                        adapter.addMoreSerices(services);
                        adapter.notifyDataSetChanged();
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


    public void addMoreServicesList(final int currentPage) {
        String url = EknowConstants.API_URL;
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response.toString());
                        final List<ServiceInfo> services = new ArrayList<>();
                        ServiceInfo s4 = new ServiceInfo();
                        s4.setSellerName("test"+currentPage);
                        services.add(s4);
                        adapter.addMoreSerices(services);
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
    }
}