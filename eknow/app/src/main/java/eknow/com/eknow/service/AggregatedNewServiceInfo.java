package eknow.com.eknow.service;

import java.util.List;

import eknow.com.eknow.user.UserInfo;

/**
 * Created by jianguog on 16/12/21.
 */

public class AggregatedNewServiceInfo {
    ServiceInfo serviceInfo;
    String[] imageUrls;

    public AggregatedNewServiceInfo(ServiceInfo serviceInfo, String[] imageUrls) {
        this.serviceInfo = serviceInfo;
        this.imageUrls = imageUrls;
    }

    public ServiceInfo getServiceInfo() {
        return serviceInfo;
    }

    public void setServiceInfo(ServiceInfo serviceInfo) {
        this.serviceInfo = serviceInfo;
    }

    public String[] getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(String[] imageUrls) {
        this.imageUrls = imageUrls;
    }
}
