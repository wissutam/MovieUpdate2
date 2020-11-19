package com.jovanovic.stefan.sqlitetutorial;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.style.UpdateLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class SelectMovie extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView empty_imageview,MV;
    TextView no_data;
    EditText Text_SE ;
    Button Go;
    int N;
    MyDatabaseHelper myDB;
    ArrayList<String> movie_id, movie_name, movie_type, movie_runtime, movie_plot;
    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_movie);

        MV= findViewById(R.id.imageView_T);
        recyclerView = findViewById(R.id.recyclerView2);
        add_button = findViewById(R.id.add_button);
        empty_imageview = findViewById(R.id.empty_imageview2);
        no_data = findViewById(R.id.no_data2);
        Text_SE = findViewById(R.id.editText_select);
        Go =findViewById(R.id.button_go);



        myDB = new MyDatabaseHelper(SelectMovie.this);
        movie_id = new ArrayList<>();
        movie_name = new ArrayList<>();
        movie_type = new ArrayList<>();
        movie_runtime = new ArrayList<>();
        movie_plot = new ArrayList<>();
        storeDataInArrays();

        Go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storeDataInArrays();
                finish();
            }
        });



        customAdapter = new CustomAdapter(SelectMovie.this,this, movie_id, movie_name, movie_type,
                movie_runtime, movie_plot,N);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(SelectMovie.this));


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2){
            recreate();
        }
    }
    void storeDataInArrays(){
        Cursor cursor = myDB.readData();
        if(cursor.getCount() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
           // empty_imageview.setImageResource(R.drawable.movie);
        }else{
            while (cursor.moveToNext()){

                    movie_id.add(cursor.getString(0));
                    movie_name.add(cursor.getString(1));
                    movie_type.add(cursor.getString(2));
                    movie_runtime.add(cursor.getString(3));
                    movie_plot.add(cursor.getString(4));

            }


            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }

    }






}
