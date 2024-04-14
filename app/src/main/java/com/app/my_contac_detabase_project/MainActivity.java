package com.app.my_contac_detabase_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.app.my_contac_detabase_project.Model.Contact_Model;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton Add;
    ArrayList<Contact_Model>contactModels=new ArrayList<>();
    Recycerview_Adapter  adapter;
    SwipeRefreshLayout swipeRefreshLayout;
   My_Contact_DataBase dataBase=new My_Contact_DataBase(MainActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerview);
        swipeRefreshLayout=findViewById(R.id.swipe);
        Add=findViewById(R.id.Add);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ShowData();
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, ADD_CONTACT_ACTIVITY.class);
                startActivity(intent);
            }
        });

     adapter=new Recycerview_Adapter(MainActivity.this,contactModels);
        recyclerView.setAdapter(adapter);
    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
         swipeRefreshLayout.setRefreshing(true);
            recyclerView.setAdapter(adapter);
            swipeRefreshLayout.setRefreshing(false);
        }
    });


    }

    private void ShowData() {
        Cursor cursor = dataBase.Showdata();
        while (cursor.moveToNext())
        {
           int id=cursor.getInt(0);
           String name=cursor.getString(1);
           String surname=cursor.getString(2);
           String number=cursor.getString(3);
           Contact_Model contactModel=new Contact_Model(id,name,surname,number);
           contactModels.add(contactModel);
            Log.d("AAA", "ShowData: Create Data"+contactModel);
        }
    }

}