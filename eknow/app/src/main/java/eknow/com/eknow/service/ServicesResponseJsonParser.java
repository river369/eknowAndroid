package eknow.com.eknow.service;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import eknow.com.eknow.EnvConstants;
import eknow.com.eknow.common.EknowException;
import eknow.com.eknow.common.ResponseJsonParserBase;
import eknow.com.eknow.user.UserInfo;

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

    /**
     * Get the services, which will be show on the list page
     * @return
     * @throws EknowException
     */
    public List<ServiceInfo> getServiceInfos() throws EknowException {
        setDataTag("services");
        List<ServiceInfo> services = new ArrayList<>();
        try {
            for (int i = 0; i < getDataArray().length(); i++) {
                JSONObject jo = (JSONObject) getDataArray().get(i);
                ServiceInfo serviceInfo = getServiceInfoFromJSONObject(jo);
                services.add(serviceInfo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw reportError(Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
        }
        return services;
    }

    /**
     * get one service info
     * @return
     * @throws EknowException
     */
    public ServiceInfo getServiceInfo() throws EknowException {
        setDataTag("serviceInfo");
        ServiceInfo serviceInfo;
        try {
            JSONObject jo = getDataObject();
            serviceInfo = getServiceInfoFromJSONObject(jo);
        } catch (JSONException e) {
            e.printStackTrace();
            throw reportError(Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
        }
        return serviceInfo;
    }

    ServiceInfo getServiceInfoFromJSONObject(JSONObject jo) throws JSONException {
        ServiceInfo serviceInfo = new ServiceInfo();
        serviceInfo.setServiceId(jo.getString("service_id"));
        serviceInfo.setServiceName(jo.getString("service_name"));
        serviceInfo.setServiceType(jo.getInt("service_type"));
        serviceInfo.setSellerId(jo.getString("seller_id"));
        serviceInfo.setSellerName(jo.getString("seller_name"));
        serviceInfo.setServiceArea(jo.getString("service_area"));
        serviceInfo.setServiceBrief(jo.getString("service_brief"));
        serviceInfo.setService_price_type(jo.getInt("service_price_type"));
        serviceInfo.setService_price(jo.getDouble("service_price"));
        serviceInfo.setStars((float)jo.getDouble("stars"));
        serviceInfo.setServiceTag(jo.getString("tag"));
        serviceInfo.setServiceDescription(jo.getString("description"));
        serviceInfo.setServiceLanguage(jo.getString("service_language"));
        return serviceInfo;
    }

    /**
     * get the service info, currently not used since it has already be got from list.
     * May be can be used in future.
     *
     * @return
     * @throws EknowException
     */
    public ServiceInfo getServiceInfoById() throws EknowException {
        return getServiceInfo();
    }

    /**
     * Get the pictures of service, which will be show on the header of details page
     * @return
     * @throws EknowException
     */
    public String[] getServicePictures() throws EknowException {
        setDataTag("servicePictures");
        if (!isTagExisted()) {
            return null;
        }
        String[] servicePictures = new String[getDataArray().length()];
        try {
            for (int i = 0; i < getDataArray().length(); i++) {
                String path = (String) getDataArray().get(i);
                //servicePictures[i] = EnvConstants.OSS_BASE_URL + path;
                servicePictures[i] = path;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw reportError(Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
        }
        return servicePictures;
    }

    /**
     * get the seller info, and comments info
     *
     * @return
     * @throws EknowException
     */
    public AggregatedServiceDetailInfo getAggregatedServiceDetails() throws EknowException {
        UserInfo seller = new UserInfo();
        List<CommentInfo> comments = new ArrayList<>();

        try {
            setDataTag("sellerInfo");
            JSONObject jo = getDataObject();
            seller.setName(jo.getString("name"));
            seller.setSignature(jo.getString("signature"));
            seller.setDescription(jo.getString("description"));
            seller.setStars((float)jo.getDouble("stars"));
            seller.setTag(jo.getString("tag"));

            setDataTag("comments");
            if (isTagExisted()) {
                for (int i = 0; i < getDataArray().length(); i++) {
                    jo = (JSONObject) getDataArray().get(i);
                    CommentInfo ci = new CommentInfo();
                    ci.setCustomerName(jo.getString("customer_name"));
                    ci.setComments(jo.getString("comments"));
                    ci.setStars((float)jo.getDouble("stars"));
                    ci.setCreationDate(jo.getString("creation_date"));
                    comments.add(ci);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw reportError(Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
        }
        return new AggregatedServiceDetailInfo(seller, comments);
    }

    public AggregatedNewServiceInfo getAggregatedCreateOrUpdatePublishingService() throws EknowException {
        ServiceInfo serviceInfo = getServiceInfo();
        String[] imageUrls = getServicePictures();
        return new AggregatedNewServiceInfo(serviceInfo, imageUrls);
    }
}
