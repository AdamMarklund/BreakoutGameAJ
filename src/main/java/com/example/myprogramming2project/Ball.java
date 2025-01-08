package com.example.myprogramming2project;

import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

import static java.lang.Math.abs;

public class Ball extends Circle {
    private static int BALLSIZE = 7;
    private int velocityX = 0;
    private int velocityY = 0;
    private boolean gameStarted = false;



    public Ball(int x, int y, Scene scene){
        super(x,y-BALLSIZE-1, BALLSIZE);
        setFill(Color.WHITE);

        scene.setOnMouseClicked(e -> {
            velocityX = 10;
            velocityY = -10;
            gameStarted = true;
            scene.setOnMouseClicked(null);
        });



    }

    public void checkBounds(Scene scene){
        if (getCenterX() + getRadius() >= scene.getWidth()){
            velocityX = -abs(velocityX);
        } else if (getCenterX() - getRadius() <= 0){
            velocityX = abs(velocityX);
        }

         if (getCenterY() - getRadius() <= 0) {
            velocityY = abs(velocityY);
        }
    }


    public int newVelocityY(int newVelocityX){
        return velocityY = (int)Math.sqrt(200-Math.pow((double)velocityX,2));
    }
    public void checkPaddle(Paddle paddle, Scene scene){
        if (getCenterY()+getRadius() >= paddle.getY() && getCenterY()+getRadius() < scene.getHeight()){
            if (getCenterX()+getRadius() >= paddle.getX() && getCenterX()-getRadius() <= paddle.getX()+paddle.getWidth()) {
                // "3 zoner"
                if (getCenterX() + getRadius() < paddle.getX() + paddle.getWidth() / 3) {
                    velocityX = -12;
                    velocityY = -newVelocityY(velocityX);

                } else if (getCenterX() - getRadius() > paddle.getX() + paddle.getWidth() * 2 / 3) {
                    velocityX = 12;
                    velocityY = -newVelocityY(velocityX);

                } else {velocityY = -velocityY;}
            }
        }
    }

    public void move(Paddle paddle){
        if (gameStarted) {
            setCenterX(getCenterX() + velocityX);
            setCenterY(getCenterY() + velocityY);
        } else {
            setCenterX(paddle.getX() + paddle.getWidth() / 2);
            setCenterY(paddle.getY());
        }
    }

    public void bounceDown(){
        velocityY = abs(velocityY);
    }

    public void bounceUp(){
        velocityY = -abs(velocityY);
    }

    public void bounceLeft(){
        velocityX = -abs(velocityX);
    }

    public void bounceRight(){
        velocityX = abs(velocityX);
    }

    public void resetBall() {
        velocityX = 0;
        velocityY = 0;
        gameStarted = false;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void getBallMoving() {
        velocityX = 10;
        velocityY = -10;
        gameStarted = true;
    }
}

