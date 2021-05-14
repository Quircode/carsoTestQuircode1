package com.quircode.testcarso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.quircode.testcarso.Net.ApiNetClient;

public class insiderActivity extends AppCompatActivity {
    ImageView imgPeli;
    TextView titlePeli,reseñaPeli,estrenoPeli,popularPeli,calPeli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insider);

        imgPeli = (ImageView)findViewById(R.id.imgPeli);
        titlePeli = (TextView)findViewById(R.id.titlePeli);
        reseñaPeli = (TextView)findViewById(R.id.reseñaPeli);
        estrenoPeli = (TextView)findViewById(R.id.estrenoPeli);
        popularPeli = (TextView)findViewById(R.id.popularPeli);
        calPeli = (TextView)findViewById(R.id.calPeli);

        setData();
    }

    private void setData(){
        titlePeli.setText(getIntent().getStringExtra("title"));
        reseñaPeli.setText(getIntent().getStringExtra("res"));
        estrenoPeli.setText(getIntent().getStringExtra("date"));
        popularPeli.setText(getIntent().getStringExtra("pop"));
        calPeli.setText(getIntent().getStringExtra("cal"));
        Glide.with(this).load(ApiNetClient.URL_IMAGE+getIntent().getStringExtra("img")).into(imgPeli);
    }
}