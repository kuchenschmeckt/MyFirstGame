package com.example.myfirstgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.Random;

public class Enemy {



    private Bitmap bitmap;


    private int x;
    private int y;

    //enemy speed
    private int speed = 1;

    //keep the enemy inside the screen
    private int maxX;
    private int minX;


    private int maxY;
    private int minY;
    private Rect detectCollision;


    public Enemy(Context context, int screenX, int screenY) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy2);
        maxX = screenX;
        maxY = screenY+3000;
        minX = 0;
        minY = 0;

        //generating a random coordinate to add enemy
        Random generator = new Random();
        speed = generator.nextInt(7) + 5;

        x = generator.nextInt(maxX)+40;
        x = generator.nextInt(maxX);
        y = 2400;

        detectCollision = new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());

    }

    public void update(int playerSpeed) {
        //decreasing x coordinate so that enemy will move right to left

        y -= speed;

        if (y < minY - bitmap.getWidth()) {

            Random generator = new Random();
            speed = generator.nextInt(10) + 12;
            y = 2000;
            y = 2400 - bitmap.getHeight();
        }
        detectCollision.left = x;
        detectCollision.top = y;
        detectCollision.right = x + bitmap.getWidth();
        detectCollision.bottom = y + bitmap.getHeight();
    }
    public void setX(int x){
        this.x = x;
    }

    public Rect getDetectCollision() {
        return detectCollision;
    }

    //stop enemy by collision
    public void stopenemy() {
        speed = 0;


    }

    //getters
    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

}