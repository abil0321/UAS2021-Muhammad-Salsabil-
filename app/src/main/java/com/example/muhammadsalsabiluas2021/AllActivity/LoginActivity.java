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

    private EditText namaL;
    private EditText passwordL;

    private Button btnLoginL;

//    private ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        namaL = findViewById(R.id.lgn_nama);
        passwordL = findViewById(R.id.lgn_password);

//        loading = findViewById(R.id.loading);

        btnLoginL = findViewById(R.id.btnLogin);
        btnLoginL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String namaS = namaL.getText().toString();
                String passwordS = passwordL.getText().toString();

                if (namaS.isEmpty()) {
                    passwordL.setError("Email Empty");
                    return;
                }

                if (passwordS.isEmpty()) {
                    passwordL.setError("Password Empty");
                    return;
                }

                Utils.hideSoftKey(namaL);

                doLogin(namaS, passwordS);
            }
        });
    }

    private void doLogin(final String namaP, String passwordP) {

//        loading.setVisibility(View.VISIBLE);

        namaL.setEnabled(false);
        passwordL.setEnabled(false);
        btnLoginL.setEnabled(false);

        String authorization = "Basic " + Utils.base64Encode(namaP, passwordP);

        ServiceClient
                .buildServiceClient()
                .login(authorization)
                .enqueue(new Callback<AuthorizationResponse>() {
                    @Override
                    public void onResponse(Call<AuthorizationResponse> call, Response<AuthorizationResponse> response) {

                        if (response.isSuccessful()) {

                            AuthorizationResponse auth = response.body();

                            UserPreferences.setTokenUser(LoginActivity.this, auth.getToken());
                            UserPreferences.setUserId(MainActivity.this, auth.getLoggedInUser().getId());
                            UserPreferences.hasLogin(MainActivity.this);

                            startActivity(new Intent(MainActivity.this, UserInfoActivity.class));
                            finish();

                        } else {
                            Toast.makeText(MainActivity.this, "Unknown login or wrong password for login " + namaP, Toast.LENGTH_SHORT).show();
                            Log.d("onResponse", "onResponse: " + response.message());

//                            loading.setVisibility(View.GONE);

                            namaL.setEnabled(true);
                            passwordL.setEnabled(true);
                            btnLoginL.setEnabled(true);
                        }
                    }

                    @Override
                    public void onFailure(Call<AuthorizationResponse> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Something Wrong!", Toast.LENGTH_SHORT).show();
                        Log.d("onFailure", "onFailure: " + t.getMessage());

//                        loading.setVisibility(View.VISIBLE);

                        namaL.setEnabled(true);
                        passwordL.setEnabled(true);
                        btnLoginL.setEnabled(true);
                    }
                });
    }
}