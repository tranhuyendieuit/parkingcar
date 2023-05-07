package com.example.managerpakingcar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.managerpakingcar.api.ApiService;
import com.example.managerpakingcar.constant.Constant;
import com.example.managerpakingcar.model.request.SignInRequestModel;
import com.example.managerpakingcar.model.response.BaseResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    private EditText editUserName;
    private EditText editPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //for changing status bar icon colors
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_login);

        editUserName = findViewById(R.id.editTextUserName);
        editPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.cirLoginButton);
        btnLogin.setOnClickListener(view -> postListUsers(editUserName.getText().toString().trim(), editPassword.getText().toString().trim()));
    }

    private void postListUsers(String userName, String password) {

        SignInRequestModel userRequestModel = new SignInRequestModel();
        userRequestModel.setUserName(userName);
        userRequestModel.setPassword(password);
        ApiService.apiService.signIn(userRequestModel).enqueue(new Callback<BaseResponseModel<String>>() {
            @Override
            public void onResponse(Call<BaseResponseModel<String>> call, Response<BaseResponseModel<String>> response) {
                BaseResponseModel<String> stringBaseResponseModel = response.body();
                if (stringBaseResponseModel.getStatus() == 200) {
                    String token = stringBaseResponseModel.getData();
                    Constant.TOKEN = token;
                    // send token to MainActivity
                    startActivity(new Intent(LoginActivity.this, MainActivity.class).putExtra("data", token));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<BaseResponseModel<String>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage()+"", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onLoginClick(View View) {
        startActivity(new Intent(this, RegisterActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);

    }
}
