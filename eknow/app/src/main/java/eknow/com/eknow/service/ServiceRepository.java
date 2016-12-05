package eknow.com.eknow.service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jianguog on 16/12/4.
 */

public class ServiceRepository {
    public static List<ServiceInfo> getOriginalServicesList() {
        final List<ServiceInfo> services = new ArrayList<>();

        for (int i=0; i<10; i++) {
            ServiceInfo s1 = new ServiceInfo();
            s1.setSellerName("test"+i);
            services.add(s1);
        }


        return services;
    }

    public static List<ServiceInfo> addMoreActorList(int currentPage) {
        final List<ServiceInfo> services = new ArrayList<>();

        ServiceInfo s4 = new ServiceInfo();
        s4.setSellerName("test"+currentPage);
        services.add(s4);

        return services;
    }
}
