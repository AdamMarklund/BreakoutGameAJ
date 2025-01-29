package com.example.myprogramming2project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;

public class ScoreBoard extends Label {

    private int score;

    public ScoreBoard(){
        super("SCORE: 0");
        setTextFill(Color.YELLOW);
        setFont(Font.font("Impact", 30));
        setLayoutX(10);
        setLayoutY(10);
    }

    public void addPoints(int points){
        score += points;
        setText("Score: " + Integer.toString(score));
    }

    public void resetScore(){
        score = 0;
        setText("Score: " + Integer.toString(score));

    }


    public int getScore() {
        return score;
    }

}
