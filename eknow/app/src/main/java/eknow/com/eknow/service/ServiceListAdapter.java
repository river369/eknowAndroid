package eknow.com.eknow.service;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import eknow.com.eknow.R;
import pl.polidea.webimageview.WebImageView;

public class ServiceListAdapter extends
        RecyclerView.Adapter<ServiceListAdapter.ViewHolder> {

    private List<ServiceInfo> services = new ArrayList<>();

    public ServiceListAdapter(List<ServiceInfo> personList) {
        this.services.addAll(personList);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.service_list_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ServiceInfo service = services.get(position);
        holder.sellerName.setText(service.getSellerName());

        //String url = "http://upload.wikimedia.org/wikipedia/en/b/bc/Wiki.png";
        String url = "http://oss.clcentury.com/yzphoto/pics/57fcf61485102170/580387eb370c1148/main.png";
        holder.image.setImageURL(url);
    }

    public void swapItems(List<ServiceInfo> services) {
//        final ActorDiffCallback diffCallback = new ActorDiffCallback(this.actors, actors);
//        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
//
//        this.actors.clear();
//        this.actors.addAll(actors);
//        diffResult.dispatchUpdatesTo(this);
    }

    @Override
    public int getItemCount() {
        return services.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView sellerName;
        private WebImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            sellerName = (TextView) itemView.findViewById(R.id.sellerName);
            image = (WebImageView) itemView.findViewById(R.id.serviceHeaderImage);
        }
    }

}

