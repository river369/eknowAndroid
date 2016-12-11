package eknow.com.eknow.service;

import eknow.com.eknow.common.RequestBuilderBase;

/**
 * Created by jianguog on 16/12/11.
 */

public class ServicesRequestBuilder extends RequestBuilderBase {
    @Override
    public String getMethod() {
        return "getServices";
    }

    public ServicesRequestBuilder(String serviceArea, String serviceType, String pageIndex) {
        getRequestData().put("serviceArea", serviceArea);
        getRequestData().put("serviceType", serviceType);
        getRequestData().put("pageIndex", pageIndex);
    }

}
