package com.example.rootsofthequadraticequation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
//import android.widget.RadioButton;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    RadioGroup RadioGroup1;

    Button langButton;

    RadioButton polski;
    RadioButton english;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup1=(RadioGroup)findViewById(R.id.RadioGroup1);
        polski =  (RadioButton)findViewById(R.id.rdbpolski);
        english  = (RadioButton)findViewById(R.id.rdbenglish);
        langButton  = (Button)findViewById(R.id.btnlang);

        /*langButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String strTemp;
                if (polski.isChecked()){
                    strTemp = "Wybrano polski";
                }
                else{
                    strTemp = "Wybrano english";
                }
                Log.i("funkcja onClick:",strTemp);
            }
        });

         */


    }

    public void buttonData(View view){
        //wciśnięcie klawiasza wybierz/choose
        //int temp;
        //temp = RadioGroup1.getCheckedRadioButtonId();
        //Log.i("funkcja buttonData:","temp = "+temp);
        Intent intent = null;
        if (polski.isChecked()){
            Log.i("Wciśnieto klawisz", "Wybrano pl");
            intent = new Intent(this, DisplayPolishActivity.class);
        }
        else if (english.isChecked()){
            Log.i("Wciśnięto klawisz", "Wybramo eng");
            intent = new Intent(this, DisplayEnglishActivity.class);
        }
        startActivity(intent);
    }
}