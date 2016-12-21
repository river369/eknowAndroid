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
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import eknow.com.eknow.EnvConstants;
import eknow.com.eknow.R;
import eknow.com.eknow.ValueConstants;
import eknow.com.eknow.user.UserInfo;
import eknow.com.eknow.utils.ImageSingleton;

public class ServiceDetailsContentFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;
    private static ServiceDetailsAdapter serviceDetailsAdapter;

    public static ServiceDetailsContentFragment newInstance(int page, ServiceDetailsAdapter sa) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        serviceDetailsAdapter = sa;
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
        if (mPage == 0) {
            return buildServiceInfo(inflater,container,savedInstanceState);
        }
        if (mPage == 1) {
            return buildCommentInfo(inflater,container,savedInstanceState);
        }
        if (mPage == 2) {
            return buildSellerInfo(inflater,container,savedInstanceState);
        }
        return null;
    }

    View buildServiceInfo(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.service_details_service_info, container, false);
        ServiceInfo serviceInfo = serviceDetailsAdapter.getServiceInfo();
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

    View buildSellerInfo(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.service_details_seller_info, container, false);
        UserInfo sellerInfo = serviceDetailsAdapter.getSellerInfo();
        if (sellerInfo != null) {
            TextView nameText = (TextView) view.findViewById(R.id.seller_name_content);
            nameText.setText(sellerInfo.getName());
            TextView briefText = (TextView) view.findViewById(R.id.seller_signature_content);
            briefText.setText(sellerInfo.getSignature());
            TextView descText = (TextView) view.findViewById(R.id.seller_description_content);
            descText.setText(sellerInfo.getDescription());
            RatingBar ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
            ratingBar.setRating((float)sellerInfo.getStars());
        }
        return view;
    }

    View buildCommentInfo(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.service_details_comments_info, container, false);
        List<CommentInfo> comments = serviceDetailsAdapter.getComments();
        if (comments != null && comments.size() > 0) {
            ListView listView = (ListView)view.findViewById(R.id.service_detail_comments_list);
            List<HashMap<String,Object>> data = new ArrayList<HashMap<String,Object>>();
            for(CommentInfo comment:comments){
                HashMap<String,Object>map = new HashMap<String,Object>();
                map.put("name", comment.getCustomerName());
                map.put("rating", comment.getStars());
                map.put("comment", comment.getComments());
                map.put("date", comment.getCreationDate());
                data.add(map);
            }
            SimpleAdapter adapter = new SimpleAdapter(getActivity(), data, R.layout.service_details_comments_item_info,
                    new String[]{"name","rating","comment","date"},
                    new int[]{R.id.comment_customer_content, R.id.comment_rating_content,
                            R.id.comment_content_content, R.id.comment_date_content});
            listView.setAdapter(adapter);
        }
        return view;
    }

}