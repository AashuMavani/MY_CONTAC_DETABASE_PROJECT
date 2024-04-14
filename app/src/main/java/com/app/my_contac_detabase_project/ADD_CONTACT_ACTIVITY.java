package com.app.my_contac_detabase_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class ADD_CONTACT_ACTIVITY extends AppCompatActivity {
    ImageView back,add_update,more2,image;
    EditText name,surname,number;
    My_Contact_DataBase dataBase=new My_Contact_DataBase(ADD_CONTACT_ACTIVITY.this);
    int id;
    String name1,surname1,number1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        back=findViewById(R.id.back);
        add_update=findViewById(R.id.add_update);
        more2=findViewById(R.id.more2);
        image=findViewById(R.id.image);
        name=findViewById(R.id.name);
        surname=findViewById(R.id.surname);
        number=findViewById(R.id.number);

       id=getIntent().getIntExtra("id",0);
       name1=getIntent().getStringExtra("name");
       surname1=getIntent().getStringExtra("surname");
       number1=getIntent().getStringExtra("number");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ADD_CONTACT_ACTIVITY.this,MainActivity.class);
                startActivity(intent);
            }
        });
        if (getIntent().getExtras()!=null){
            name.setText(""+name1);
            surname.setText(""+surname1);
            number.setText(""+number1);
        }

        add_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           if (getIntent().getExtras()==null){
               String n1=name.getText().toString();
               String n2=surname.getText().toString();
               String n3=number.getText().toString();
               dataBase.AddContact(""+n1,""+n2,""+n3);
            }
           else {
            String n1=name.getText().toString();
            String n2=surname.getText().toString();
            String n3=number.getText().toString();
            dataBase.UpDateContant(id,""+n1,""+n2,""+n3);

           }
           Intent intent=new Intent(ADD_CONTACT_ACTIVITY.this,MainActivity.class);
           startActivity(intent);
            }
        });



    }
}