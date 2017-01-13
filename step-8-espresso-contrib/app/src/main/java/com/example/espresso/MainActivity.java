package com.example.espresso;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, DatePickerFragment.DateSetListener {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.edit_name)
    EditText mNameEditText;
    @BindView(R.id.button_greeting)
    Button mGreetingButton;
    @BindView(R.id.text_greeting_unique)
    TextView mGreetingTextView;
    @BindView(R.id.edit_bod)
    EditText mBodEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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
            case R.id.action_simple:
                startActivity(new Intent(this, SimpleActivity.class));
                break;
            case R.id.action_listview:
                startActivity(new Intent(this, ListViewActivity.class));
                break;

            case R.id.action_recyclerview:
                startActivity(new Intent(this, RecyclerViewActivity.class));
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

    @OnClick(R.id.edit_bod)
    void showDatePicker() {
        DatePickerFragment datePicker = new DatePickerFragment();
        datePicker.show(getSupportFragmentManager(), DatePickerFragment.class.getSimpleName());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_list:
                startActivity(new Intent(this, ListViewActivity.class));
                return true;
            case R.id.nav_contact:
                startActivity(new Intent(this, ProfileActivity.class));
                return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public void onDateSet(int day, int month, int year) {
        mBodEditText.setText(day + "/" + month + "/" + year);
    }
}
