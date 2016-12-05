package eknow.com.eknow.service;

import android.database.Observable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.concurrent.TimeUnit;

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

        adapter = new ServiceListAdapter(ServiceRepository.getOriginalServicesList());
        recyclerView = (RecyclerView) findViewById(R.id.servicesList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                simulateLoadMoreData(currentPage);
            }
        });
    }

    private void simulateLoadMoreData(int currentPage) {
        adapter.addMoreSerices(ServiceRepository.addMoreActorList(currentPage));
        adapter.notifyDataSetChanged();
    }


}
