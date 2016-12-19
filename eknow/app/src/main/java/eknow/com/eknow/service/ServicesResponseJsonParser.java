package eknow.com.eknow.service;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import eknow.com.eknow.EnvConstants;
import eknow.com.eknow.common.EknowException;
import eknow.com.eknow.common.ResponseJsonParserBase;

/**
 * Created by jianguog on 16/12/11.
 */

public class ServicesResponseJsonParser extends ResponseJsonParserBase {

    public ServicesResponseJsonParser(JSONObject response) throws EknowException {
        super(response);
    }

    EknowException reportError(String method, String messages){
        return new EknowException("Fail to parse service data in [" + method + "]. error is [" + messages + "]");
    }

    public List<ServiceInfo> getServiceInfos() throws EknowException {
        setDataTag("services");
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
            throw reportError(Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
        }
        return services;
    }

    public String[] getServicePictures() throws EknowException {
        setDataTag("servicePictures");
        String[] servicePictures = new String[getData().length()];
        try {
            for (int i = 0; i < getData().length(); i++) {
                String path = (String) getData().get(i);
                //System.out.println(EnvConstants.OSS_BASE_URL + path);
                servicePictures[i] = EnvConstants.OSS_BASE_URL + path;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw reportError(Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
        }
        return servicePictures;
    }

}
