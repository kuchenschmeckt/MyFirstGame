package com.example.myfirstgame;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.Image;
import android.media.ImageReader;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private ImageButton buttonPlay;
    private ImageButton buttonScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonPlay = (ImageButton) findViewById(R.id.buttonPlay);
        buttonScore = (ImageButton) findViewById( R.id.buttonScore );

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);



        buttonPlay.setOnClickListener(this);
        buttonScore.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Exit?")

                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        GameView.stopMusic();
                        Intent startMain = new Intent(Intent.ACTION_MAIN);
                        startMain.addCategory(Intent.CATEGORY_HOME);
                        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(startMain);
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }



    @Override
    public void onClick(View v) {

        if (v == buttonPlay) {

            startActivity( new Intent( MainActivity.this, GameActivity.class ) );



        }

        if (v == buttonScore) {
            startActivity( new Intent( MainActivity.this, HighScore.class));


        }

        buttonPlay = (ImageButton) findViewById(R.id.buttonPlay);  // button sound
        final MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.sample);
        mp.start();



    }
}