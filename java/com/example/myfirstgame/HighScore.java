package com.example.myfirstgame;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.content.Intent;



import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class HighScore extends AppCompatActivity {

    TextView textView,textView3,textView4, textView0;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        textView0 = (TextView) findViewById( R.id.textView0 );
        textView0.setText("Highscores");






        textView = (TextView) findViewById(R.id.textView);




        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);

        sharedPreferences  = getSharedPreferences("SHAR_PREF_NAME", Context.MODE_PRIVATE);

        //setting the values to the textViews
        textView.setText("1. "+sharedPreferences.getInt("score1",0)+""+"p");



        textView3.setText("2. "+sharedPreferences.getInt("score3",0)+""+"p");
        textView4.setText("3. "+sharedPreferences.getInt("score4",0)+""+"p");



    }
}