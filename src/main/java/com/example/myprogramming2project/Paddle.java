package com.example.myprogramming2project;

import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Paddle extends Rectangle {

    private static Image image = new Image("file:src/UFO.png");
    private static ImagePattern image_pattern = new ImagePattern(image);

    private static int WIDTH = 150;
    private static int HEIGHT = 30;
    private static int GAP = 30;

    private static Paddle singleton;

    private Paddle(){

    }
    // Singleton
    public static Paddle createPaddle(Scene scene){
        if (singleton == null)
            singleton = new Paddle(scene);
        return singleton;
    }
    private Paddle(Scene scene) {

        super(scene.getWidth()/2-WIDTH/2, scene.getHeight()-HEIGHT-GAP, WIDTH, HEIGHT);
        setFill(Color.GREEN);
        setArcHeight(20);
        setArcWidth(20);
        scene.setCursor(Cursor.NONE);
        scene.setOnMouseMoved(e -> {
            if (e.getX() >= scene.getWidth()-Globals.getOffsetX()+30-getWidth()){
                setX(scene.getWidth()-Globals.getOffsetX()+30-getWidth());
            } else if (e.getX() <= Globals.getOffsetX()){
                setX(Globals.getOffsetX());
            }
            else {setX(e.getX());}
        });
        setFill(image_pattern);

    }


    public void movePaddle(){


    }

}
