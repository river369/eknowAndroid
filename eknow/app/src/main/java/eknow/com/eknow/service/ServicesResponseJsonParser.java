package eknow.com.eknow.service;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import eknow.com.eknow.common.ResponseJsonParserBase;

/**
 * Created by jianguog on 16/12/11.
 */

public class ServicesResponseJsonParser extends ResponseJsonParserBase {

    @Override
    public String getDataType() {
        return "services";
    }

    public ServicesResponseJsonParser(JSONObject response) {
        super(response);
    }

    public List<ServiceInfo> getServiceInfos(){
        List<ServiceInfo> services = new ArrayList<>();
        try {
            for (int i = 0; i < getData().length(); i++) {
                JSONObject jo = (JSONObject) getData().get(i);
                ServiceInfo si = new ServiceInfo();
                si.setServiceId(jo.getString("service_id"));
                si.setServiceName(jo.getString("service_name"));
                si.setSellerId(jo.getString("seller_id"));
                si.setSellerName(jo.getString("seller_name"));
                si.setServiceArea(jo.getString("service_area"));
                si.setServiceBrief(jo.getString("service_brief"));
                si.setService_price_type(jo.getInt("service_price_type"));
                si.setService_price(jo.getDouble("service_price"));
                si.setStars(jo.getDouble("stars"));
                services.add(si);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return services;
    }
}
