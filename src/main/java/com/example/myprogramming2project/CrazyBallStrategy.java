package com.example.myprogramming2project;

import javafx.scene.Scene;

public class CrazyBallStrategy extends Ball implements BallStrategy {

    public CrazyBallStrategy(int x, int y, Scene scene){
        super(x, y, scene);
    }
    @Override
    public int getSquareSpeed() {
        return 400;
    }
}