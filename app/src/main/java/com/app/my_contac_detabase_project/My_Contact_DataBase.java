package com.app.my_contac_detabase_project;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class My_Contact_DataBase extends SQLiteOpenHelper {
    public My_Contact_DataBase(Context context) {
        super(context, "My_Contact", null, 1);
        Log.d("ABC", "My_Contact_DataBase:Create Database ");
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="create table ContactData(ID integer primary key autoincrement,Name text,SURNAME text,NUMBER text)";
        db.execSQL(query);
        Log.d("AAA", "onCreate: Create table");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void AddContact(String name, String surname, String number) {
        String query="insert into ContactData(NAME,SURNAME,NUMBER)values('"+name+"','"+surname+"','"+number+"')";
       SQLiteDatabase db=getWritableDatabase();
       db.execSQL(query);

    }

    public Cursor Showdata() {
        String query="select *from ContactData";
        SQLiteDatabase db=getReadableDatabase();
       Cursor cursor=db.rawQuery(query,null);
     return cursor;
    }

    public void UpDateContant(int id, String name, String surname, String number) {
        String query="upDate ContactData set NAME='"+name+"',SURNAME='"+surname+"',NUMBER='"+number+"'";
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(query);



    }

    public void DeleteData(int id) {
        String query="delete from ContactData where ID="+id+" ";
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(query);
    }
}
