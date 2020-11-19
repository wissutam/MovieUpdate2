package com.jovanovic.stefan.sqlitetutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    EditText name_input, type_input, runtime_input, plot_input;
    Button add_button;
    RadioGroup GM;
   // RadioButton R_adventure,R_comedy,R_drama,R_erotica,R_fantasy,R_horror,R_sci_fi,R_western;
    String TypeString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name_input = findViewById(R.id.name_input);
        type_input = findViewById(R.id.type_input);
        runtime_input = findViewById(R.id.runtime_input);
        plot_input = findViewById(R.id.plot_input);
        add_button = findViewById(R.id.add_button);
        GM = findViewById(R.id.RG_Movie);
        /*R_adventure = findViewById(R.id.radioButton_adventure);
        R_comedy = findViewById(R.id.radioButton_comedy);
        R_drama = findViewById(R.id.radioButton_drama);
        R_erotica = findViewById(R.id.radioButton_erotica);
        R_fantasy = findViewById(R.id.radioButton_fantasy);
        R_horror = findViewById(R.id.radioButton_horror);
        R_sci_fi = findViewById(R.id.radioButton_sci_fi);
        R_western = findViewById(R.id.radioButton_western);*/
        GM.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton R_Type =AddActivity.this.findViewById(i);
                TypeString = R_Type.getText().toString();
                Toast T1 = Toast.makeText(AddActivity.this, "TYPE MOVIE IS = " + TypeString, Toast.LENGTH_SHORT);
                        T1.show();

            }
        });

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addBook(name_input.getText().toString().trim(),
                        TypeString.trim(),
                        Integer.valueOf(runtime_input.getText().toString().trim()), plot_input.getText().toString().trim());
                int  P=99;

                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                intent.putExtra("score",P);
                startActivity(intent);






            }
        });
    }
}
