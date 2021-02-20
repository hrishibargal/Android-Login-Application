package com.hrishi.firstlogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;

public class Detail extends AppCompatActivity {
    Retrofit retrofit;
    TextView text;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String token = getIntent().getStringExtra("token");
        String tokenn = "Token " + token;
        retrofit = new Retrofit.Builder().baseUrl(LoginApi.DJANGO_SITE).addConverterFactory(GsonConverterFactory.create()).build();
        LoginApi loginApi = retrofit.create(LoginApi.class);
        ImageView imageView = findViewById(R.id.image);
        text = findViewById(R.id.text);
        recyclerView=findViewById(R.id.recylcer);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        Call<List<Body>> call = loginApi.getImage(tokenn);
        call.enqueue(new Callback<List<Body>>() {
            @Override
            public void onResponse(Call<List<Body>> call, Response<List<Body>> response) {
                List<Body> posts = response.body();
                if (response.isSuccessful()) {
//                    Toast.makeText(Detail.this, "Success", Toast.LENGTH_SHORT).show();
                    if (response.body() != null) {
//                        Toast.makeText(Detail.this, "Success is", Toast.LENGTH_SHORT).show();
                        // display the image data in a ImageView or save ito
                        ListAdapter listAdapter = new ListAdapter(posts);
                        System.out.println(posts);
                        recyclerView.setAdapter(listAdapter);

                    }else{
                        Toast.makeText(Detail.this, "Response is null", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(Detail.this, "Resoponse failed", Toast.LENGTH_SHORT).show();
                }

            }
                @Override
                public void onFailure (Call < List < Body >> call, Throwable t){
                    Log.d("Failed: ", t.getMessage());
                }
            });
        }

    }
