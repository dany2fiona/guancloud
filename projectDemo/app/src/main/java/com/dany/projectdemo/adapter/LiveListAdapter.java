package com.dany.projectdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dany.projectdemo.R;
import com.dany.projectdemo.model.Room;
import com.dany.projectdemo.model.Room.ResultsBean;

import java.util.ArrayList;

/**
 * Created by dan.y on 2016/10/31.
 */
public class LiveListAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<ResultsBean> datas;

    public LiveListAdapter(Context context, ArrayList<ResultsBean> datas) {
        super();
        this.mContext = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        Wrapper wrapper;
        TextView nameTv;
        TextView roomIdTv;
        TextView roomAliasTv;
        ImageView roomIv;
        if(row == null){
            row = LayoutInflater.from(mContext).inflate(R.layout.item_bean_livelist, null, false);
            wrapper = new Wrapper(row);
            row.setTag(wrapper);
        }else{
            wrapper = (Wrapper) row.getTag();
        }
        ResultsBean bean = datas.get(position);
        nameTv = wrapper.getNameTv();
        roomIdTv = wrapper.getRoomIdTv();
        roomAliasTv = wrapper.getRoomAliasTv();
        roomIv = wrapper.getRoomIv();

//        nameTv.setText("");
        roomIdTv.setText(bean.getRoomid());
        roomAliasTv.setText(bean.getRoom_alias());
//        roomIv.setImageResource(R.mipmap.ic_launcher);
        return row;
    }

    class Wrapper{
        private TextView nameTv;
        private TextView roomIdTv;
        private TextView roomAliasTv;
        private ImageView roomIv;
        private View row;

        public Wrapper(View row) {
            super();
            this.row = row;
        }

        public TextView getNameTv() {
            if(nameTv==null){
                nameTv = (TextView) row.findViewById(R.id.tv_name);
            }
            return nameTv;
        }
        public TextView getRoomIdTv() {
            if(roomIdTv==null){
                roomIdTv = (TextView) row.findViewById(R.id.tv_roomid);
            }
            return roomIdTv;
        }
        public TextView getRoomAliasTv() {
            if(roomAliasTv==null){
                roomAliasTv = (TextView) row.findViewById(R.id.tv_room_alias);
            }
            return roomAliasTv;
        }
        public ImageView getRoomIv() {
            if(roomIv==null){
                roomIv = (ImageView) row.findViewById(R.id.iv_room);
            }
            return roomIv;
        }

    }

}
