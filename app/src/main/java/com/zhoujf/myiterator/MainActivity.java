package com.zhoujf.myiterator;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Itrater mItrater;
    private ViewPager mViewPager;
    private String[] TITLES = {"0000", "1111", "2222", "3333", "4444", "5555", "6666", "7777", "8888", "9999", "1010", "1111", "1212", "1313"};
   // private String[] titles = {"0000", "1111", "2222", "3333", "4444", "5555", "6666", "7777"};
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        mItrater = (Itrater) findViewById(R.id.it_main);
        mViewPager = (ViewPager) findViewById(R.id.vp_main);
        mViewPager.setAdapter(mPagerAdapter);
        mItrater.setTitle(TITLES);
        mItrater.setOffset(0, 0);
        mViewPager.addOnPageChangeListener(mOnPageChangeListener);


    }

    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            Log.d(TAG, "onCreate: " + positionOffset);
            Log.d(TAG, "onCreate: " + positionOffsetPixels + "tiaomu:" + position);
            Log.d(TAG, "onCreate: " + position);
            mItrater.setOffset(position, positionOffset);
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    private PagerAdapter mPagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {

            return TITLES.length;

        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView textView = new TextView(mContext);
            textView.setText(TITLES[position]);
            container.addView(textView);
            return textView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    };

}
