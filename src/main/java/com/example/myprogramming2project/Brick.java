package com.example.myprogramming2project;


import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public abstract class Brick extends Group {

    private static int WIDTH = 50;
    private static int HEIGHT = 20;


    private Polygon leftTriangle, rightTriangle, topTriangle, bottomTriangle;

    public Brick(double leftX, double topY){
        brickFromTriangles(leftX, topY);


    }

    private void brickFromTriangles(double leftX, double topY){
        double centerX = leftX + WIDTH/2;
        double centerY = topY + HEIGHT/2;
        double  rightX = leftX + WIDTH;
        double bottomY = topY + HEIGHT;


        leftTriangle = new Polygon();
        leftTriangle.getPoints().addAll(new Double[]{
                leftX,topY,
                leftX,bottomY,
                centerX, centerY});
        leftTriangle.setFill(Color.GREEN);

        getChildren().add(leftTriangle);

        rightTriangle = new Polygon();
        rightTriangle.getPoints().addAll(new Double[]{
                rightX,topY,
                rightX,bottomY,
                centerX, centerY});
        rightTriangle.setFill(Color.RED);

        getChildren().add(rightTriangle);

        topTriangle = new Polygon();
        topTriangle.getPoints().addAll(new Double[]{
                rightX,topY,
                leftX,topY,
                centerX, centerY});
        topTriangle.setFill(Color.BLUE);

        getChildren().add(topTriangle);

        bottomTriangle = new Polygon();
        bottomTriangle.getPoints().addAll(new Double[]{
                rightX,bottomY,
                leftX,bottomY,
                centerX, centerY});
        bottomTriangle.setFill(Color.YELLOW);

        getChildren().add(bottomTriangle);

    }

    public void checkIntersects(Ball ball, Group bricks, ScoreBoard scoreLabel) {

        boolean hit = false;

        if (!((Path) Shape.intersect(ball, bottomTriangle)).getElements().isEmpty()) {
            ball.bounceDown();
            hit = true;
        } else if (!((Path) Shape.intersect(ball, topTriangle)).getElements().isEmpty()) {
            ball.bounceUp();
            hit = true;
        }
        if (!((Path) Shape.intersect(ball, leftTriangle)).getElements().isEmpty()) {
            ball.bounceLeft();
            hit = true;
        } else if (!((Path) Shape.intersect(ball, rightTriangle)).getElements().isEmpty()) {
            ball.bounceRight();
            hit = true;
        }


        if (hit) {
            hitBrick(bricks, scoreLabel);
        }
    }
    public abstract void hitBrick(Group bricks, ScoreBoard scoreLabel);

}
