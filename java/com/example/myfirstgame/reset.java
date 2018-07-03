package com.example.myfirstgame;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class reset  {

    private Bitmap bitmap;
    private int x;
    private int y;







    public reset(Context context) {

        bitmap = BitmapFactory.decodeResource
                ( context.getResources(), R.drawable.restart );



        x = 400;
        y = 1000;



    }





    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;

    }

    public Bitmap getbitmap() {
        return bitmap;

    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;

    }

    public int getX() {
        return x;

    }
    public int getY() {
        return y;

    }




}