package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class TaskFormActivity extends AppCompatActivity {

    EditText nameTask;
    EditText bodyTask;
    DatePicker dateCompletedTask;
    Button submitButton;
    int Year;
    int MonthOfYear;
    int DayOfMonth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_form);
        nameTask = findViewById(R.id.NameTaskInput);
        bodyTask = findViewById(R.id.BodyTaskInput);
        dateCompletedTask = findViewById(R.id.DateCompletedTaskInput);
        submitButton = findViewById(R.id.SubmitNewTask);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(TaskFormActivity.this, MainActivity.class);
        MainActivity.setFlag(false);
        startActivity(intent);
        finish();
    }

    public void SubmitNewTask(View view) {
        Intent intent = new Intent(TaskFormActivity.this, MainActivity.class);
        Year = dateCompletedTask.getYear();
        MonthOfYear = dateCompletedTask.getMonth();
        DayOfMonth = dateCompletedTask.getDayOfMonth();
        intent.putExtra("NameNewTask", nameTask.getText().toString());
        intent.putExtra("BodyNewTask", bodyTask.getText().toString());
        intent.putExtra("DateCompleted", DayOfMonth + " " + MonthOfYear + " " + Year);
        MainActivity.setFlag(true);
        startActivity(intent);
        finish();
    }
}
