package com.zhoujf.myiterator;

import android.content.Context;
import android.graphics.Canvas;
import android.icu.text.MeasureFormat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.security.cert.TrustAnchor;

/**
 * Created by ZhouJF on 2017-05-21.
 */

public class Itrater extends RelativeLayout {
    private static final String TAG = "Itrater";
    private String[] TITLES;// = {"0000", "1111", "2222", "3333", "4444", "5555", "6666", "7777", "8888", "9999", "1010", "1111", "1212", "1313"};
    private Context mContext;
    private TextView mTextView;
    private ImageView mImageView;
    private float offset = 0;
    private float position = 0;
    private RelativeLayout mRelativeLayou;
    private float mposition;
    private StringBuffer mStringBuffer;


    public Itrater(Context context) {
        this(context, null);
    }

    public Itrater(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();

    }
    private void init() {
        View.inflate(mContext, R.layout.view_itrater, this);
        mTextView = (TextView) findViewById(R.id.tv_itrater);
        mImageView = (ImageView) findViewById(R.id.iv_itrater);
        mRelativeLayou = (RelativeLayout) findViewById(R.id.rl_itrater);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(2000, MeasureSpec.EXACTLY);

        mImageView.measure(childWidthMeasureSpec, heightMeasureSpec);
        mTextView.measure(childWidthMeasureSpec, heightMeasureSpec);
        mRelativeLayou.measure(childWidthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int height = mTextView.getMeasuredHeight();
        int ivHeight = mRelativeLayou.getMeasuredHeight();
        int measuredWidth = mImageView.getMeasuredWidth();

        int left;
        if (position < 2) {
            left = 0;
        } else if (position > TITLES.length - 6) {

            left =  -(TITLES.length -7)* 130;
        } else {
            left = (int) (-(position - 2 + offset) * 130);
        }
        mTextView.layout(0,0,2000,height);
        mRelativeLayou.layout(left, 0, 2000, ivHeight);
        int ivLeft = 0;
        if (position > mposition) {
            ivLeft = (int) ((position + offset) * 220) - measuredWidth/2-920;

        } else {
            ivLeft = (int) ((position + offset) * 220) - measuredWidth/2-920;
        }
        int ivTop;
        if (offset<0.5f){
            if (offset<0.25f){
                ivTop = (int) (ivHeight/4 * offset) +45 ;
            }else {
                ivTop = (int) (ivHeight/4 *(0.5f- offset)) + 45;
            }

        }else {
            if (offset<0.75){
                ivTop = (int) -(ivHeight/4 * (offset-0.5f)) +30 ;
            }else {
                ivTop = (int) -(ivHeight/4 *(1.0f- offset)) + 30;
            }
        }

        mImageView.layout(ivLeft, ivTop , 2000,ivTop+38);
        mposition = (int) position;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    public void setOffset(int position, float offset) {
        this.position = position;
        this.offset = offset;
        requestLayout();

    }

    public void setTitle(String[] titles) {
        TITLES = titles;
        mStringBuffer = new StringBuffer();
        if (TITLES != null) {
            for (int i = 0; i < TITLES.length; i++) {
                mStringBuffer.append(TITLES[i]).append("  ");

            }
        }
        mTextView.setText(mStringBuffer);
    }
}
