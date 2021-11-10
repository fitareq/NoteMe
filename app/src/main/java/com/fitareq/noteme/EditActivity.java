package com.fitareq.noteme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import com.fitareq.noteme.databinding.ActivityEditBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EditActivity extends AppCompatActivity {

    private ActivityEditBinding binding;
    private mViewModel viewModel;
    String taskName, taskDescription, taskDeadline, taskStatus, taskEmail = null, taskPhone= null, taskUrl = null;
    String[] spinnerItem = {"Open", "In-Progress", "Test", "Done"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditBinding.inflate(getLayoutInflater());
        View v = binding.getRoot();
        setContentView(v);

        Long id = getIntent().getLongExtra("id",0);

        viewModel = new ViewModelProvider(this).get(mViewModel.class);

        viewModel.getDetailsData(id).observe(this, dataClass -> {

            binding.editTaskNameEdtx.setText(dataClass.getCreatedDate());
            binding.editTaskNameEdtx.setText(dataClass.getTaskName());
            binding.editDescriptionEdtx.setText(dataClass.getDescription());
            binding.editTaskDate.setText(dataClass.getDeadline());
        });


        binding.editTaskStatusSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, spinnerItem));
        binding.editTaskSubmitBtn.setOnClickListener(view -> {
            Date d = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
            String createdDate = df.format(d);
            taskName = binding.editTaskNameEdtx.getText().toString();
            taskDescription = binding.editDescriptionEdtx.getText().toString();
            taskDeadline = binding.editTaskDate.getText().toString();
            taskStatus = binding.editTaskStatusSpinner.getSelectedItem().toString();
            DataClass dataClass = new DataClass(id, taskName, createdDate, taskDeadline, taskStatus, taskDescription, taskEmail, taskPhone, taskUrl);
            viewModel.setData(dataClass);
            Toast.makeText(getApplicationContext(), "Note Updated", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));

        });

        binding.editTaskSetDate.setOnClickListener(view -> {
            Calendar c = Calendar.getInstance();
            int mDay = c.get(Calendar.DAY_OF_MONTH);
            int mMonth = c.get(Calendar.MONTH);
            int mYear = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    binding.editTaskDate.setText(dayOfMonth+"."+(month+1)+"."+year);
                }
            },mYear,mMonth,mDay);
            datePickerDialog.show();
        });
    }

}