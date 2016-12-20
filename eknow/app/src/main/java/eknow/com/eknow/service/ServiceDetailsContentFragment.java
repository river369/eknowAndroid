package eknow.com.eknow.service;

/**
 * Created by jianguog on 16/12/15.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import eknow.com.eknow.EnvConstants;
import eknow.com.eknow.R;
import eknow.com.eknow.ValueConstants;
import eknow.com.eknow.utils.ImageSingleton;

public class ServiceDetailsContentFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;
    private static ServiceInfo serviceInfo;

    public static ServiceDetailsContentFragment newInstance(int page, ServiceInfo si) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        serviceInfo = si;
        ServiceDetailsContentFragment serviceFragment = new ServiceDetailsContentFragment();
        serviceFragment.setArguments(args);
        return serviceFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.service_details_service_info, container, false);
        if (serviceInfo != null) {
            TextView nameText = (TextView) view.findViewById(R.id.service_name_content);
            nameText.setText(serviceInfo.getServiceName());
            TextView briefText = (TextView) view.findViewById(R.id.service_brief_content);
            briefText.setText(serviceInfo.getServiceBrief());
            TextView descText = (TextView) view.findViewById(R.id.service_description_content);
            descText.setText(serviceInfo.getServiceDescription());
            RatingBar ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
            ratingBar.setRating((float)serviceInfo.getStars());
            TextView areaText = (TextView) view.findViewById(R.id.service_area_content);
            areaText.setText(serviceInfo.getServiceArea());
            TextView typeText = (TextView) view.findViewById(R.id.service_type_content);
            typeText.setText(ValueConstants.serviceType[serviceInfo.getServiceType()]);
            TextView languageText = (TextView) view.findViewById(R.id.service_language_content);
            languageText.setText(serviceInfo.getServiceLanguage());
            String servicePriceType = serviceInfo.getService_price_type() ==1 ? "/小时" : "/次";
            String servicePrice = String.valueOf(serviceInfo.getService_price()) + servicePriceType ;
            //System.out.println(servicePrice);
            TextView priceText = (TextView) view.findViewById(R.id.service_price_content);
            priceText.setText(servicePrice);
        }
        return view;
    }

}