package eknow.com.eknow.service;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by jianguog on 16/12/5.
 */

public abstract class EndlessRecyclerOnScrollListener extends
        RecyclerView.OnScrollListener {

    private int previousTotal = 0;
    private boolean loading = true;
    int firstVisibleItem, visibleItemCount, totalItemCount;

    private int currentPage = 1;

    private LinearLayoutManager mLinearLayoutManager;

    public EndlessRecyclerOnScrollListener(
            LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mLinearLayoutManager.getItemCount();
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

        System.out.println("[================");
        System.out.println("visibleItemCount="+visibleItemCount);
        System.out.println("totalItemCount="+totalItemCount);
        System.out.println("firstVisibleItem="+firstVisibleItem);
        System.out.println("previousTotal="+previousTotal);
        System.out.println("loading="+loading);
        System.out.println("currentPage="+currentPage);

        System.out.println("================]");
        if (loading) {
            if (totalItemCount > previousTotal) {
                System.out.println("I am here 1");
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading
                && (totalItemCount - visibleItemCount) <= firstVisibleItem + 1) {
            System.out.println("I am here 2");
            currentPage++;
            onLoadMore(currentPage);
            loading = true;
        }
    }

    public abstract void onLoadMore(int currentPage);
}
