package com.example.espresso;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity {

    public static final String EXTRA_EMAIL = "Email";
    public static final String EXTRA_FULLNAME = "Fullname";
    @BindView(R.id.edit_email)
    EditText mEmailEditText;
    @BindView(R.id.edit_fullname)
    EditText mFullNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_register)
    public void onClick() {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_EMAIL, mEmailEditText.getText().toString());
        intent.putExtra(EXTRA_FULLNAME, mFullNameEditText.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
