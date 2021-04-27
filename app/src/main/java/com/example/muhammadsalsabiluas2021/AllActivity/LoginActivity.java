package com.example.muhammadsalsabiluas2021.AllActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.muhammadsalsabiluas2021.AuthorizationResponse;
import com.example.muhammadsalsabiluas2021.R;
import com.example.muhammadsalsabiluas2021.ServiceClient;
import com.example.muhammadsalsabiluas2021.UserPreferences;
import com.example.muhammadsalsabiluas2021.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText nama;
    private EditText password;

    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nama = findViewById(R.id.lgn_nama);
        password = findViewById(R.id.lgn_password);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nama.getText().toString();
                String passwordL = password.getText().toString();
                if (name.isEmpty()) {
                    nama.setError("nama Empty");
                    return;
                }

                if (passwordL.isEmpty()) {
                    password.setError("Password Empty");
                    return;
                }

                Utils.hideSoftKey(nama);

                doLogin(name, passwordL);
            }
        });
    }
    private void doLogin(final String namad, String passwordd){

        nama.setEnabled(false);
        password.setEnabled(false);
        btnLogin.setEnabled(false);

        String authorization = "Basic " + Utils.base64Encode(namad, passwordd);

        ServiceClient.buildServiceClient().login(authorization).enqueue(new Callback<AuthorizationResponse>() {
            @Override
            public void onResponse(Call<AuthorizationResponse> call, Response<AuthorizationResponse> response) {
                if (response.isSuccessful()) {

                    AuthorizationResponse auth = response.body();

                    UserPreferences.setTokenUser(LoginActivity.this, auth.getToken());
                    UserPreferences.setUserId(HomePage.this, auth.getLoggedInUser().getId());
                    UserPreferences.hasLogin(HomePage.this);

                    startActivity(new Intent(HomePage.this, UserInfoActivity.class));
                    finish();

                } else {
                    Toast.makeText(HomePage.this, "Unknown login or wrong password for login " + nama, Toast.LENGTH_SHORT).show();
                    Log.d("onResponse", "onResponse: " + response.message());


                    nama.setEnabled(true);
                    password.setEnabled(true);
                    btnLogin.setEnabled(true);
                }
            }

            @Override
            public void onFailure(Call<AuthorizationResponse> call, Throwable t) {
                Toast.makeText(HomePage.this, "Something Wrong!", Toast.LENGTH_SHORT).show();
                Log.d("onFailure", "onFailure: " + t.getMessage());

                nama.setEnabled(true);
                password.setEnabled(true);
                btnLogin.setEnabled(true);
            }
        });

    }
}