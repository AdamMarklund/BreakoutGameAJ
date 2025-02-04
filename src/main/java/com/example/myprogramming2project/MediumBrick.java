package com.example.myprogramming2project;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class MediumBrick extends Brick {

    private static Image image = new Image("file:src/Diamond.png");
    private static ImagePattern image_pattern = new ImagePattern(image);

    private double leftX;
    private double topY;

    public MediumBrick(double leftX, double topY) {
        super(leftX, topY);
        this.leftX = leftX;
        this.topY = topY;

        Rectangle rect = new Rectangle(leftX, topY, 50, 20);
        rect.setFill(image_pattern);
        getChildren().add(rect);
    }

    @Override
    public void hitBrick(Group bricks, ScoreBoard scoreLabel) {
        bricks.getChildren().set(bricks.getChildren().indexOf(this), new SoftBrick(leftX, topY));
        scoreLabel.addPoints(2);
    }
}



