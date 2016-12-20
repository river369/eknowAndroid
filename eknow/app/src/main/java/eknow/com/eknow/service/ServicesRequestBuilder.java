package eknow.com.eknow.service;

import java.util.Map;

import eknow.com.eknow.common.RequestBuilderBase;

/**
 * Created by jianguog on 16/12/11.
 */

public class ServicesRequestBuilder extends RequestBuilderBase {

    public ServicesRequestBuilder() {
    }
    public Map<String, String> buildServicesListRequestParameters(String serviceArea, String serviceType, String pageIndex){
        getRequestData().put("serviceArea", serviceArea);
        getRequestData().put("serviceType", serviceType);
        getRequestData().put("pageIndex", pageIndex);
        setMethod("getServices");
        return buildRequestParameters();
    }

    public Map<String, String>  buildServicePicturesRequestParameters(String sellerId, String serviceId){
        getRequestData().put("sellerId", sellerId);
        getRequestData().put("serviceId", serviceId);
        setMethod("getServicePictures");
        return buildRequestParameters();
    }

    public Map<String, String> buildServiceInfoByIdRequestParameters(String serviceId){
        getRequestData().put("serviceId", serviceId);
        setMethod("getServiceInfoById");
        return buildRequestParameters();
    }

}
