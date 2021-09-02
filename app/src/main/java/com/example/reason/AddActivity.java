package com.example.reason;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.reason.data.DatabaseHandler;
import com.example.reason.model.Item;
import com.example.reason.ui.ItemRecyclerViewAdapter;
import com.example.reason.ui.RecyclerViewAdapter;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    private ItemRecyclerViewAdapter recyclerViewAdapter;
    private List<Item> itemList;

    private AlertDialog alertDialog;
    private AlertDialog.Builder builder;

    private Button addActivity;
    private Button cancelActivity;
    private DatabaseHandler databaseHandler;

    private EditText activityName;
    private Button activitySaveBtn;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        databaseHandler = new DatabaseHandler(this);
        List<Item> items = databaseHandler.getAllItems();

        addActivity = findViewById(R.id.addactivity_button);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemList = new ArrayList<>();

        itemList = databaseHandler.getAllItems();


        recyclerViewAdapter = new ItemRecyclerViewAdapter(this, itemList);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();


        addActivity.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addactivity_button:
                createPopDialog();
                break;
        }
    }

    private void createPopDialog() {
        Context context;
        builder =   new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.popup_activitiy_name, null);

        activityName = view.findViewById(R.id.activityname);
        activitySaveBtn = view.findViewById(R.id.activity_saveBtn);

        builder.setView(view);
        alertDialog = builder.create();
        alertDialog.show();

        activitySaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!activityName.getText().toString().isEmpty()){
                    saveItem(v);
                }
            }
        });
    }

    private void saveItem(View v) {
        Item item = new Item();

        String newActName = activityName.getText().toString().trim();


        item.setActivityName(newActName);
        item.setTime("11:45");
        item.setActivitySetName("Maths");
        item.setInterval(25);
        item.setChecked(0);


        databaseHandler.addItem(item);

        Snackbar.make(v, "Item Saved", Snackbar.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                alertDialog.dismiss();
                startActivity(new Intent(AddActivity.this, AddActivity.class));
                finish();
            }
        }, 600);
    }
}