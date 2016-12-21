package eknow.com.eknow.service;

import java.io.Serializable;

/**
 * Created by jianguog on 16/12/4.
 */

public class ServiceInfo implements Serializable {
    private String serviceId;
    private String serviceArea;
    private int serviceType;
    private String serviceName;
    private String sellerId;
    private String sellerName;
    private String serviceBrief;
    private String serviceDescription;
    private int service_price_type;
    private double service_price;
    private float stars;
    private String serviceTag;
    private String serviceLanguage;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceArea() {
        return serviceArea;
    }

    public void setServiceArea(String serviceArea) {
        this.serviceArea = serviceArea;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getServiceBrief() {
        return serviceBrief;
    }

    public void setServiceBrief(String serviceBrief) {
        this.serviceBrief = serviceBrief;
    }

    public int getService_price_type() {
        return service_price_type;
    }

    public void setService_price_type(int service_price_type) {
        this.service_price_type = service_price_type;
    }

    public double getService_price() {
        return service_price;
    }

    public void setService_price(double service_price) {
        this.service_price = service_price;
    }

    public float getStars() {
        return stars;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }

    public String getServiceTag() {
        return serviceTag;
    }

    public void setServiceTag(String serviceTag) {
        this.serviceTag = serviceTag;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public int getServiceType() {
        return serviceType;
    }

    public void setServiceType(int serviceType) {
        this.serviceType = serviceType;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }


    public String getServiceLanguage() {
        return serviceLanguage;
    }

    public void setServiceLanguage(String serviceLanguage) {
        this.serviceLanguage = serviceLanguage;
    }


    @Override
    public String toString() {
        return "ServiceInfo{" +
                "serviceId='" + serviceId + '\'' +
                ", serviceArea='" + serviceArea + '\'' +
                ", serviceType=" + serviceType +
                ", serviceName='" + serviceName + '\'' +
                ", sellerId='" + sellerId + '\'' +
                ", sellerName='" + sellerName + '\'' +
                ", serviceBrief='" + serviceBrief + '\'' +
                ", serviceDescription='" + serviceDescription + '\'' +
                ", service_price_type=" + service_price_type +
                ", service_price=" + service_price +
                ", stars=" + stars +
                ", serviceTag='" + serviceTag + '\'' +
                ", serviceLanguage='" + serviceLanguage + '\'' +
                '}';
    }
}
