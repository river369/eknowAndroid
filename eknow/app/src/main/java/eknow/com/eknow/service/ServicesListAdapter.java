package eknow.com.eknow.service;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import eknow.com.eknow.R;

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
        holder.sellerName.setText(service.getSellerName());

        //String url = "http://upload.wikimedia.org/wikipedia/en/b/bc/Wiki.png";
        String url = "http://oss.clcentury.com/yzphoto/pics/57fcf61485102170/580387eb370c1148/main.png";
        holder.image.setImageURL(url);
    }

    @Override
    public int getItemCount() {
        return services.size();
    }


}

