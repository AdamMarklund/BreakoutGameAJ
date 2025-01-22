package com.example.myprogramming2project;


import javafx.animation.ScaleTransition;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.awt.*;

public class GameOverLabel extends Label {
    private Label startLabel;

    public GameOverLabel() {
        super("Game Over");

        setTextFill(Color.RED);
        setFont(Font.font("Impact", 175));
        setLayoutX(10);
        setLayoutY(200);
        startLabel = new Label("Tap to start");
        startLabel.setTextFill(Color.WHITE);
        startLabel.setLayoutX(325);
        startLabel.setLayoutY(450);
        startLabel.setFont(Font.font("impact",25 ));

    }

    public Label getStartLabel() {
        return startLabel;
    }
}
