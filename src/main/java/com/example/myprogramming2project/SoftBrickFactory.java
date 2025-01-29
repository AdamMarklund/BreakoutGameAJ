package com.example.myprogramming2project;

public class SoftBrickFactory implements BrickFactory {

    @Override
    public Brick createBrick(int posX, int posY) {
        return new SoftBrick(posX, posY);
    }
}
