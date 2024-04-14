package com.example.nisharscalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultTv,inputTv;
    MaterialButton buttonC,buttonOpenbracket,buttonClosebracket;
    MaterialButton buttonDivide,buttonMultiply,buttonPlus,buttonMinus,buttonEqual,buttonDot;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
        inputTv = findViewById(R.id.input_tv);

        assignId(buttonC,R.id.button_c);
        assignId(buttonOpenbracket,R.id.button_openbracket);
        assignId(buttonClosebracket,R.id.button_closebracket);
        assignId(buttonDivide,R.id.button_divide);
        assignId(buttonMultiply,R.id.button_multiply);
        assignId(buttonPlus,R.id.button_plus);
        assignId(buttonMinus,R.id.button_minus);
        assignId(buttonEqual,R.id.button_equal);
        assignId(button7,R.id.button_7);
        assignId(button8,R.id.button_8);
        assignId(button9,R.id.button_9);
        assignId(button4,R.id.button_4);
        assignId(button5,R.id.button_5);
        assignId(button6,R.id.button_6);
        assignId(button1,R.id.button_1);
        assignId(button2,R.id.button_2);
        assignId(button3,R.id.button_3);
        assignId(button0,R.id.button_0);
        assignId(buttonDot,R.id.button_dot);


    }

    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
//        inputTv.setText(buttonText);
        String dataToCalculate = inputTv.getText().toString();

        if(buttonText.equals("=")){
            inputTv.setText(resultTv.getText());
            return;
        }

        if(buttonText.equals("C")){
            inputTv.setText("");
            resultTv.setText("0");
            return;
        }
        else {
            dataToCalculate = dataToCalculate+buttonText;
        }

        inputTv.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if (!finalResult.equals("Error")){
            resultTv.setText(finalResult);
        }

    }

    String getResult(String data){
        try {
            Context context= Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if (finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Error";
        }

    }

}
