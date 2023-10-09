package com.example.demo.UI.Functions.Calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.mariuszgromada.math.mxparser.Expression;

public class CalculatorFunction extends AppCompatActivity implements View.OnClickListener {
    private Button add, sub, mult, div, dot, equal,clear;
    private Button num_0, num_1, num_2, num_3, num_4, num_5, num_6, num_7, num_8, num_9;
    private TextView tv_solution,tv_result;
    private FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_function);

        setFindById();//綁定物件id
        setListener();//設定監聽器
    }

    private void setListener() {
        add.setOnClickListener(this);   sub.setOnClickListener(this);
        mult.setOnClickListener(this);  div.setOnClickListener(this);
        dot.setOnClickListener(this);   equal.setOnClickListener(this);
        num_0.setOnClickListener(this);   num_1.setOnClickListener(this);
        num_2.setOnClickListener(this);   num_3.setOnClickListener(this);
        num_4.setOnClickListener(this);   num_5.setOnClickListener(this);
        num_6.setOnClickListener(this);   num_7.setOnClickListener(this);
        num_8.setOnClickListener(this);   num_9.setOnClickListener(this);
        clear.setOnClickListener(this);
        //將點擊事件設定到OnClick

        floatingActionButton.setOnClickListener(view -> finish());
        //浮動式按鈕不用集中到OnClick，所以這裡就照樣執行finish的指令
    }//設定監聽器

    private void setFindById() {
        add = findViewById(R.id.btn_add);   sub = findViewById(R.id.btn_sub);
        mult = findViewById(R.id.btn_mult); div = findViewById(R.id.btn_div);
        dot = findViewById(R.id.btn_dot);   equal = findViewById(R.id.btn_equal);
        num_0 = findViewById(R.id.num_0);   num_1 = findViewById(R.id.num_1);
        num_2 = findViewById(R.id.num_2);   num_3 = findViewById(R.id.num_3);
        num_4 = findViewById(R.id.num_4);   num_5 = findViewById(R.id.num_5);
        num_6 = findViewById(R.id.num_6);   num_7 = findViewById(R.id.num_7);
        num_8 = findViewById(R.id.num_8);   num_9 = findViewById(R.id.num_9);
        clear = findViewById(R.id.btn_clear);

        floatingActionButton = findViewById(R.id.calculator_floatActionButton);

        tv_result = findViewById(R.id.calculator_result);
        tv_solution = findViewById(R.id.calculator_solution);
    }//綁定物件id

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        String btnText = button.getText().toString();
        String dataToCalculate = tv_result.getText().toString();

        if (btnText.equals("=")){
            tv_result.setText(getResult(tv_solution.getText().toString()));
            tv_solution.append(dataToCalculate + btnText);
            return;
        } else if (btnText.equals("CLEAR")) {
            tv_solution.setText("");
            tv_result.setText("");
            return;
        } else if (btnText.equals("+") || btnText.equals("-") || btnText.equals("*") || btnText.equals("/")){
            tv_solution.setText(tv_result.getText() + btnText);
            tv_result.setText("");
        }else{
            dataToCalculate = dataToCalculate + btnText;
            tv_result.setText(dataToCalculate);}
    }//當有點擊事件發生時就集中到這裡處理

        private String getResult(String dataToCalculate) {
            try {
                Expression expression = new Expression(dataToCalculate+tv_result.getText().toString());
                return String.valueOf(expression.calculate());
            }catch (Exception e){
                return "Error";
            }
        }
}