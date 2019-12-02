package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DataBaseChanger{
    //private SQLiteDatabase db;

    private DataBaseActivity helper;
    private SQLiteDatabase db;

    public DataBaseChanger(Context context){
        helper = new DataBaseActivity(context);
    }

    public void Insert(String name,String body, String dateCompleted){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(Constants.NAME_TASK, name);
        value.put(Constants.BODY_TASK, body);
        value.put(Constants.DATE_ADDED, new Date().toString());
        value.put(Constants.DATE_COMPLETED, dateCompleted);
        db.insert("Test_Table", null, value);
    }

    public ArrayList<Task> GetData(){

        db = helper.getReadableDatabase();
        String[] NazvaniyaStolbtsov = {
                Constants.ID,
                Constants.NAME_TASK,
                Constants.BODY_TASK,
                Constants.DATE_ADDED,
                Constants.DATE_COMPLETED
        };
        Cursor cursor = db.query(
            "Test_Table",
                NazvaniyaStolbtsov,
                null,
                null,
                null,
                null,
                null
        );

        ArrayList<Task> Tasks = new ArrayList<Task>();

        try{
            String header = "\n|" + Constants.ID + " - " + Constants.NAME_TASK + " - " + Constants.BODY_TASK + " - " +
             Constants.DATE_ADDED + " - " + Constants.DATE_COMPLETED + "|";

            int IDColumnIndex = cursor.getColumnIndex(Constants.ID);
            int NameTaskColumnIndex = cursor.getColumnIndex(Constants.NAME_TASK);
            int BodyTaskColumnIndex = cursor.getColumnIndex(Constants.BODY_TASK);
            int DateAddedColumnIndex = cursor.getColumnIndex(Constants.DATE_ADDED);
            int DateCompletedColumnIndex = cursor.getColumnIndex(Constants.DATE_COMPLETED);

            while(cursor.moveToNext()){
                int currentID = cursor.getInt(IDColumnIndex);
                String nameTask = cursor.getString(NameTaskColumnIndex);
                String bodyTask = cursor.getString(BodyTaskColumnIndex);
                String dateAdded = cursor.getString(DateAddedColumnIndex);
                String dateCompleted = cursor.getString(DateCompletedColumnIndex);
                Tasks.add(new Task(currentID, nameTask, bodyTask, dateAdded, dateCompleted));
            }
        }
        finally {
            cursor.close();
        }

        return Tasks;
    }

}
