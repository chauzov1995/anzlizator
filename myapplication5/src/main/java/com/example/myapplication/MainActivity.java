package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         button= (Button)findViewById(R.id.button);
         textView= (TextView)findViewById(R.id.textView);


        View.OnClickListener oclBtnOk = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Меняем текст в TextView (tvOut)
                textView.setText("asdasda");
            }
        };

        // присвоим обработчик кнопке OK (btnOk)
        button.setOnClickListener(oclBtnOk);

    }
}
