package com.example.espresso;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.espresso.model.Character;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    public static final String ARG_CHARACTER = "SELECTED_CHARACTER";
    @BindView(R.id.text_role)
    TextView mRoleTextView;
    @BindView(R.id.image_portrait)
    ImageView mPortraitImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Character character = getIntent().getParcelableExtra(ARG_CHARACTER);
        setTitle(character.getName());
        Glide.with(this)
                .load(character.getPortrait())
                .placeholder(R.mipmap.ic_launcher)
                .into(mPortraitImageView);
        mRoleTextView.setText(character.getRole());
    }
}
