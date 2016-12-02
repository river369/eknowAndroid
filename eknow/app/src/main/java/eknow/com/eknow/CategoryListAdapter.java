package eknow.com.eknow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import pl.polidea.webimageview.WebImageView;

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
        TextView textView = (TextView) view.findViewById(R.id.sellerName);

        WebImageView image = (WebImageView) view.findViewById(R.id.serviceHeaderImage);
        //String url = "http://upload.wikimedia.org/wikipedia/en/b/bc/Wiki.png";
        String url = "http://oss.clcentury.com/yzphoto/pics/57fcf61485102170/580387eb370c1148/main.png";
        image.setImageURL(url);

        //System.out.println("[[[["+image+"}}}}}}");
        //System.out.println("[[[["+image.getUrl()+"}}}}}}"+image.isShown());
        //ImageView imageView = (ImageView) view.findViewById(R.id.serviceHeaderImage);
        textView.setText( getItem(position));
        return view;
    }
}
