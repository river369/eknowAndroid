package eknow.com.eknow.service;

/**
 * Created by jianguog on 16/12/15.
 */

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

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

            //LinearLayout layout = (LinearLayout) view.findViewById(R.id.serviceTagLayout);
            String tags = serviceInfo.getServiceTag();
            if(tags != null && tags.length() > 0 && !tags.equalsIgnoreCase(" ")) {
                String[] tagsArray = tags.split(",");
                GridView gridView = (GridView) view.findViewById(R.id.serviceTagGridView);
                gridView.setAdapter(new TagButtonAdaptor(getActivity(), tagsArray));
//                gridView.setOnItemClickListener(new OnItemClickListener() {
//                    public void onItemClick(AdapterView<?> parent, View v,
//                                            int position, long id) {
//                        Toast.makeText(
//                                getApplicationContext(),
//                                ((TextView) v.findViewById(R.id.grid_item_label))
//                                        .getText(), Toast.LENGTH_SHORT).show();
//
//                    }
//                });

//                int start = 0 ;
//                LinearLayout row = null;
//                for (int i = 0; i < tagsArray.length ; i++) {
//                    if (i == start * 3) {
//                        start++;
//                        row = new LinearLayout(getActivity());
//                        row.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                                ViewGroup.LayoutParams.WRAP_CONTENT));
//
//                        layout.addView(row);
//                    }
//                    Button btn = new Button(getActivity());
//                    btn.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
//                            ViewGroup.LayoutParams.WRAP_CONTENT));
//
//                    btn.setTextSize(10);
//                    btn.setTextColor(getResources().getColor(R.color.eknowTitle));
//                    btn.setBackgroundResource(R.drawable.tag_button_bg);
//                    btn.setText(tagsArray[i]);
//                    row.addView(btn);
//                }
            } else {
                TextView tagText = (TextView) view.findViewById(R.id.service_tag_title);
                tagText.setText("");
            }
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

            String tags = sellerInfo.getTag();
            if(tags != null && tags.length() > 0 && !tags.equalsIgnoreCase(" ")) {
                String[] tagsArray = tags.split(",");
                GridView gridView = (GridView) view.findViewById(R.id.sellerTagGridView);
                gridView.setAdapter(new TagButtonAdaptor(getActivity(), tagsArray));
            } else {
                TextView tagText = (TextView) view.findViewById(R.id.seller_tag_title);
                tagText.setText("");
            }
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