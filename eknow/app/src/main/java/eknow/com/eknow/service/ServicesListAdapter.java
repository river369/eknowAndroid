package eknow.com.eknow.service;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import eknow.com.eknow.EknowConstants;
import eknow.com.eknow.R;
import pl.polidea.webimageview.WebImageView;

public class ServicesListAdapter extends
        RecyclerView.Adapter<ServicesListViewHolder> {

    private List<ServiceInfo> services = new ArrayList<>();

    public ServicesListAdapter(List<ServiceInfo> serviceList) {
        this.services.addAll(serviceList);
    }
    public void addMoreSerices(List<ServiceInfo> serviceList){
        this.services.addAll(serviceList);
    }

    @Override
    public ServicesListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.service_list_row, parent, false);
        return new ServicesListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ServicesListViewHolder holder, int position) {
        final ServiceInfo service = services.get(position);
        holder.title.setText("【" + service.getServiceArea() + "】" + service.getServiceName());
        holder.ratingBar.setRating((float)service.getStars());
        holder.sellerName.setText(service.getSellerName());
        String serviceType = service.getService_price_type() ==1 ? "/小时" : "/次";
        holder.price.setText(String.valueOf(service.getService_price()) + serviceType);
        holder.description.setText(service.getServiceBrief());
        String url = EknowConstants.SERVIC_MAIN_PIC_URL + service.getSellerId() + "/" + service.getServiceId() + "/main.png";
        //System.out.println(service + url);
        holder.mainImage.setImageURL(url);
    }

    @Override
    public int getItemCount() {
        return services.size();
    }


}

