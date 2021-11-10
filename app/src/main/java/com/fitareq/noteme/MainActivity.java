package com.fitareq.noteme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fitareq.noteme.databinding.ActivityMainBinding;

import java.util.List;


public class MainActivity extends AppCompatActivity implements mAdapter.AdapterOnClickListener {


    private ActivityMainBinding binding;
    private mViewModel viewModel;
    private mAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View v = binding.getRoot();
        setContentView(v);

        viewModel = new ViewModelProvider(this).get(mViewModel.class);

        binding.allNotes.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        viewModel.getData().observe(this, dataClasses -> {
            adapter = new mAdapter(dataClasses,this);
            binding.allNotes.setAdapter(adapter);
        });
        binding.addNoteFab.setOnClickListener(v1 -> {
            startActivity(new Intent(getApplicationContext(), AddNoteActivity.class));
        });

    }



    @Override
    public void itemClickListener(Long id) {

    }



    @Override
    public void editClickListener(Long id) {

    }



    @Override
    public void deleteClickListener(DataClass dataClass) {
        viewModel.deleteData(dataClass);
    }




}