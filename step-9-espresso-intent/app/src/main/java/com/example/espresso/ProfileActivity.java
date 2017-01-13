package com.example.espresso;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class ProfileActivity extends AppCompatActivity {

    private static final int RC_SIGN_UP = 123;
    private static final int RC_PICK_IMAGE = 124;
    public static final int RC_READ_EXTERNAL = 25;
    @BindView(R.id.image_avatar)
    ImageView mAvatarImageView;
    @BindView(R.id.text_user_info)
    TextView mUserInfoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button_sign_up, R.id.button_take_image})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_sign_up:
                Intent contactIntent = new Intent(this, SignUpActivity.class);
                startActivityForResult(contactIntent, RC_SIGN_UP);
                break;
            case R.id.button_take_image:
                takeCamera();
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @AfterPermissionGranted(RC_READ_EXTERNAL)
    private void takeCamera() {
        String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            Intent imageIntent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(imageIntent, RC_PICK_IMAGE);
        } else {
            EasyPermissions.requestPermissions(this, "get image", RC_READ_EXTERNAL, perms);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == RC_SIGN_UP) {
                String fullname = data.getStringExtra(SignUpActivity.EXTRA_FULLNAME);
                mUserInfoTextView.setText(getString(R.string.hello, fullname));
            } else if (requestCode == RC_PICK_IMAGE) {
                final Uri imageUri = data.getData();
                Glide.with(this).load(imageUri).into(mAvatarImageView);
            }
        }
    }
}
