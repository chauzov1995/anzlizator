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

public class histor extends AppCompatActivity {

    ListView lv1;
    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    DatabaseHelper databaseHelper;


    ArrayList<Histor_elem> histors = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.histor);











        Bundle extras = getIntent().getExtras();
        String   poluch = extras.getString("id");



        databaseHelper = new DatabaseHelper(getApplicationContext());
        // создаем базу данных



        db = databaseHelper.open();
        //получаем данные из бд в виде курсора
        userCursor =  db.rawQuery("select b.nazvanie as nazv1, c.nazvanie as nazv2, summa, komment\n" +
                "from chto_kuda a\n" +
                "LEFT OUTER JOIN  koch_spis b ON b._id=a.kuda\n" +
                "LEFT OUTER JOIN  koch_spis c ON c._id=a.otkuda  \n" +
                "where otkuda=" +poluch+ " or kuda="+poluch, null);
        // определяем, какие столбцы из курсора будут выводиться в ListView


        userCursor.moveToFirst();
        while (userCursor.isAfterLast() == false) {
            String otkuda=userCursor.getString(userCursor.getColumnIndex("nazv1"));
            String kuda=userCursor.getString(userCursor.getColumnIndex("nazv2"));
            String summa=userCursor.getString(userCursor.getColumnIndex("summa"));
            String komment=userCursor.getString(userCursor.getColumnIndex("komment"));

            histors.add(new Histor_elem(otkuda, kuda, summa, komment));
            userCursor.moveToNext();
        }



        lv1 = (ListView) findViewById(R.id.lv1);
        historAdapter adapter1 = new historAdapter(this, histors);
        lv1.setAdapter(adapter1);




        Toast.makeText(this, extras.toString(), Toast.LENGTH_SHORT).show();
    }


    public class Histor_elem {
        public String otkuda;
        public String kuda;
        public String summa;
        public String komment;




        Histor_elem(String otkuda, String kuda, String summa, String komment){
            this.otkuda = otkuda;
            this.kuda = kuda;
            this.summa = summa;
            this.komment = komment;
        }

        public String getotkuda(){
            return this.otkuda;
        }
        public String getkuda(){
            return this.kuda;
        }
        public String getsumma(){
            return this.summa;
        }
        public String getkomment(){
            return this.komment;
        }
    }


}




