package com.example.myprogramming2project;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class SoftBrick extends Brick{

    private static Image image = new Image("file:src/Gold.png");
    private static ImagePattern image_pattern = new ImagePattern(image);


    public SoftBrick(double leftX, double topY) {
        super(leftX, topY);
        Rectangle rect = new Rectangle(leftX, topY, 50, 20);

        // set fill for rectangle
        rect.setFill(image_pattern);
        getChildren().add(rect);
    }

    @Override
    public void hitBrick(Group bricks, ScoreBoard scoreLabel) {
        bricks.getChildren().remove(this);
        scoreLabel.addPoints(1);
    }
}
