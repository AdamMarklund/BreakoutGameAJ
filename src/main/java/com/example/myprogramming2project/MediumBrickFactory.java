package com.example.myprogramming2project;

public class MediumBrickFactory implements BrickFactory{

    @Override
    public Brick createBrick(int posX, int posY) {
        return new MediumBrick(posX, posY);
    }
}
