package eknow.com.eknow;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by jianguog on 16/11/28.
 */

public class CategoryListActivity extends AppCompatActivity {

    AbsListView absListView;

    static String[] listItems = { "First Item", "Second Item", "Third Item",
            "Fourth Item", "Fifth Item", "Sixth Item", "Seventh Item",
            "Eight Item", "Ninth Item", "Tenth Item" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_list);

        absListView = (AbsListView) findViewById(R.id.listView1);
        absListView.setAdapter( new CategoryListAdapter(this, R.layout.category_list, listItems));

    }


}
