package com.dany.projectdemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dany.projectdemo.Contract.RoomContract;
import com.dany.projectdemo.Presenter.RoomPresenter;
import com.dany.projectdemo.R;
import com.dany.projectdemo.adapter.LiveListAdapter;
import com.dany.projectdemo.model.Room;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dan.y on 2016/10/27.
 */
public class MainActivity extends BaseActivity implements RoomContract.View {
    private RoomContract.Presenter mPresenter;
    @BindView(R.id.tv_title)
    TextView mTitleTv;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.lv_live)
    PullToRefreshListView mRefreshListView;
    private ListView lvResults;
    private boolean isRefresh;
    private int mPageIndex = 1;
    private LiveListAdapter resultAdapter;
    private ArrayList<Room.ResultsBean> resultData;
    private List<Room.ResultsBean> netDbData;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
        initData();
    }

    private void initViews() {
        new RoomPresenter(this);
        setSupportActionBar(mToolbar);
        mTitleTv.setText("直播列表");
        initListView();
    }

    private void initListView() {
        lvResults = mRefreshListView.getRefreshableView();
        mRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mRefreshListView.getLoadingLayoutProxy(true, true).setLoadingDrawable(null);
        mRefreshListView.getLoadingLayoutProxy(true, true).setReleaseLabel(
                "松开加载更多");
        mRefreshListView.getLoadingLayoutProxy(false, true)
                .setPullLabel("上拉刷新");
        changeRefreshTime();

        // 设置上拉下拉事件
        mRefreshListView
                .setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                    @Override
                    public void onRefresh(
                            PullToRefreshBase<ListView> refreshView) {
                        isRefresh = true;
                        changeRefreshTime();

                        if (refreshView.isHeaderShown()) {
                            // 下拉刷新 业务代码
                            mPageIndex = 1;
                            if(netDbData!=null && netDbData.size() >0){
                                netDbData.clear();
                            }
                            mPresenter.loadRoom(MainActivity.this,mPageIndex);

                        } else {
                            // 上拉加载更多 业务代码
                            mPageIndex++;
                            mPresenter.loadRoom(MainActivity.this,mPageIndex);
                        }
                    }

                });

        lvResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    int position, long l) {
                position--;
                // 跳转到播放页面
                Room.ResultsBean bean = resultData.get(position);
                Toast.makeText(MainActivity.this, bean.toString(), Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getActivity(), DetailActivity.class);
//                intent.putExtra("goodsdata", bean);
//                startActivity(intent);
            }
        });

    }

    private void changeRefreshTime() {
        String label = DateUtils.formatDateTime(this
                        .getApplicationContext(), System.currentTimeMillis(),
                DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE
                        | DateUtils.FORMAT_ABBREV_ALL);
        mRefreshListView.getLoadingLayoutProxy(true, true)
                .setLastUpdatedLabel(label);
    }


    private void initData() {
        netDbData = new ArrayList<Room.ResultsBean>();
        getResultData();
        mPresenter.loadRoom(MainActivity.this,mPageIndex);
    }

    private void getResultData() {
        if (resultData == null) {
            // 初始化
            resultData = new ArrayList<>();
        } else {
            resultData.clear();
            for (int i = 0; i < netDbData.size(); i++) {
                resultData.add(netDbData.get(i));
            }
        }
        if (resultAdapter == null) {
            resultAdapter = new LiveListAdapter(this, resultData);
            lvResults.setAdapter(resultAdapter);
        } else {
            resultAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showRoom(List<Room.ResultsBean> list) {
        Log.i("dan.y", list.size() + "");
        isRefresh = true;
        netDbData.addAll(list);
        getResultData();
        if (isRefresh) {
            // Call onRefreshComplete when the list has been refreshed.
            mRefreshListView.onRefreshComplete();
            isRefresh = false;
        }
    }

    @Override
    public void setPresenter(Object presenter) {
        mPresenter = (RoomContract.Presenter) presenter;
    }
}
