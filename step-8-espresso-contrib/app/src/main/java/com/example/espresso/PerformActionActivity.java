package com.example.espresso;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PerformActionActivity extends AppCompatActivity {
    @BindView(R.id.edit_animal)
    EditText mAnimalEdit;
    @BindView(R.id.button_buy)
    Button mBuyButton;
    @BindView(R.id.text_result)
    TextView mResultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perform_action);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.button_buy)
    void buy() {
        mResultText.setText("You bought a " + mAnimalEdit.getText().toString());
    }

    @OnClick({R.id.dog, R.id.cat, R.id.horse, R.id.fish, R.id.dragon, R.id.seahorse, R.id.snake, R.id.ant})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.dog:
                mResultText.setText("You bought a dog");
                break;
            case R.id.cat:
                mResultText.setText("You bought a cat");
                break;
            case R.id.horse:
                mResultText.setText("You bought a horse");
                break;
            case R.id.fish:
                mResultText.setText("You bought a fish");
                break;
            case R.id.dragon:
                mResultText.setText("You bought a dragon");
                break;
            case R.id.seahorse:
                mResultText.setText("You bought a sea horse");
                break;
            case R.id.snake:
                mResultText.setText("You bought a snake");
                break;
            case R.id.ant:
                mResultText.setText("You bought a ant");
                break;
        }
    }
}
