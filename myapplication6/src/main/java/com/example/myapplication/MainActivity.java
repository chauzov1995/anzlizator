package com.example.myapplication;

import android.app.Activity;
import android.content.ClipData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    TextView tvOut;
    Button btnOk;
    Button btnOk1;
    float x;
    float y;
    float x_start;
    float y_start;
    String sMove;
    boolean start_task=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOut = (TextView) findViewById(R.id.tvOut);
        btnOk = (Button) findViewById(R.id.btnOk);
        btnOk1 = (Button) findViewById(R.id.btnOk1);





        btnOk1.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent arg1) {

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
                            if (x_start + 100 < x || x_start - 100 > x || y_start + 100 < y || y_start - 100 > y) {

                                start_task = false;
                            }
                            sMove = "Move: " + x + "," + y;
                        }
                           if (!start_task) {
                               v.startDrag(null, shadowBuilder, v, 0);

                               sMove = "Move: " + x + "," + y+" движка";
                           }

                        break;
                    case MotionEvent.ACTION_UP: // отпускание

                        start_task = false;
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        start_task = false;
                        break;
                }
                tvOut.setText(sMove);





                return false;

                // v.setVisibility(View.INVISIBLE);//чтобы удалит изнач

            }
        });


    }
}
