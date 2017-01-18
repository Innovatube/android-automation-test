package com.example.uiautomator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class GreetingFragment extends Fragment {


    @BindView(R.id.edit_name)
    EditText mNameEditText;
    @BindView(R.id.text_greeting)
    TextView mGreetingText;

    public GreetingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_greeting, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.button_greeting)
    public void onClick() {
        if (TextUtils.isEmpty(mNameEditText.getText().toString())) {
            mGreetingText.setText("Hello there!");
        } else {
            mGreetingText.setText(getString(R.string.hello, mNameEditText.getText().toString()));
        }
    }
}
