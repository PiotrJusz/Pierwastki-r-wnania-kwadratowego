package com.example.rootsofthequadraticequation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
//import android.widget.RadioButton;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText editTextNumberDecimal_A, editTextNumberDecimal_B, editTextNumberDecimal_C;
    TextView textView_function,textView_delta, textView_info, textView_x1, textView_x2;
    int defaultTextColor;

    public String[] countDelta(Float a, Float b, Float c){
        //obliczenie delty i związanych z tym miejsc zerowych/pierwaistków równania kwadratowego
        //zwrócenie tablicy answer z wartościa delty, z informacją o ilości rozwiązań, wartościami x1 i x2
        String[] result = new String[3];
        double x1;
        double x2;
        String[] answer = new String[4];
        String langInfo = String.valueOf(Locale.getDefault().getLanguage());
        if ("pl".equals(langInfo)){
            result[0] = "Równanie nie posiada rozwiązań.";
            result[1] = "Równanie posiada jedno rozwiązanie.";
            result[2] = "Równanie posiada dwa rozwiązania";
        }
        else{ //langinfo == eng
            result[0]= "The roots do not exist.";
            result[1]="The roots are real and equal.";
            result[2]="The roots are real and distinct.";
        }

        Float delta;
        delta = b*b - 4*a*c;
        answer[0]=Float.toString(delta);
        if (a == 0) {
            if (b == 0) {
                //funkcja stała y=const
                if ("pl".equals(langInfo)){
                    answer[0] = "nie istnieje.";
                }
                else {
                    answer[0] = "not exist.";
                }
                answer[1] = result[0];
                answer[2] = "";
                answer[3] = "";
                //return answer;
            }
            else {//funkcja liniowa y=bx+c >> x=-c/b
                answer[0] = "not exist.";
                answer[1] = result[1];
                x1 = -c / b;
                if (x1 == -0.0){
                    x1 = Float.valueOf(0);
                }
                answer[2] = Double.toString(x1);
                answer[3] = "";
                //return answer;
            }
        }
        else {//a>0 and a<0 >> funkcja kwadratowa
            if (delta < 0) {//brak rozwiązań dla delta<0
                answer[1] = result[0];
                answer[2] = "";
                answer[3] = "";
                //return answer;
            }
            else if (delta == 0) {//jedno rozwiązanie dla funkcji kwadratowej, delta=0
                x1 = Math.round((-b) / (2 * a));
                if (x1 == -0) {
                    x1 = Float.valueOf(0);
                }
                answer[1] = result[1];
                answer[2] = Double.toString(x1);
                answer[3] = "";
                //return answer;

            } else {//delta>0, dwa rozwiązania funkcji kwadratowej
                x1 = Math.round(-b + Math.sqrt(delta)) / (2 * a);
                x2 = Math.round(-b - Math.sqrt(delta)) / (2 * a);
                answer[1] = result[2];
                answer[2] = Double.toString(x1);
                answer[3] = Double.toString(x2);
                //return answer;
            }
        }
        return answer;
    }

    public void setResults(String[] data){
        //data = countDelta = answer
        //formatowanie i drukowanie wyników w głównym oknie aplikacji

        //domyślne wartości:
        textView_delta.setText(new StringBuilder().append("delta: ").append(data[0]).toString());
        textView_info.setText(data[1]);
        textView_x1.setText("");
        textView_x2.setText("");

        //zmiana wartości domyślnych jeśli jeśli istnieje wartość dla x1 lub x2
        if (data[2] != ""){
            textView_x1.setText(new StringBuilder().append("x1 = ").append(data[2]));
        }
        if (data[3] != "") {
            //t.setText(Html.fromHtml("7<sup>2</sup>"));
            textView_x2.setText(new StringBuilder().append("x2 = ").append(data[3]));
        }
    }

    public String createF(){
        //utworzenie funkcji y dla podanych przez użytkownika parametrów a, b, c
        //wartośc domyślna
        String f = "y = ";
        //pobranie podanego przez użytkownika wartości dla a,b i c
        String factorA = editTextNumberDecimal_A.getText().toString();
        String factorB = editTextNumberDecimal_B.getText().toString();
        String factorC = editTextNumberDecimal_C.getText().toString();
        //przypisanie 0 jeśli wprowadzona wartośc nie istnieje (jest równa "")
        if (factorA.equals("") || factorB.equals("") || factorC.equals("")){
            if(factorA.equals("")){
                factorA= String.valueOf(0);
            }
            if(factorB.equals("")){
                factorB= String.valueOf(0);
            }
            if(factorC.equals("")){
                factorC= String.valueOf(0);
            }
        }

        Float fFactorA, fFactorB, fFactorC;
        //sprawdzanie poprawności wprowadzanycg danych przez użytkownika
        //zmiana domyślnego koloru tekstu na czerwony w momencie wykrycia błędu
        try {
            editTextNumberDecimal_A.setTextColor(defaultTextColor);
            fFactorA = Float.parseFloat((factorA));
        }
        catch (NumberFormatException e){
            fFactorA = Float.valueOf(0);
            editTextNumberDecimal_A.setTextColor(Color.RED);
        }
        try {
            editTextNumberDecimal_B.setTextColor(defaultTextColor);
            fFactorB = Float.parseFloat(factorB);
        }
        catch (NumberFormatException e){
            fFactorB = Float.valueOf(0);
            editTextNumberDecimal_B.setTextColor(Color.RED);
        }
        try{
            editTextNumberDecimal_C.setTextColor(defaultTextColor);
            fFactorC = Float.parseFloat((factorC));
        }
        catch (NumberFormatException e){
            fFactorC = Float.valueOf(0);
            editTextNumberDecimal_C.setTextColor(Color.RED);
        }
        //tworzenie wyrażenia tekstowego przedstawiające wyrażenie funkcji (zmienic ten komentarz!)
        if (fFactorA != 0) {
            //dopisanie x2
            f += new StringBuilder().append(fFactorA).append("*x\u00B2");
        }
        if (fFactorB < 0){
            f += new StringBuilder().append(fFactorB).append("*x");
        }
        else if(fFactorB > 0){
            f += new StringBuilder().append("+").append(fFactorB).append("*x");
        }
        if (fFactorC > 0){
            f += new StringBuilder().append("+").append(fFactorC);
        }
        else if (fFactorC < 0){
            f += new StringBuilder().append(fFactorC);
        }
        String[] data = countDelta(fFactorA,fFactorB,fFactorC);
        setResults(data);
        return f;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNumberDecimal_A = (EditText)findViewById(R.id.editTextNumberDecimal_A);
        defaultTextColor = editTextNumberDecimal_A.getCurrentTextColor();
        editTextNumberDecimal_B = (EditText)findViewById(R.id.editTextNumberDecimal_B);
        editTextNumberDecimal_C = (EditText)findViewById(R.id.editTextNumberDecimal_C);
        textView_function = (TextView) findViewById(R.id.textView_function);
        textView_delta = (TextView) findViewById(R.id.textView_delta);
        textView_info = (TextView) findViewById(R.id.textView_info);
        textView_x1 = (TextView) findViewById(R.id.textView_x1);
        textView_x2 = (TextView) findViewById(R.id.textView_x2);
        //dodanie interfejsu TextChangedListener dla pol a,b i c
        //
        editTextNumberDecimal_A.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                //dynamiczne wypisywanie zmiany wspólczynnika a w okno główne w miescju na wyrażenie funkcyje
                textView_function.setText(createF());
            }
        });
        editTextNumberDecimal_B.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                //dynamiczne wypisywanie zmiany wspólczynnika b w okno główne w miescju na wyrażenie funkcyje
                textView_function.setText(createF());
            }
        });
        editTextNumberDecimal_C.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                //dynamiczne wypisywanie zmiany wspólczynnika a w okno główne w miescju na wyrażenie funkcyje
                textView_function.setText(createF());
            }
        });
    }
}