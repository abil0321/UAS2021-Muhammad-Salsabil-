package com.example.muhammadsalsabiluas2021.AllActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.muhammadsalsabiluas2021.UserPreferences;

public class SplashScreeenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (UserPreferences.isLogin(this)) {
            startActivity(new Intent(this, UserInfoActivity.class));
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
