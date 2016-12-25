package eknow.com.eknow.service;

/**
 * Created by jianguog on 16/12/24.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import eknow.com.eknow.R;


public class TagButtonAdaptor extends BaseAdapter {
    private Context context;
    private final String[] tagValues;

    public TagButtonAdaptor(Context context, String[] tagValues) {
        this.context = context;
        this.tagValues = tagValues;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.tag_button, null);

            Button button = (Button) gridView.findViewById(R.id.id_tag_button);
            button.setText(tagValues[position]);

        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return tagValues.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
