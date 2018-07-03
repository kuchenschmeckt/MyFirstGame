package com.example.myfirstgame;

import android.graphics.BitmapFactory;

import java.util.Random;

public class Star {
    private int x;
    private int y;
    private int speed = 2;

    private int maxX;
    private int maxY;
    private int minX;
    private int minY;




    public Star(int screenX, int screenY) {
        maxX = screenX;
        maxY = screenY;
        minX = 0;
        minY = 0;

        Random generator = new Random();
        speed = generator.nextInt(10);

        //random coordinate
        //inside the screen size
        x = generator.nextInt(maxX);
        y = generator.nextInt(maxY);
    }

    public void fasterstar() {
        speed = +5;
    }

    public void update(int playerSpeed) {



        y -= speed;
        //if the star reached the left edge of the screen
        if (y < 0) {
            //again starting the star from right edge
            //infinite scrolling background effect
            y = maxY;
            Random generator = new Random();
            x = generator.nextInt(maxX);
            speed = generator.nextInt(15);
        }

    }

    public float getStarWidth() {
        //Making the star width random so that
        //it will give a real look
        float minX = 3.0f;
        float maxX = 6.0f;
        Random rand = new Random();
        float finalX = rand.nextFloat() * (maxX - minX) + minX;
        return finalX;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}