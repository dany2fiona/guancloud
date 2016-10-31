package com.dany.projectdemo.view;

import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
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
import butterknife.OnClick;
import tv.buka.sdk.BukaSDK;

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
    @BindView(R.id.live_btn)
    Button live_start;
    private ListView lvResults;
    private int mPageIndex = 1;
    private boolean isToEnd = false;
    private LiveListAdapter resultAdapter;
    private ArrayList<Room.ResultsBean> resultData;
    private List<Room.ResultsBean> netDbData;

    private final static int MSG_PULL_FROM_END_NO_HAS_MORE = 0x0012;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_PULL_FROM_END_NO_HAS_MORE:
                    stopWaiting();
                    Toast.makeText(MainActivity.this, "已经到底了", Toast.LENGTH_SHORT).show();
                    mRefreshListView.onRefreshComplete();
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //初始化bukaSDK
        setVolumeControlStream(AudioManager.STREAM_VOICE_CALL);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        BukaSDK.getInstance().init("1", getApplicationContext());

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
                        changeRefreshTime();

                        if (refreshView.isHeaderShown()) {
                            // 下拉刷新 业务代码
                            mPageIndex = 1;
                            isToEnd = false;
                            if(netDbData!=null && netDbData.size() >0){
                                netDbData.clear();
                            }
                            mPresenter.loadRoom(MainActivity.this,mPageIndex);

                        } else {
                            // 上拉加载更多 业务代码
                            if (!isToEnd){
                                mPageIndex++;
                                mPresenter.loadRoom(MainActivity.this,mPageIndex);
                            }else{
                                showWaiting();
                                mHandler.sendEmptyMessageDelayed(MSG_PULL_FROM_END_NO_HAS_MORE, 200);
                            }
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
                Intent intent = new Intent(MainActivity.this, PlayActivity.class);
                intent.putExtra("roomid", bean.getRoomid());
                startActivity(intent);

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
        netDbData.addAll(list);
        getResultData();
        mRefreshListView.onRefreshComplete();
    }

    @Override
    public void stopRefresh() {
        mRefreshListView.onRefreshComplete();
    }

    @Override
    public void showToolBarNumber(String total) {
        mTitleTv.setText("直播列表("+total+")");
    }

    @Override
    public void isToEnd(boolean isToEnd) {
        this.isToEnd = isToEnd;
    }


    @Override
    public void setPresenter(Object presenter) {
        mPresenter = (RoomContract.Presenter) presenter;
    }

    @OnClick(R.id.live_btn)
    public void start_live(){
        Intent intent = new Intent(MainActivity.this, LiveActivity.class);
        startActivity(intent);

    }
}
