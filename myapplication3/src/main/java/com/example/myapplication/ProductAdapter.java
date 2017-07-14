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

public class ProductAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Product> objects;
    MainActivity MAIN;
    String otvet="";
    String sMove;
    TextView tv;
    float x;
    float y;

    float x_start;
    float y_start;

    boolean start_task=false;


    ProductAdapter(Context context, ArrayList<Product> products, MainActivity MAIN) {
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
            view = lInflater.inflate(R.layout.list_item, parent, false);
        }
        String otvet="";

        Product p = getProduct(position);

        // заполняем View в пункте списка данными из товаров: наименование, цена
        // и картинка
        ((TextView) view.findViewById(R.id.nazv)).setText(p.getNazv());
        ((TextView) view.findViewById(R.id.start)).setText(p.getStart());
        ((TextView) view.findViewById(R.id.fakt)).setText(p.getFakt());

        tv=(TextView)MAIN.findViewById(R.id.textView3);

        ImageButton addButton= (ImageButton) view.findViewById(R.id.addButton);


        addButton.setOnDragListener(new View.OnDragListener() {

            @Override
            public boolean onDrag(View v, DragEvent event) {



                // TODO Auto-generated method stub
                int action = event.getAction();
                switch(action) {

                    case DragEvent.ACTION_DRAG_STARTED:

                       //Toast.makeText(MAIN, objects.get(position).id.toString(), Toast.LENGTH_SHORT).show();
                        break;

                    case DragEvent.ACTION_DRAG_EXITED:
                        break;

                    case DragEvent.ACTION_DRAG_ENTERED:
                        break;

                    case DragEvent.ACTION_DROP:{
                        //код для переноса денег

String otvet=event.getClipData().getItemAt(0).getText().toString()
        .concat(";")
        .concat(objects.get(position).id);

                        Intent intent = new Intent(MAIN, add_koch.class);
                        intent.putExtra("id",  otvet);
                        MAIN.startActivity(intent);


                        // Toast.makeText(MAIN, "drag", Toast.LENGTH_SHORT).show();
                        return(true);
                    }

                    case DragEvent.ACTION_DRAG_ENDED:{
                        // tvOut.setText("ended");
                        return(true);

                    }

                    default:
                        break;
                }
                return true;
            }});





        addButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent arg1) {

                try{


                x = arg1.getRawX();
                y = arg1.getRawY();
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                switch (arg1.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажатие
                        start_task=true;
                        x_start=x;
                        y_start=y;
                        break;
                    case MotionEvent.ACTION_MOVE: // движение
                        if(start_task) {
                            if (x_start + 1 < x || x_start - 1 > x || y_start + 1 < y || y_start - 1 > y) {
                                start_task = false;
                            }
                            sMove = "Move: " + x + "," + y;
                        }
                        if (!start_task) {
                            ClipData clipData= ClipData.newPlainText("id",objects.get(position).id.toString());
                            v.startDrag(clipData, shadowBuilder, v, 0);
                            sMove = "Move: " + x + "," + y+" движка";
                            tv.setText(sMove);
                        }
                        break;
                    case MotionEvent.ACTION_UP: // отпускание
                        start_task = false;
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        start_task = false;
                        tv.setText("toup");
                        break;

                }

                }catch(Exception ex){

                    tv.setText("toup");
                }

                return false;
               // v.setVisibility(View.INVISIBLE);//чтобы удалит изнач
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(MAIN, histor.class);
                intent.putExtra("id", objects.get(position).id.toString());
                MAIN.startActivity(intent);
            }
        });


        return view;
    }





    // товар по позиции
    Product getProduct(int position) {
        return ((Product) getItem(position));
    }




}