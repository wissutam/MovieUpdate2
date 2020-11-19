package com.jovanovic.stefan.sqlitetutorial;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText name_input, type_input, runtime_input, plot_input;
    Button update_button, delete_button;
    RadioGroup GM2;
    String id, name, type, runtime, plot;
    String TypeString2;
    RadioButton R_adventure2,R_comedy2,R_drama2,R_erotica2,R_fantasy2,R_horror2,R_sci_fi2,R_western2;
    int P=99;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name_input = findViewById(R.id.movie_input2);
        type_input = findViewById(R.id.type_input2);
        runtime_input = findViewById(R.id.runtime_input2);
        plot_input = findViewById(R.id.plot_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);
        R_adventure2 = findViewById(R.id.radioButton_adventure2);
        R_comedy2 = findViewById(R.id.radioButton_comedy);
        R_drama2 = findViewById(R.id.radioButton_drama2);
        R_erotica2 = findViewById(R.id.radioButton_erotica2);
        R_fantasy2 = findViewById(R.id.radioButton_fantasy2);
        R_horror2 = findViewById(R.id.radioButton_horror2);
        R_sci_fi2 = findViewById(R.id.radioButton_sci_fi2);
        R_western2 = findViewById(R.id.radioButton_western2);

        GM2 = findViewById(R.id.RG_Movie2);
        GM2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton R_Type =UpdateActivity.this.findViewById(i);
                TypeString2 = R_Type.getText().toString();
               /* Toast T1 = Toast.makeText(UpdateActivity.this, "TYPE MOVIE IS  " + TypeString2, Toast.LENGTH_SHORT);
                T1.show();
*/
            }
        });
        //First we call this
        getAndSetIntentData();


        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                name = name_input.getText().toString().trim();
                type = TypeString2.trim();
                runtime = runtime_input.getText().toString().trim();
                plot = plot_input.getText().toString().trim();
                myDB.updateData(id, name, type, runtime, plot);

                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                intent.putExtra("score",P);
                startActivity(intent);

            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

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
            plot_input.setText(plot);

            if(type.equals("adventure")){
                R_adventure2.setChecked(true);
            }
            if(type.equals("comedy")){
                R_comedy2.setChecked(true);
            }
            if(type.equals("drama")){
                R_drama2.setChecked(true);
            }
            if(type.equals("erotica")){
                R_erotica2.setChecked(true);
            }
            if(type.equals("fantasy")){
                R_fantasy2.setChecked(true);
            }
            if(type.equals("horror")){
                R_horror2.setChecked(true);
            }
            if(type.equals("sci_fi")){
                R_sci_fi2.setChecked(true);
            }
            if(type.equals("western")){
                R_western2.setChecked(true);
            }



            Log.d("stev", name +" "+ type +" "+ runtime+" "+ plot);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                intent.putExtra("score",P);
                startActivity(intent);
                //finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
