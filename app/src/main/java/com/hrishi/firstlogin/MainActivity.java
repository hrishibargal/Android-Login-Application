package com.hrishi.firstlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Retrofit retrofit;

    LoginApi loginApi;
    EditText emailText;
    EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         emailText=findViewById(R.id.emailId);
         pass=findViewById(R.id.passId);
        Button btn=findViewById(R.id.btn);

        retrofit=new Retrofit.Builder().baseUrl("http://192.168.43.216:8000/").addConverterFactory(GsonConverterFactory.create()).build();
         loginApi=retrofit.create(LoginApi.class);

         btn.setOnClickListener(v -> login());





    }
    private void login(){
        String mail=emailText.getText().toString();
        String password=pass.getText().toString();

        Call<Token> call=loginApi.token(mail,password);
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                Token token=response.body();
                if (response.isSuccessful()){
//                    Toast.makeText(MainActivity.this, token.getToken(), Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(MainActivity.this, Detail.class);
                    intent.putExtra("token", token.getToken());
                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this, "Invalid login details", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
