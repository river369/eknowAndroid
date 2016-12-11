package eknow.com.eknow.service;

/**
 * Created by jianguog on 16/12/4.
 */

public class ServiceInfo {
    private String serviceId;
    private String serviceArea;
    private String serviceName;
    private String sellerId;
    private String sellerName;
    private String serviceBrief;
    private int service_price_type;
    private double service_price;
    private double stars;

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

    public double getStars() {
        return stars;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        return "ServiceInfo{" +
                "serviceId='" + serviceId + '\'' +
                ", serviceArea='" + serviceArea + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", sellerId='" + sellerId + '\'' +
                ", sellerName='" + sellerName + '\'' +
                ", serviceBrief='" + serviceBrief + '\'' +
                ", service_price_type=" + service_price_type +
                ", service_price=" + service_price +
                ", stars=" + stars +
                '}';
    }
}
