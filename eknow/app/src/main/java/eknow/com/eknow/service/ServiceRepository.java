package eknow.com.eknow.service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jianguog on 16/12/4.
 */

public class ServiceRepository {
    public static List<ServiceInfo> getOriginalActorList() {
        final List<ServiceInfo> services = new ArrayList<>();

        ServiceInfo s1 = new ServiceInfo();
        s1.setSellerName("test1");
        services.add(s1);

        ServiceInfo s2 = new ServiceInfo();
        s2.setSellerName("test2");
        services.add(s2);

        ServiceInfo s3 = new ServiceInfo();
        s3.setSellerName("test3");
        services.add(s3);


        return services;
    }
}
