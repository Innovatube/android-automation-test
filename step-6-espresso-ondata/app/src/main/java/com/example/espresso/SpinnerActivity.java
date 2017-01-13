package com.example.espresso;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class SpinnerActivity extends AppCompatActivity {
    private TextView mSimpleTextView;
    private Button mSimpleButton;
    private Spinner mSimpleSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        mSimpleButton = (Button) findViewById(R.id.button_simple);
        mSimpleTextView = (TextView) findViewById(R.id.text_simple);
        mSimpleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSimpleTextView.setText("Hello Espresso!");
            }
        });
        mSimpleSpinner = (Spinner) findViewById(R.id.spinner_simple);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner, android.R.layout.simple_dropdown_item_1line);
        mSimpleSpinner.setAdapter(adapter);
        mSimpleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSimpleTextView.setText("One " + parent.getItemAtPosition(position).toString()
                        + " a day!");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
