package com.fitareq.noteme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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

        binding.allNotes.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        viewModel.getSpecificData("Open").observe(this,dataClasses -> {
            adapter = new mAdapter(dataClasses, this);
            binding.allNotes.setAdapter(adapter);
        });
        binding.addNoteFab.setOnClickListener(v1 -> {
            startActivity(new Intent(getApplicationContext(), AddNoteActivity.class));
        });

        binding.bottomNav.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.nav_open:
                    viewModel.getSpecificData("Open").observe(this,dataClasses -> {
                        adapter = new mAdapter(dataClasses, this);
                        binding.allNotes.setAdapter(adapter);
                    });
                    break;
                case R.id.nav_in_progress:
                    //viewModel.getSpecificData("Open").removeObservers(this);
                    viewModel.getSpecificData("In-Progress").observe(this,dataClasses -> {
                        adapter = new mAdapter(dataClasses, this);
                        binding.allNotes.setAdapter(adapter);
                    });
                    break;
                case R.id.nav_test:
                    viewModel.getSpecificData("Test").observe(this,dataClasses -> {
                        adapter = new mAdapter(dataClasses, this);
                        binding.allNotes.setAdapter(adapter);
                    });
                    break;
                case R.id.nav_done:
                    viewModel.getSpecificData("Done").observe(this,dataClasses -> {
                        adapter = new mAdapter(dataClasses, this);
                        binding.allNotes.setAdapter(adapter);
                    });
                    break;
            }
            return true;

        });

    }



    @Override
    public void itemClickListener(Long id) {
        Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);

    }



    @Override
    public void editClickListener(Long id) {
        Intent intent = new Intent(getApplicationContext(), EditActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);

    }



    @Override
    public void deleteClickListener(DataClass dataClass) {
        viewModel.deleteData(dataClass);
    }




}