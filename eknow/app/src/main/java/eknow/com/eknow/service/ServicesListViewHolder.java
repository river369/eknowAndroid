package eknow.com.eknow.service;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import eknow.com.eknow.R;

/**
 * Created by jianguog on 16/12/5.
 */

public class ServicesListViewHolder extends RecyclerView.ViewHolder {
    public TextView title;
    public RatingBar ratingBar;
    public TextView sellerName;
    public NetworkImageView mainImage;
    public TextView price;
    public TextView description;

    public ServicesListViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.title);
        ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
        mainImage = (NetworkImageView) itemView.findViewById(R.id.serviceMainImage);
        sellerName = (TextView) itemView.findViewById(R.id.sellerName);
        price = (TextView) itemView.findViewById(R.id.price);
        description = (TextView) itemView.findViewById(R.id.description);
    }
}
