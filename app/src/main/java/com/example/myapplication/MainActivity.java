package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

import com.pushtorefresh.storio3.sqlite.StorIOSQLite;
import com.pushtorefresh.storio3.sqlite.impl.DefaultStorIOSQLite;
import com.pushtorefresh.storio3.sqlite.queries.Query;


public class MainActivity extends AppCompatActivity {
    private SQLiteOpenHelper helper;
    private Button buttonMenu;
    private ImageView LogoMainImageButton;
    private LinearLayout leftMenu;
    private LinearLayout topBar;
    private DataBaseChanger mDataBaseChanger;
    private RecyclerView mRecyclerView;
    private ItemAdapter adapter;
    private String DateCompletedString = "empty";
    private static boolean flag = false;

    public static void setFlag(boolean newFlag){
        flag = newFlag;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        leftMenu = findViewById(R.id.leftMenu);
        buttonMenu = findViewById(R.id.buttonMenu);

        mDataBaseChanger = new DataBaseChanger(this);
        if(flag){
            DateCompletedString = getIntent().getStringExtra("DateCompleted");
            Log.e("aasddasd", DateCompletedString );
            mDataBaseChanger.Insert(getIntent().getStringExtra("NameNewTask"), getIntent().getStringExtra("BodyNewTask"), getIntent().getStringExtra("DateCompleted"));
        }
        LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(llm);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), llm.getOrientation());



        adapter = new ItemAdapter(mDataBaseChanger.GetData());
        mRecyclerView.setAdapter(adapter);

        Log.e("asd", mDataBaseChanger.GetData().get(0).getNameTask());


        /*buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                leftMenu.setVisibility(View.VISIBLE);
            }
        });*/
        topBar = findViewById(R.id.topBar);
/*        topBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leftMenu.setVisibility(View.GONE);
            }
        });*/



    }

    class ItemAdapter extends RecyclerView.Adapter<Item>{

        private ArrayList<Task> Tasks;

        public ItemAdapter(ArrayList<Task> tasks){Tasks = tasks;}

        @Override
        public Item onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.item, parent, false);
            return new Item(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Item holder, int position) {
            Task task = Tasks.get(position);
            holder.bindCrime(task);
        }

        @Override
        public int getItemCount() {
            return Tasks.size();
        }
    }

    class Item extends RecyclerView.ViewHolder {
        private TextView Name;
        private TextView Body;
        private TextView dateCompleted;


        public Item(View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.Name);
            Body = itemView.findViewById(R.id.Body);
            dateCompleted = itemView.findViewById(R.id.dateCompleted);
        }

        public void bindCrime(Task task){
            Name.setText(task.getNameTask());
            Body.setText(task.getBodyTask());
            dateCompleted.setText(task.getDateCompleted());
        }
    }


    public void OpenFormNewTask(View view) {
        Intent intent = new Intent(MainActivity.this,TaskFormActivity.class);
        startActivity(intent);
        finish();
    }

}




