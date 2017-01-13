package com.example.espresso;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_checkview:
                startActivity(new Intent(this, CheckViewActivity.class));
                break;

            case R.id.action_perform:
                startActivity(new Intent(this, PerformActionActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
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
