package com.ravimhzn.recyclerviewwithmvvm.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ravimhzn.recyclerviewwithmvvm.R;
import com.ravimhzn.recyclerviewwithmvvm.adapters.RecyclerAdapter;
import com.ravimhzn.recyclerviewwithmvvm.models.Places;
import com.ravimhzn.recyclerviewwithmvvm.viewmodels.MainActivityViewModel;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private RecyclerAdapter recyclerAdapter;
    private MainActivityViewModel mainActivityViewModel;
    private FloatingActionButton fButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        fButton = (FloatingActionButton) findViewById(R.id.fButton);
        fButton.setOnClickListener(this);
        getDataFromRepository(); //Usually we get it from Server;
        setUpRecyclerView();
    }

    private void getDataFromRepository() {
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        mainActivityViewModel.init();

        mainActivityViewModel.getPlaces().observe(this, new Observer<List<Places>>() {
            @Override
            public void onChanged(@Nullable List<Places> nicePlaces) {
                recyclerAdapter.notifyDataSetChanged();
            }
        });

        mainActivityViewModel.getIsUpdating().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    showProgress();
                } else {
                    hideProgress();
                    recyclerView.smoothScrollToPosition(mainActivityViewModel.getPlaces().getValue().size() - 1);
                }
            }
        });
    }

    private void setUpRecyclerView() {
        recyclerAdapter = new RecyclerAdapter(mainActivityViewModel.getPlaces().getValue(), this);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerAdapter);
    }

    private void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fButton:
                Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();
                addNewRow();
                break;
        }
    }

    private void addNewRow() {
        mainActivityViewModel.addNewValue(
                new Places(
                        "https://i.imgur.com/ZcLLrkY.jpg",
                        "Washington"
                )
        );
    }
}
