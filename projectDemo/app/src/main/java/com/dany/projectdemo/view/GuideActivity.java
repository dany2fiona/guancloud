package com.dany.projectdemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.dany.projectdemo.R;
import com.dany.projectdemo.common.utils.MyCookie;
import com.viewpagerindicator.view.indicator.FixedIndicatorView;
import com.viewpagerindicator.view.indicator.Indicator;
import com.viewpagerindicator.view.indicator.IndicatorViewPager;

/**
 * Created by dan.y on 2016/10/27.
 */
public class GuideActivity extends AppCompatActivity {
    private IndicatorViewPager indicatorViewPager;
    private LayoutInflater inflate;
    private  final static int[] images = {R.mipmap.link_page1, R.mipmap.link_page2, R.mipmap.link_page3};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.guide_viewPager);
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(viewPager.getCurrentItem()==images.length-2){
                    hideIndicator();
                }
                if(viewPager.getCurrentItem()==images.length-1)
                {
                    return true;
                }
                return false;
            }
        });
        Indicator indicator = (Indicator) findViewById(R.id.guide_indicator);
        indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
        inflate = LayoutInflater.from(getApplicationContext());
        indicatorViewPager.setAdapter(adapter);

    }

    final static class GuideViewHolder {
        FrameLayout iv_guide;
        Button btnGuide;
        Button guideSkipBtn;
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            //设置已经引导过了，下次启动不用再次引导
            MyCookie.getInstance().putIsFirstIn(false);
            Intent intent = new Intent(GuideActivity.this, LoginActivity.class);
            startActivity(intent);
            GuideActivity.this.finish();
        }
    };

    private IndicatorViewPager.IndicatorPagerAdapter adapter = new IndicatorViewPager.IndicatorViewPagerAdapter() {
        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = inflate.inflate(R.layout.tab_guide, container, false);
            }
            return convertView;
        }

        @Override
        public View getViewForPage(int position, View convertView, ViewGroup container) {
            GuideViewHolder viewHolder = null;

            if (convertView == null) {
                viewHolder = new GuideViewHolder();
                convertView = inflate.inflate(R.layout.tab_guide_content, container, false);
                viewHolder.iv_guide = (FrameLayout) convertView
                        .findViewById(R.id.iv_guide);
                viewHolder.btnGuide = (Button) convertView
                        .findViewById(R.id.btnGuide);
                viewHolder.btnGuide.setVisibility(View.GONE);
                viewHolder.guideSkipBtn = (Button) convertView
                        .findViewById(R.id.guideSkipBtn);
                viewHolder.guideSkipBtn.setOnClickListener(clickListener);
//                viewHolder.guideContent = (TextView) convertView.findViewById(R.id.guideContent);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (GuideViewHolder) convertView.getTag();
            }
            viewHolder.iv_guide.setBackgroundResource(images[position]);
            if (position == getCount() - 1) {
                viewHolder.guideSkipBtn.setVisibility(View.GONE);
                viewHolder.btnGuide.setVisibility(View.VISIBLE);
                viewHolder.btnGuide.setOnClickListener(clickListener);
//                convertView.setOnClickListener(clickListener);
            }else{
                viewHolder.guideSkipBtn.setVisibility(View.VISIBLE);
            }
            return convertView;
        }

        @Override
        public int getCount() {
            return images.length;
        }

    };

    private void hideIndicator(){
        FixedIndicatorView indicator = (FixedIndicatorView) indicatorViewPager.getIndicatorView();
//        ViewGroup.LayoutParams layoutParams = indicator.getLayoutParams();
//        layoutParams.height = 0;
//        indicator.requestLayout();
        indicator.setVisibility(View.GONE);
    }

    private void showIndicator(){
        FixedIndicatorView indicator = (FixedIndicatorView) indicatorViewPager.getIndicatorView();
        indicator.setVisibility(View.VISIBLE);
    }

}
