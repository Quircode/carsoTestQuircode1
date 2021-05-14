package com.quircode.testcarso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.quircode.testcarso.Contract.MovieListContract;
import com.quircode.testcarso.Model.Pelis;
import com.quircode.testcarso.Presenter.PelisPresenter;
import com.quircode.testcarso.View.recyclerPelisAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieListContract.View {

    private PelisPresenter pelisPresenter;
    private RecyclerView recyclerPelis;
    private List<Pelis> pelisList;
    private recyclerPelisAdapter recyclerPelisAdapter;
    private ProgressBar loader;
    private int pageNo = 1;

    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pelisList = new ArrayList<>();

        recyclerPelis = (RecyclerView)findViewById(R.id.recyclerPelis);
        loader = (ProgressBar) findViewById(R.id.loader);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerPelis.setLayoutManager(layoutManager);

        pelisPresenter = new PelisPresenter(this);
        pelisPresenter.requestFromServer();
    }

    @Override
    public void showProgressBar() {
        loader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        loader.setVisibility(View.GONE);
    }

    @Override
    public void setDataToRecyclerView(List<Pelis> movieListArray) {
        pelisList.addAll(movieListArray);
        recyclerPelisAdapter = new recyclerPelisAdapter(pelisList, MainActivity.this);
        recyclerPelis.setAdapter(recyclerPelisAdapter);
        recyclerPelisAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, insiderActivity.class);
                intent.putExtra("title",pelisList.get(recyclerPelis.getChildAdapterPosition(view)).getTitle());
                intent.putExtra("res",pelisList.get(recyclerPelis.getChildAdapterPosition(view)).getOverview());
                intent.putExtra("date",pelisList.get(recyclerPelis.getChildAdapterPosition(view)).getReleaseDate());
                intent.putExtra("pop",String.valueOf(pelisList.get(recyclerPelis.getChildAdapterPosition(view)).getPopularity()));
                intent.putExtra("cal",String.valueOf(pelisList.get(recyclerPelis.getChildAdapterPosition(view)).getVoteAverage()));
                intent.putExtra("img",pelisList.get(recyclerPelis.getChildAdapterPosition(view)).getPosterPath());

                startActivity(intent);
            }
        });
    }

    @Override
    public void onResponseFailure(Throwable t) {
        Log.e("ERROR: ",t.getMessage());
        Toast.makeText(this,"No se pudo adquirir los datos :(",Toast.LENGTH_LONG).show();
    }
}