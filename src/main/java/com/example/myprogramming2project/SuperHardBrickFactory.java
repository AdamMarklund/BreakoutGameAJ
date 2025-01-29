package com.example.myprogramming2project;

public class SuperHardBrickFactory implements BrickFactory {

    @Override
    public Brick createBrick(int posX, int posY) {
        return new SuperHardBrick(posX, posY);
    }
}
