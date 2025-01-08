package com.example.myprogramming2project;


import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameOverLabel extends Label {

    private static GameOverLabel singleton;

    public GameOverLabel() {
        super("[Game Over]");
        setTextFill(Color.WHITE);
        setFont(Font.font("Impact", 100));
    }

}
