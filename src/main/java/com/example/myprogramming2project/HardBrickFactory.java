package com.example.myprogramming2project;

public class HardBrickFactory implements BrickFactory{

    @Override
    public Brick createBrick(int posX, int posY) {
        return new HardBrick(posX, posY);
    }
}
