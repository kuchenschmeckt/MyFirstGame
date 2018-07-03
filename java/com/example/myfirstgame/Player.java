package com.example.myfirstgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.view.Gravity;


public class Player {
    private Bitmap bitmap;
    private int x;
    private int y;
    private int speed = 0;


    private boolean boosting;

    //Gravity Value to add gravity effect on the player
    private int GRAVITY = -18;


    private int maxY;
    private int minY;
    private int maxX;
    private int minX;


    private final int MIN_SPEED = 1;
    private final int MAX_SPEED = 30;
    private Rect detectCollision;

    public Player(Context context, int screenX, int screenY) {
        x = 20;
        y = 500;
        speed = 1;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.player2);

        //calculating maxY
        maxY = screenY - bitmap.getHeight();
        maxX = screenX - bitmap.getHeight();
        minX = screenX - bitmap.getHeight();
        minY = screenY - bitmap.getHeight();


        minY = 30;
        minX = 30;

        //setting the boosting value to false initially
        boosting = false;
        detectCollision = new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());
    }

    //setting boosting true
    public void setBoosting() {
        boosting = true;
    }

    //setting boosting false
    public void stopBoosting() {
        boosting = false;
    }
    //HIER WEITER MACHEN public void alles stoppen bei collison star.java, enemy.java, player.java und dann in gameview bei if Rect(.intersects bla bla.
    public void stopspeed() {
        speed = 0;
        GRAVITY = -80;
    }

    public void update() {
        //if the ship is boosting
        if (boosting) {
            //speeding up the ship
            speed += 18;
        } else {
            //slowing down if not boosting
            speed -= 5;
        }
        //controlling the top speed
        if (speed > MAX_SPEED) {
            speed = MAX_SPEED;
        }
        //if the speed is less than min speed
        //controlling it so that it won't stop completely
        if (speed < MIN_SPEED) {
            speed = MIN_SPEED;
        }

        //moving the ship down
        x += speed + GRAVITY;

        //but controlling it also so that it won't go off the screen
        if (x < minY) {
            x = minY;
        }
        if (x > maxY) {
            x = maxY;
        }
        if (x < minX) {
            x = minX;
        }
        if (x > maxX) {
            x = maxX;
        }

        detectCollision.left = x;
        detectCollision.top = y;
        detectCollision.right = x + bitmap.getWidth();
        detectCollision.bottom = y + bitmap.getHeight();
    }

    public Rect getDetectCollision() {
        return detectCollision;
    }

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