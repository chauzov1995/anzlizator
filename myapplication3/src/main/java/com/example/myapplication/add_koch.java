package com.example.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by chauzov_n_g on 07.07.2017.
 */

public class add_koch extends AppCompatActivity {

    EditText editText3;
    EditText editText2;
    Button button;

    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;

    String otkuda ;
    String kuda ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_koch);




        editText3 = (EditText) findViewById(R.id.editText3);
        editText2 = (EditText) findViewById(R.id.editText2);
        button = (Button) findViewById(R.id.button);






        Bundle extras = getIntent().getExtras();

        String[]   poluch = extras.getString("id").split(";",0);

         otkuda = poluch[0];
         kuda = poluch[1];


         Toast.makeText(this, kuda, Toast.LENGTH_SHORT).show();
    }


    public void save(View view){

        sqlHelper = new DatabaseHelper(this);
        db = sqlHelper.open();

        ContentValues cv = new ContentValues();

        cv.put("id_clienta", "1");
        cv.put("otkuda", otkuda);
        cv.put("kuda", kuda);
        cv.put("summa", editText3.getText().toString());
        cv.put("komment", editText2.getText().toString());
        cv.put("data_fakt", "0");
        cv.put("data", "0");
        cv.put("visible", "0");






            db.insert("chto_kuda", null, cv);

      //  goHome();


    }

}

