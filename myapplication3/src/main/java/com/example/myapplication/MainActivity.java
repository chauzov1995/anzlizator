package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView userList;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;

    ArrayList<Product> products = new ArrayList();
    GridView productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        databaseHelper = new DatabaseHelper(getApplicationContext());
        // создаем базу данных

        databaseHelper.create_db();//копируем базу

        // открываем подключение
        db = databaseHelper.open();




        //получаем данные из бд в виде курсора
        userCursor =  db.rawQuery("select * from koch_spis", null);
        // определяем, какие столбцы из курсора будут выводиться в ListView


        userCursor.moveToFirst();
        while (userCursor.isAfterLast() == false) {
            String id=userCursor.getString(userCursor.getColumnIndex("_id"));
            String nazvanie=userCursor.getString(userCursor.getColumnIndex("nazvanie"));
            String dengi_start=userCursor.getString(userCursor.getColumnIndex("dengi_start"));
            String dengi_fakt=userCursor.getString(userCursor.getColumnIndex("dengi_fakt"));

            products.add(new Product(id, nazvanie, dengi_start, dengi_fakt, "1"));
            userCursor.moveToNext();
        }

        products.add(new Product("new", "Добавить", null, null,"1"));


        productList = (GridView) findViewById(R.id.productList);
        ProductAdapter adapter = new ProductAdapter(this, products,this);
        productList.setAdapter(adapter);
















    }
    @Override
    public void onResume() {
        super.onResume();


    }
    // по нажатию на кнопку запускаем UserActivity для добавления данных
    public void add(View view){
        Intent intent = new Intent(this, UserActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        // Закрываем подключение и курсор
        db.close();
        userCursor.close();
    }
}