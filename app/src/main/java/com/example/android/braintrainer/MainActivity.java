package com.example.android.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView result;
    TextView sum;
    TextView time;
    TextView points;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgain;
    RelativeLayout game;
    ArrayList<Integer> ans = new ArrayList<Integer>();
    int locationOfAns;
    int score = 0;
    double highScore = 0;
    int numberOfQuestions = 0;


    public void playAgain(View view){
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        score = 0;
        numberOfQuestions = 0;
        time.setText("30s");
        points.setText("0/0");
        result.setText("");
        playAgain.setVisibility(View.INVISIBLE);
        generateQuestions();

        new CountDownTimer(30000+100,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                time.setText(String.valueOf(millisUntilFinished/1000)+"s");

            }

            @Override
            public void onFinish() {
                time.setText("0s");
                double p = Math.round((score*1.0/numberOfQuestions)*100);
                if(p>highScore){
                    highScore=p;
                }
                result.setText("Your score: "+p+"\nHigh score: "+highScore);
                playAgain.setVisibility(View.VISIBLE);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);

            }
        }.start();
    }

    public void generateQuestions(){
        Random rand = new Random();
        String s;

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        s=Integer.toString(a)+"+"+Integer.toString(b);
        sum.setText(s);
        locationOfAns = rand.nextInt(4);
        int incorrectAns;
        ans.clear();
        for(int i=0;i<4;i++){
            if(i==locationOfAns){
                ans.add(a+b);
            }
            else{
                incorrectAns = rand.nextInt(41);
                while(incorrectAns==a+b) {
                    incorrectAns = rand.nextInt(41);
                }
                ans.add(incorrectAns);
            }
        }
        button0.setText(Integer.toString(ans.get(0)));
        button1.setText(Integer.toString(ans.get(1)));
        button2.setText(Integer.toString(ans.get(2)));
        button3.setText(Integer.toString(ans.get(3)));

    }

    public void start(View view){
        startButton.setVisibility(View.INVISIBLE);
        game.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.startButton));


    }

    public void chooseAnswer(View view){

        if(view.getTag().toString().equals(Integer.toString(locationOfAns))){
            score++;
            result.setText("Correct!");
        }
        else
        {
            result.setText("Wrong!");
        }
        numberOfQuestions++;
        points.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        generateQuestions();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = (Button) findViewById(R.id.startButton);
        sum = (TextView) findViewById(R.id.sumText);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        result = (TextView) findViewById(R.id.resultText);
        points = (TextView) findViewById(R.id.score);
        playAgain = (Button)findViewById(R.id.playButton);
        time = (TextView)findViewById(R.id.time);
        game = (RelativeLayout)findViewById(R.id.layout);

    }


}
