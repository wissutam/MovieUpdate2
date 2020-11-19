package com.jovanovic.stefan.sqlitetutorial;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ReadOnly extends AppCompatActivity {

    TextView name_input, type_input, runtime_input, plot_input;
    String id, name, type, runtime, plot;
    ImageView MV_TYPE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_only);

        name_input = findViewById(R.id.movie_name_text_view);
        type_input = findViewById(R.id.movie_type_text_view);
        runtime_input = findViewById(R.id.movie_runtime_text_view);
        plot_input = findViewById(R.id.movie_plot_text_view);
        MV_TYPE = findViewById(R.id.movie_image_view);
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
                getIntent().hasExtra("type") && getIntent().hasExtra("runtime")&& getIntent().hasExtra("plot")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            type = getIntent().getStringExtra("type");
            runtime = getIntent().getStringExtra("runtime");
            plot = getIntent().getStringExtra("plot");

            //Setting Intent Data
            name_input.setText(name);
            type_input.setText(type);
            runtime_input.setText(runtime);
            if(type.equals("adventure")){
                MV_TYPE.setImageResource(R.drawable.adventure);
            }
            if(type.equals("comedy")){
                MV_TYPE.setImageResource(R.drawable.comedy);
            }
            if(type.equals("drama")){
                MV_TYPE.setImageResource(R.drawable.drama);
            }
            if(type.equals("erotica")){
                MV_TYPE.setImageResource(R.drawable.erotica);
            }
            if(type.equals("fantasy")){
                MV_TYPE.setImageResource(R.drawable.fantasy);
            }
            if(type.equals("horror")){
                MV_TYPE.setImageResource(R.drawable.horror);
            }
            if(type.equals("sci_fi")){
                MV_TYPE.setImageResource(R.drawable.sci_fi);
            }
            if(type.equals("western")){
                MV_TYPE.setImageResource(R.drawable.western);
            }
            plot_input.setText(plot);

            Log.d("stev", name +" "+ type +" "+ runtime +" "+ plot);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

}