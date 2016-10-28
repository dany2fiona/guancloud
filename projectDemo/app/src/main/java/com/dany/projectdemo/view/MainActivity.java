package com.dany.projectdemo.view;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.dany.projectdemo.Contract.RoomContract;
import com.dany.projectdemo.Presenter.RoomPresenter;
import com.dany.projectdemo.R;
import com.dany.projectdemo.model.Room;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dan.y on 2016/10/27.
 */
public class MainActivity extends BaseActivity implements RoomContract.View {

    @BindView(R.id.tv_test)
    TextView mTestTv;
    @BindView(R.id.tv_title)
    TextView mTitleTv;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    private RoomContract.Presenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
        initData();
    }

    private void initViews() {
        setSupportActionBar(mToolbar);
        mTitleTv.setText("直播列表");
        new RoomPresenter(this);
    }

    private void initData() {
        mPresenter.start();
        mPresenter.loadRoom();
    }

    @Override
    public void showRoom(List<Room.ResultsBean> list) {
        Log.i("dan.y", list.size() + "");
        StringBuffer sb = new StringBuffer();
        for (Room.ResultsBean resultsBeen :
                list) {
            sb.append(resultsBeen.toString());
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        mTestTv.setText(sb.toString());
    }

    @Override
    public void showDialog() {
        showWaiting();
    }

    @Override
    public void stopDialog() {
        stopWaiting();
    }

    @Override
    public void setPresenter(Object presenter) {
        mPresenter = (RoomContract.Presenter) presenter;
    }
}
