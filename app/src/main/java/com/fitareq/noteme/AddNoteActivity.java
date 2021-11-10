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

import com.fitareq.noteme.databinding.ActivityAddNoteBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class AddNoteActivity extends AppCompatActivity {

    private ActivityAddNoteBinding binding;
    private mViewModel viewModel;
    String taskName, taskDescription, taskDeadline, taskStatus, taskEmail = null, taskPhone= null, taskUrl = null;
    String[] spinnerItem = {"Open", "In-Progress", "Test", "Done"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityAddNoteBinding.inflate(getLayoutInflater());
        View v = binding.getRoot();
        setContentView(v);


        viewModel = new ViewModelProvider(this).get(mViewModel.class);
        binding.taskStatusSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, spinnerItem));


        binding.addTaskSubmitBtn.setOnClickListener(v1 -> {
            Date d = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
            String createdDate = df.format(d);
            taskName = binding.taskNameEdtx.getText().toString();
            taskDescription = binding.descriptionEdtx.getText().toString();
            taskDeadline = binding.taskDate.getText().toString();
            taskStatus = binding.taskStatusSpinner.getSelectedItem().toString();
            DataClass dataClass = new DataClass(System.currentTimeMillis(), taskName, createdDate, taskDeadline, taskStatus, taskDescription, taskEmail, taskPhone, taskUrl);
           /* dataClass.setTaskName(taskName);
            dataClass.setDescription(taskDescription);
            dataClass.setDeadline(taskDeadline);
            dataClass.setStatus(taskStatus);
            dataClass.setEmail(taskEmail);
            dataClass.setPhone(taskPhone);
            dataClass.setUrl(taskUrl);
            dataClass.setCreatedDate(createdDate);*/
            viewModel.setData(dataClass);
            Toast.makeText(getApplicationContext(), "Note Added", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        });

        binding.mailImgview.setOnClickListener(v1 -> {

        });
        binding.phoneImgview.setOnClickListener(v1 -> {

        });
        binding.urlImgview.setOnClickListener(v1 -> {

        });


        binding.taskSetDate.setOnClickListener(v1 -> {
            Calendar c = Calendar.getInstance();
            int mDay = c.get(Calendar.DAY_OF_MONTH);
            int mMonth = c.get(Calendar.MONTH);
            int mYear = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    binding.taskDate.setText(dayOfMonth+"."+(month+1)+"."+year);
                }
            },mYear,mMonth,mDay);
            datePickerDialog.show();
        });
    }




}