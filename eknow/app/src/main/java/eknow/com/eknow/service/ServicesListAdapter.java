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

    // major data to show
    private List<ServiceInfo> services = new ArrayList<>();

    /*
     constructor
      */
    public ServicesListAdapter(List<ServiceInfo> serviceList) {
        this.services.addAll(serviceList);
    }

    /*
     the external interface to add more service
      */
    public void addMoreSerices(List<ServiceInfo> serviceList){
        this.services.addAll(serviceList);
    }

    /*
    bind the view in xml with internal data type
     */
    @Override
    public ServicesListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.service_list_row, parent, false);
        view.setOnClickListener(this);
        return new ServicesListViewHolder(view);
    }

    /**
     * set the data of row in the view from serviceinfo
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ServicesListViewHolder holder, int position) {
        final ServiceInfo service = services.get(position);
        // bind something for item click
        holder.itemView.setTag(service);

        //bind other service info for show
        holder.title.setText("【" + service.getServiceArea() + "】" + service.getServiceName());
        holder.ratingBar.setRating((float)service.getStars());
        holder.sellerName.setText(service.getSellerName());
        String servicePriceType = service.getService_price_type() ==1 ? "/小时" : "/次";
        holder.price.setText(String.valueOf(service.getService_price()) + servicePriceType);
        holder.description.setText(service.getServiceBrief());
        String url = EnvConstants.SERVIC_MAIN_PIC_URL + service.getSellerId() + "/" + service.getServiceId() + "/main.png";
        holder.mainImage.setImageUrl(url, ImageSingleton.getInstance().getImageLoader());
        holder.mainImage.setErrorImageResId(R.drawable.head_default);
    }

    @Override
    public int getItemCount() {
        return services.size();
    }

    /*
     Define A listener to export to external
      */
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view , ServiceInfo si);
    }
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    /*
     The onclick call of adapter will be map to OnRecyclerViewItemClickListener.onItemClick as above.
     The above listener is defined in upper class ServicesListFragment.onCreateView()
      */
    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, (ServiceInfo)v.getTag());
        }
    }



}

