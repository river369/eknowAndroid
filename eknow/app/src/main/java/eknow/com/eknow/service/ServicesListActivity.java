package eknow.com.eknow.service;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import eknow.com.eknow.R;

/**
 * Created by jianguog on 16/11/28.
 */

public class ServicesListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ServiceListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_list);

        adapter = new ServiceListAdapter(ServiceRepository.getOriginalActorList());
        recyclerView = (RecyclerView) findViewById(R.id.servicesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
    }


}
