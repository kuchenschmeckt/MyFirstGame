package com.example.myfirstgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


public class GameView extends SurfaceView implements Runnable {

    volatile boolean playing;
    private Thread gameThread = null;
    private Player player;




    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;

    private Enemy[] enemies;

    private int enemyCount = 7;
    private int score;

    int highScore[] = new int[4];


    SharedPreferences sharedPreferences;






    static MediaPlayer gamesound;
    final MediaPlayer getpoints;
    final MediaPlayer gameoversound;

    public static void stopMusic(){
        gamesound.stop();
    }





    Context context;





    int screenX;                //GameOver
    boolean flag;
    private boolean isGameOver;

    private ArrayList<Star> stars = new
            ArrayList<Star>();

    private explosion Explosion;
    private Points points;
    private reset Reset;

    // hier +1 wenn grünen ball einsammeln wie explosion blos mit +1 bild

    private Friend friend;

    public GameView(Context context, int screenX, int screenY) {
        super(context);
        player = new Player(context, screenX, screenY);

        surfaceHolder = getHolder();
        paint = new Paint();

        int starNums = 100;
        for (int i = 0; i < starNums; i++) {
            Star s = new Star(screenX, screenY);
            stars.add(s);
        }

        enemies = new Enemy[enemyCount];
        for (int i = 0; i < enemyCount; i++) {
            enemies[i] = new Enemy(context, screenX, screenY);
        }

        friend = new Friend(context, screenX, screenY);

        gamesound = MediaPlayer.create( context,R.raw.superboy );
        getpoints = MediaPlayer.create( context,R.raw.point);
        gameoversound = MediaPlayer.create( context,R.raw.gameover);
        gamesound.start();

        score = 0;


        Reset = new reset(context);



        Explosion = new explosion(context);
        points = new Points(context);

        this.context = context;

        sharedPreferences = context.getSharedPreferences("SHAR_PREF_NAME",Context.MODE_PRIVATE);



        highScore[0] = sharedPreferences.getInt("score1",0);

        highScore[2] = sharedPreferences.getInt("score3",0);
        highScore[3] = sharedPreferences.getInt("score4",0);







    }

    @Override
    public void run() {
        while (playing) {
            update();
            draw();
            control();
        }
    }

    private void update() {





        player.update();



        points.setX( -250 );
        points.setY( -250 );

        //setting boom outside the screen
        Explosion.setX(-250);
        Explosion.setY(-250);

        Reset.setY( 400 );
        Reset.setX( 1000 );





        for (Star s : stars) {
            s.update(player.getSpeed());
        }

        for (int i = 0; i < enemyCount; i++) {
            enemies[i].update(player.getSpeed());
            if (enemies[i].getX()==screenX){
                flag = true;
            }


            //if collision occurrs with player
            if (Rect.intersects(player.getDetectCollision(), enemies[i].getDetectCollision())) {

                //displaying boom at that location
                Explosion.setX(enemies[i].getX());
                Explosion.setY(enemies[i].getY());

                enemies[i].setX(-200);
                playing = false;
                isGameOver = true;
                gamesound.stop();
                gameoversound.start();


                for(int d=0;d<4;d++){

                if(highScore[d]<score){

                    final int finald = d;
                    highScore[d] = score;
                    break;
                }
            }




            SharedPreferences.Editor e = sharedPreferences.edit();

            for(int d=0;d<4;d++){

                int j = d+1;
                e.putInt("score"+j,highScore[d]);
            }
            e.apply();


            if(score>2){

                enemies[i].update(player.getSpeed()+4);
                sterne();



            }

            if(score>5){
                enemies[i].update(enemies[i].getSpeed()+6);
                sterne();
                enemyCount = 8;


            }


            }

            if (Rect.intersects(player.getDetectCollision(), friend.getDetectCollision())) {
                friend.setX( -150 );

                score = score +1;


                points.setY( player.getY() );
                points.setX( player.getX() );
                getpoints.start();

                for(int d=0;d<4;d++){


                    if(highScore[d]<score){

                        final int finald = d;
                        highScore[d] = score;
                        break;
                    }
                }




                SharedPreferences.Editor e = sharedPreferences.edit();

                for(int d=0;d<4;d++){

                    int j = d+1;
                    e.putInt("score"+j,highScore[d]);
                }
                e.apply();





            }

            friend.update(enemies[i].getSpeed());



        }
    }

    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {
            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor( Color.BLACK );

            paint.setColor( Color.WHITE );

            for (Star s : stars) {
                paint.setStrokeWidth( s.getStarWidth() );
                canvas.drawPoint( s.getX(), s.getY(), paint );
            }

            canvas.drawBitmap(
                    player.getBitmap(),
                    player.getX(),
                    player.getY(),
                    paint );

            for (int i = 0; i < enemyCount; i++) {
                canvas.drawBitmap(
                        enemies[i].getBitmap(),
                        enemies[i].getX(),
                        enemies[i].getY(),
                        paint
                );
            }

            //drawing boom image
            canvas.drawBitmap(
                    Explosion.getbitmap(),
                    Explosion.getX(),
                    Explosion.getY(),
                    paint
            );


            canvas.drawBitmap(
                    points.getbitmap(),
                    points.getX(),
                    points.getY(),
                    paint
            );

            canvas.drawBitmap(
                    friend.getBitmap(),
                    friend.getX(),
                    friend.getY(),
                    paint
            );

            canvas.save();


            canvas.rotate( 90f, 1000, 70 );
            paint.setTextSize( 90 );
            canvas.drawText( "Score:"+score, 1000, 70, paint );
            canvas.restore();






            if(isGameOver){

                canvas.rotate(90f, 550, 300);
                paint.setTextSize(270);
                canvas.drawText( "Game Over",550,300,paint );

                canvas.drawBitmap(
                        Reset.getbitmap(),
                        Reset.getX(),
                        Reset.getY(),
                        paint
                );





            }

            surfaceHolder.unlockCanvasAndPost(canvas);

        }
    }

    private void control() {
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
        }
    }

    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void sterne() {

        for (Star s : stars) {
            s.update( player.getSpeed()+5 );
        }

    }


    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                player.stopBoosting();
                break;
            case MotionEvent.ACTION_DOWN:
                player.setBoosting();
                break;
        }

        if(isGameOver){




            if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                context.startActivity( new Intent( context,MainActivity.class ) );
            }








            }
        return true;
    }
}


