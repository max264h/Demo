package com.example.demo.UI.Functions.Game;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameFunction extends AppCompatActivity {
    private EditText et_player_answer;
    private Button btn_enter;
    private TextView result;
    private FloatingActionButton floatingActionButton;
    private ArrayList<Integer> CorrectAnswer, PlayerAnswer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_function);

        setFindById();
        setAnswer();
        setListener();
    }

    private void setListener() {
        btn_enter.setOnClickListener(view -> CheckAnswer(et_player_answer.getText().toString()));
        floatingActionButton.setOnClickListener(view -> finish());
    }

    private void CheckAnswer(String player_answer) {
        PlayerAnswer = new ArrayList<>();
        et_player_answer.setText("");//每次輸入完答案都把EditText清空

        String regex = ".*[a-zA-Z].*";//使用正則表達式判斷輸入的答案有沒有英文字母
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(player_answer);
        if (player_answer.length() < 4 || matcher.matches()) {
            Toast.makeText(this,"請輸入四個數字",Toast.LENGTH_SHORT).show(); return;}

        for (int i = 0; i < 4; i++) {
            PlayerAnswer.add(Character.getNumericValue(player_answer.charAt(i)));
        }//將玩家輸入的答案丟給陣列存取

        int quantityA = 0, quantityB = 0;//A跟B的數量
        for (int i = 0; i < 4; i++) {
            if (CorrectAnswer.contains(PlayerAnswer.get(i))){//先判斷輸入的答案有沒有在正確答案內
                if (CorrectAnswer.get(i).equals(PlayerAnswer.get(i))) quantityA++;//如果有就判斷當下的數字是否跟正確答案一樣，是就A+1
                else quantityB++;//反之B+1
            }
        }//判斷幾A幾B
        if (quantityA == 4){
            Toast.makeText(this,"恭喜～你猜中了！",Toast.LENGTH_SHORT).show();
            result.setText("");
            setAnswer();
        }//當A的數量為四個的時候，就展開新的一輪
        result.append("這次輸入 : "+ PlayerAnswer + "，結果為 : " + quantityA + "A" + quantityB + "B\n");
        //沒有4A的時候就正常顯示幾A幾B
    }

    private void setAnswer() {
        CorrectAnswer= new ArrayList<>();
        ArrayList<Integer> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add(i);
        }//先建立一個0到9的陣列
        Collections.shuffle(data);//接著將陣列洗牌
        CorrectAnswer.addAll(data.subList(0,4));//將前四個數字加進正確解答
        Log.d("test", "setAnswer: "+CorrectAnswer);
    }//設定正確答案

    private void setFindById() {
        et_player_answer = findViewById(R.id.et_game);
        btn_enter = findViewById(R.id.btn_game);
        result = findViewById(R.id.game_result);
        floatingActionButton = findViewById(R.id.game_floatActionButton);
    }
}