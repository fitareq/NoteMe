package com.fitareq.noteme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.fitareq.noteme.databinding.ActivityAddNoteBinding;


public class AddNoteActivity extends AppCompatActivity {

    private ActivityAddNoteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityAddNoteBinding.inflate(getLayoutInflater());
        View v = binding.getRoot();
        setContentView(v);
    }




}