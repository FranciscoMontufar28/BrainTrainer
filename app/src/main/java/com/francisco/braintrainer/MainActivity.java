package com.francisco.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton, btn1,btn2,btn3,btn4, playagain;
    TextView questionTextView, resultTextView, scoreTextView, timerTextView;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int LocationOfCorrectAnswer;
    int Score=0;
    int numberOfQuestions=0;

    public void playAgain(View view){
        Score = 0;
        numberOfQuestions = 0;
        playagain.setVisibility(View.INVISIBLE);

        createQuestion();

        timerTextView.setText("30 s");
        scoreTextView.setText("0/0");
        resultTextView.setText("");

        new CountDownTimer(3000,1000){

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000)+" s");
            }

            @Override
            public void onFinish() {
                timerTextView.setText("0 s");
                resultTextView.setText("Your score: " + Integer.toString(Score)+"/"+Integer.toString(numberOfQuestions));
            }
        };

    }

    public void chooseAnswer(View view){
        if(view.getTag().toString().equals(Integer.toString(LocationOfCorrectAnswer))){
            Score ++;
            resultTextView.setText("Correct!");
            createQuestion();
        }else {
            resultTextView.setText("Wrong!");
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(Score)+"/"+Integer.toString(numberOfQuestions));
        playagain.setVisibility(View.INVISIBLE);
     }

    public void Start(View view){

        startButton.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.btn_start);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        questionTextView= findViewById(R.id.questionTextView);
        timerTextView = findViewById(R.id.timerTextView);
        playagain = findViewById(R.id.BtnPlayAgain);

        resultTextView.setText("");
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);


        playAgain(findViewById(R.id.BtnPlayAgain));



    }

    public void createQuestion(){
        Random rand = new Random();
        LocationOfCorrectAnswer = rand.nextInt(4);
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        int incorrectAnswer;

        answers.clear();
        questionTextView.setText(""+a+"+"+b);

        for (int i=0; i<4; i++){
            if (i==LocationOfCorrectAnswer){
                answers.add(a+b);
            }else {
                incorrectAnswer = rand.nextInt(41);

                while (incorrectAnswer==a+b){
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }
        btn1.setText(""+answers.get(0));
        btn2.setText(""+answers.get(1));
        btn3.setText(""+answers.get(2));
        btn4.setText(""+answers.get(3));
    }
}
