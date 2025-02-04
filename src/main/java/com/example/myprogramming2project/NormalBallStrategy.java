package com.example.myprogramming2project;

import javafx.scene.Scene;

public class NormalBallStrategy extends Ball implements BallStrategy{

    public NormalBallStrategy(int x, int y, Scene scene){
        super(x, y, scene);
    }

    @Override
    public int getSquareSpeed() {
        return 200;
    }
}
