package com.example.myprogramming2project;

import javafx.scene.Scene;

public class FastBallStrategy extends Ball implements BallStrategy {

    public FastBallStrategy(int x, int y, Scene scene){
        super(x, y, scene);
    }
    @Override
    public int getSquareSpeed() {
        return 300;
    }
}
