package eknow.com.eknow.user;

/**
 * Created by jianguog on 16/12/31.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import eknow.com.eknow.R;

public class SelectPhoneRegionListAdaptor extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<String> splitList;
    private List<String> totallist;

    public SelectPhoneRegionListAdaptor(Context context, List<String> splitList, List<String> totallist) {
        this.splitList = splitList;
        this.totallist = totallist;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return totallist.size();
    }

    @Override
    public Object getItem(int position) {
        return totallist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean isEnabled(int position) {
        if (splitList.contains(totallist.get(position))) {
            return false;
        }
        return super.isEnabled(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (splitList.contains(totallist.get(position))) {
            convertView = mInflater.inflate(R.layout.region_header, null);
        } else {
            convertView = mInflater.inflate(R.layout.region_item, null);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.itemTitle);
        textView.setText(totallist.get(position));
        return convertView;
    }

}
