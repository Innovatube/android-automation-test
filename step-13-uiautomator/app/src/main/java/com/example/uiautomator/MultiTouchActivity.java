package com.example.uiautomator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MultiTouchActivity extends AppCompatActivity implements View.OnTouchListener {

    @BindView(R.id.text_touch_count)
    TextView mTouchCountTextView;
    @BindView(R.id.activity_multi_touch)
    RelativeLayout mTouchLayout;
    private int mTouchCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_touch);
        ButterKnife.bind(this);
        mTouchLayout.setOnTouchListener(this);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int maskedAction = event.getActionMasked();
        switch (maskedAction) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                mTouchCount++;
                break;
        }
        if (mTouchCount < 0) {
            mTouchCount = 0;
        }
        mTouchCountTextView.setText(String.valueOf(mTouchCount));
        return true;
    }
}
