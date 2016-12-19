package eknow.com.eknow.service;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import eknow.com.eknow.EnvConstants;
import eknow.com.eknow.R;
import eknow.com.eknow.utils.ImageSingleton;


public class ServicesListAdapter extends
        RecyclerView.Adapter<ServicesListViewHolder> implements View.OnClickListener{

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
        view.setOnClickListener(this);
        return new ServicesListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ServicesListViewHolder holder, int position) {
        final ServiceInfo service = services.get(position);
        // bind something for item click
        holder.itemView.setTag(service.getSellerId() + "," + service.getServiceId());

        //bind other service info for show
        holder.title.setText("【" + service.getServiceArea() + "】" + service.getServiceName());
        holder.ratingBar.setRating((float)service.getStars());
        holder.sellerName.setText(service.getSellerName());
        String serviceType = service.getService_price_type() ==1 ? "/小时" : "/次";
        holder.price.setText(String.valueOf(service.getService_price()) + serviceType);
        holder.description.setText(service.getServiceBrief());
        String url = EnvConstants.SERVIC_MAIN_PIC_URL + service.getSellerId() + "/" + service.getServiceId() + "/main.png";
        //System.out.println(service + url);
        //holder.mainImage.setImageURL(url);
        holder.mainImage.setImageUrl(url, ImageSingleton.getInstance().getImageLoader());
        holder.mainImage.setErrorImageResId(R.drawable.head_default);
    }

    @Override
    public int getItemCount() {
        return services.size();
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            // The onclick call will be map to OnRecyclerViewItemClickListener.onItemClick as follow.
            mOnItemClickListener.onItemClick(v,(String)v.getTag());
        }
    }

    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view , String data);
    }
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

}

