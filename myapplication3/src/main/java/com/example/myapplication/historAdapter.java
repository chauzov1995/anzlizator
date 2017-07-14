package com.example.myapplication;

import java.util.ArrayList;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.text.TextUtils.concat;

public class historAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<histor.Histor_elem> objects;
    MainActivity MAIN;


    boolean start_task=false;


    historAdapter(Context context, ArrayList<histor.Histor_elem> products) {
        ctx = context;
        objects = products;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.MAIN=MAIN;
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return objects.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // пункт списка

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.histor_list_item, parent, false);
        }


        histor.Histor_elem p = getProduct(position);

        // заполняем View в пункте списка данными из товаров: наименование, цена
        // и картинка
        String text="из "+ p.getotkuda()+ " в "+p.getkuda()+" #"+p.getkomment()+" "+p.getsumma()+"р.";

        ((TextView) view.findViewById(R.id.textView4)).setText(text);


/*
        ImageButton addButton= (ImageButton) view.findViewById(R.id.addButton);




        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(MAIN, histor.class);
             //   intent.putExtra("id", objects.get(position).id.toString());
                MAIN.startActivity(intent);
            }
        });
*/

        return view;
    }





    // товар по позиции
    histor.Histor_elem getProduct(int position) {
        return ((histor.Histor_elem) getItem(position));
    }




}