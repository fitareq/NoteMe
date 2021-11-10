package com.fitareq.noteme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.fitareq.noteme.databinding.ActivityDetailsBinding;

public class DetailsActivity extends AppCompatActivity {

    private ActivityDetailsBinding binding;
    private mViewModel viewModel;
    DataClass dtClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        View v = binding.getRoot();
        setContentView(v);

        Long id = getIntent().getLongExtra("id",0);


        viewModel = new ViewModelProvider(this).get(mViewModel.class);
        viewModel.getDetailsData(id).observe(this, dataClass -> {

            binding.taskDetailsCreatedDate.setText(dataClass.getCreatedDate());
            binding.taskDetailsStatus.setText(dataClass.getStatus());
            binding.taskDetailsNoteTitle.setText(dataClass.getTaskName());
            binding.taskDetailsNoteDescription.setText(dataClass.getDescription());
            binding.taskDetailsDeadlineDate.setText(dataClass.getDeadline());
        });

        binding.taskDetailsDelete.setOnClickListener(view -> {
            viewModel.deleteDetailsData(id);

        });
        binding.taskDetailsEditFab.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), EditActivity.class);
            intent.putExtra("id", id);
            startActivity(intent);
        });

    }


}