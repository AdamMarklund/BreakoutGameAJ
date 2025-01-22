package com.example.myprogramming2project;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ScoreBoard extends Label {

    private int score;

    public ScoreBoard(){
        super("Score: 0");
        setTextFill(Color.YELLOW);
        setFont(Font.font("Impact", 20));
        setLayoutX(5);
    }

    public void addPoints(int points){
        score += points;
        setText("Score: " + Integer.toString(score));
    }

    public int getScore() {
        return score;
    }

}
