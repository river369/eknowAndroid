package eknow.com.eknow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by jianguog on 16/11/28.
 */
class CategoryListAdapter extends ArrayAdapter<String> {

    public CategoryListAdapter(Context context, int resource,
                               String[] values) {
        super(context, resource, values);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.category_list_row, parent, false);
        TextView textView = (TextView) view.findViewById(R.id.textView1);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView1);
        textView.setText( getItem(position));
        return view;
    }
}
