package eknow.com.eknow.service;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.List;

import eknow.com.eknow.R;
import eknow.com.eknow.common.photo.util.PhotoUtils;
import eknow.com.eknow.utils.ImageSingleton;

/**
 * Created by jianguog on 17/1/9.
 */

public class ServiceAddGridAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<String> remotePictures = new ArrayList<>();
    public ServiceAddGridAdapter(Context context, List<String> remotePictures) {
        inflater = LayoutInflater.from(context);
        this.remotePictures = remotePictures;
    }

    public int getCount() {
        if(remotePictures.size() == PhotoUtils.picture_max_num){
            return remotePictures.size();
        }
        //System.out.println("getCount=" + (remotePictures.size()+1));
        return remotePictures.size()+1;
    }

    public Object getItem(int arg0) {
        return null;
    }

    public long getItemId(int arg0) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        NetworkImageView imageView;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_published_grida, parent, false);
            imageView = (NetworkImageView) convertView.findViewById(R.id.item_grida_image);
        } else {
            imageView = (NetworkImageView) convertView;
        }

        if (position == remotePictures.size()) {
            imageView.setDefaultImageResId(R.drawable.icon_addpic_unfocused);
            if (position == PhotoUtils.picture_max_num) {
                imageView.setVisibility(View.GONE);
            }
        } else {
            System.out.println(remotePictures.get(position));
            imageView.setImageUrl(remotePictures.get(position), ImageSingleton.getInstance().getImageLoader());
        }
        return imageView;
    }
}
