package com.example.espresso;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.edit_name)
    EditText mNameEditText;
    @BindView(R.id.button_greeting)
    Button mGreetingButton;
    @BindView(R.id.text_greeting_unique)
    TextView mGreetingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_greeting)
    void onGreet() {
        if (TextUtils.isEmpty(mNameEditText.getText().toString())) {
            mGreetingTextView.setText("Hello there");
        } else {
            mGreetingTextView.setText("Hello " + mNameEditText.getText().toString());
        }
    }
}
