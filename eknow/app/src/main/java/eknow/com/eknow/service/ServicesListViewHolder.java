package eknow.com.eknow.service;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import eknow.com.eknow.R;
import pl.polidea.webimageview.WebImageView;

/**
 * Created by jianguog on 16/12/5.
 */

public class ServicesListViewHolder extends RecyclerView.ViewHolder {
    public TextView sellerName;
    public WebImageView image;

    public ServicesListViewHolder(View itemView) {
        super(itemView);
        sellerName = (TextView) itemView.findViewById(R.id.sellerName);
        image = (WebImageView) itemView.findViewById(R.id.serviceHeaderImage);
    }
}
