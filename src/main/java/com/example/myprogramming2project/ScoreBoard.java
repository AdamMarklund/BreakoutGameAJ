package com.example.myprogramming2project;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;

public class ScoreBoard extends Label {

    private int score;

    public ScoreBoard(){
        super("Score: 0");
        setTextFill(Color.YELLOW);
        setFont(Font.font("Impact", 30));
        setLayoutX(Globals.getOffsetX() + 10);
        setLayoutY(10);
    }

    public void addPoints(int points){
        score += points;
        setText("Score: " + Integer.toString(score));
        animateScoreLabel();
    }

    public void resetScore(){
        score = 0;
        setText("Score: " + Integer.toString(score));

    }


    public int getScore() {
        return score;
    }


    public void animateScoreLabel() {
        ScaleTransition scaleTransitionUp = new ScaleTransition(Duration.millis(200),this);
        scaleTransitionUp.setByX(0.25f);
        scaleTransitionUp.setByY(0.25f);
        scaleTransitionUp.setCycleCount(1);
        scaleTransitionUp.setAutoReverse(true);


        ScaleTransition scaleTransitionDown = new ScaleTransition(Duration.millis(200),this);
        scaleTransitionDown.setByX(-0.25f);
        scaleTransitionDown.setByY(-0.25f);
        scaleTransitionDown.setCycleCount(1);
        scaleTransitionDown.setAutoReverse(true);



        scaleTransitionUp.setOnFinished(event -> scaleTransitionDown.play());
        setScaleX(1);
        setScaleY(1);
        scaleTransitionUp.play();

    }
}
