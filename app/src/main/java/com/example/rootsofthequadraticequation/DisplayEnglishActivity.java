/**package com.example.rootsofthequadraticequation;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class DisplayEnglishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_english);
    }
}**/

package com.example.rootsofthequadraticequation;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;


public class DisplayEnglishActivity extends AppCompatActivity {

    EditText editTextNumberDecimal_A, editTextNumberDecimal_B, editTextNumberDecimal_C;
    TextView textView_function,textView_delta, textView_info, textView_x1, textView_x2;

    public String[] countDelta(Float a, Float b, Float c){
        String[] result = new String[3];
        double x1;
        double x2;
        String[] answer = new String[4];
        result[0]= "The roots do not exist.";
        result[1]="The roots are real and equal.";
        result[2]="The roots are real and distinct.";
        Float delta;
        delta = b*b - 4*a*c;
        answer[0]=Float.toString(delta);
        if (a == 0) {
            if (b == 0) {
                answer[0] = "not exist.";
                answer[1] = result[0];
                answer[2] = "";
                answer[3] = "";
                return answer;
            }
            else {

                //0=bx+c x=-c/b
                answer[0] = "not exist.";
                answer[1] = result[1];
                answer[2] = Float.toString(-c / b);
                answer[3] = "";
                return answer;
            }
        }
        else {
            if (delta < 0) {
                answer[1] = result[0];
                answer[2] = "";
                answer[3] = "";
                return answer;
            }
            else if (delta == 0) {
                x1 = (-b) / (2 * a);
                if (x1 == -0) {
                    x1 = Float.valueOf(0);
                }
                answer[1] = result[1];
                answer[2] = Double.toString(x1);
                answer[3] = "";
                return answer;

            } else {
                x1 = (-b + Math.sqrt(delta)) / (2 * a);
                x2 = (-b - Math.sqrt(delta)) / (2 * a);
                answer[1] = result[2];
                answer[2] = Double.toString(x1);
                answer[3] = Double.toString(x2);
                return answer;
            }
        }
    }

    public void setResults(String[] data){
        //temp_delta = "delta: %s",data[0];
        //temp_delta
        textView_delta.setText(new StringBuilder().append("delta: ").append(data[0]).toString());
        textView_info.setText(data[1]);
        if (data[2] != ""){
            textView_x1.setText(new StringBuilder().append("x1 = ").append(data[2]));
        }
        else{
            textView_x1.setText("");
        }
        if (data[3] != "") {
            textView_x2.setText(new StringBuilder().append("x2 = ").append(data[3]));
        }
        else{
            textView_x2.setText("");
        }
    }

    public String createF(){
        String f = "y2 = ";
        String factorA = editTextNumberDecimal_A.getText().toString();
        if (factorA.equals("")){
            factorA="0";
        }
        String factorB = editTextNumberDecimal_B.getText().toString();
        if (factorB.equals("")){
            factorB="0";
        }
        String factorC = editTextNumberDecimal_C.getText().toString();
        if (factorC.equals("")){
            factorC="0";
        }

        Log.i("createF()","factorA: "+factorA);
        Log.i("createF()","factorB: "+factorB);
        Log.i("createF()","factorC: "+factorC);
        Float fFactorA, fFactorB, fFactorC;
        try {
            fFactorA = Float.parseFloat((factorA));
            editTextNumberDecimal_A.setTextColor(Color.BLACK);
        }
        catch (NumberFormatException e){
            //wait
            fFactorA = Float.valueOf(0);
            //int i = ;
            editTextNumberDecimal_A.setTextColor(Color.RED);
            //editTextNumberDecimal_A.setText("0");

        }
        try {
            fFactorB = Float.parseFloat(factorB);
        }
        catch (NumberFormatException e){
            fFactorB = Float.valueOf(0);

        }
        try{
            fFactorC = Float.parseFloat((factorC));

        }
        catch (NumberFormatException e){
            fFactorC = Float.valueOf(0);
        }
        if (fFactorA != 0) {
            f += new StringBuilder().append(fFactorA).append("*x2");
        }
        else if(fFactorA == 0){
            //do nothing
        }
        if (fFactorB < 0){
            f += new StringBuilder().append(fFactorB).append("*x");
        }
        else if(fFactorB > 0){
            f += new StringBuilder().append("+").append(fFactorB).append("*x");
        }
        else{
            //fFactor = 0
            //do nothing

        }

        if (fFactorC > 0){
            f += new StringBuilder().append("+").append(fFactorC);
        }
        else if (fFactorC < 0){
            f += new StringBuilder().append(fFactorC);
        }
        else{
            //do nothing
        }
        String[] data = countDelta(fFactorA,fFactorB,fFactorC);
        setResults(data);
        return f;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_english);

        editTextNumberDecimal_A = (EditText)findViewById(R.id.editTextNumberDecimal_A);
        editTextNumberDecimal_B = (EditText)findViewById(R.id.editTextNumberDecimal_B);
        editTextNumberDecimal_C = (EditText)findViewById(R.id.editTextNumberDecimal_C);
        textView_function = (TextView) findViewById(R.id.textView_function);
        textView_delta = (TextView) findViewById(R.id.textView_delta);
        textView_info = (TextView) findViewById(R.id.textView_info);
        textView_x1 = (TextView) findViewById(R.id.textView_x1);
        textView_x2 = (TextView) findViewById(R.id.textView_x2);



        editTextNumberDecimal_A.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //editTextNumberDecimal_A.setTextColor(Color.BLACK);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //textView_function.setText(createF());
            }

            @Override
            public void afterTextChanged(Editable s) {

                textView_function.setText(createF());
            }
        });
        editTextNumberDecimal_B.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //textView_function.setText(createF());
            }

            @Override
            public void afterTextChanged(Editable s) {
                textView_function.setText(createF());
            }
        });
        editTextNumberDecimal_C.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //textView_function.setText(createF());
            }

            @Override
            public void afterTextChanged(Editable s) {
                textView_function.setText(createF());
            }
        });
    }
}