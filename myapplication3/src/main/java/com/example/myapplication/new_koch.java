package com.example.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by kisa on 25.07.2017.
 */


    public class new_koch extends AppCompatActivity {

    EditText editText;
    EditText editText4;
    Button button2;

    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;

    String otkuda;
    String kuda;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_koch);


        editText = (EditText) findViewById(R.id.editText);
        editText4 = (EditText) findViewById(R.id.editText4);
        button2 = (Button) findViewById(R.id.button2);



    }

    public void save(View view){

        sqlHelper = new DatabaseHelper(this);
        db = sqlHelper.open();

        ContentValues cv = new ContentValues();

        cv.put("type", "1");
        cv.put("nazvanie", editText.getText().toString());
        cv.put("dengi_start", editText4.getText().toString());
        cv.put("data_sozd", "0");
        cv.put("dengi_fakt", "0");


        db.insert("koch_spis", null, cv);

        //  goHome();


    }
}
