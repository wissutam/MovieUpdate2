package com.jovanovic.stefan.sqlitetutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {

    Button B_Admin,B_User;
    int P = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        B_User=findViewById(R.id.button_User);
        B_Admin=findViewById(R.id.button_Admin);

        B_User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    P=2;

                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                intent.putExtra("score",P);
                startActivity(intent);
            }
        });
        B_Admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                P=99;

                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                intent.putExtra("score",P);
                startActivity(intent);
            }
        });



    }

}