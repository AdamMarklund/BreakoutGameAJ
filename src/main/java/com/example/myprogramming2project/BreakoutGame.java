package com.example.myprogramming2project;
// strategy, facade, decorator, singleton,adapter, factory
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class BreakoutGame extends Application {

    @Override
    public void start(Stage stage){

        // Root node and scene
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600, Color.BLACK);
        stage.setScene(scene);


        //Background
        /*
        ImagePattern image_pattern = new ImagePattern(new Image("file:src/Background.png"));
        Rectangle backgroundRect = new Rectangle(0, 0, 800, 600);
        backgroundRect.setFill(image_pattern);
        root.getChildren().add(backgroundRect);

         */



        // Paddle
        Paddle paddle = Paddle.createPaddle(scene);
        root.getChildren().add(paddle);

        // GameOverLabel
        Group gameOverLabelGr = new Group();
        GameOverLabel gameOverLabel = new GameOverLabel();


        // Ball
        Ball ball = new Ball((int)paddle.getX()+(int)paddle.getWidth()/2, (int)paddle.getY(), scene);
        root.getChildren().add(ball);

        Group bricks = new Group();


        ScoreBoard scoreLabel = new ScoreBoard();
        root.getChildren().add(scoreLabel);

        /*
        Image image = new Image("file:src/Gold.png");
        ImagePattern image_pattern = new ImagePattern(image);
        // create a Rectangle
        Rectangle rect = new Rectangle(100, 100, 200, 150);
        root.getChildren().add(rect);
        // set fill for rectangle

         */




        // Image image = new Image();

        for (int i=0; i<11; i++){
           bricks.getChildren().add(new SoftBrick(i*70,200));
           bricks.getChildren().add(new MediumBrick(i*70, 150));
           bricks.getChildren().add(new HardBrick(i*70, 100));


        }
        root.getChildren().add(bricks);





        stage.show();

        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {


                if (bricks.getChildren().isEmpty()) {
                    newGame(bricks);
                }



                checkGameOver(ball, scene, bricks, root, gameOverLabelGr, gameOverLabel);
                ball.checkBounds(scene);
                ball.checkPaddle(paddle, scene);
                ball.move(paddle);
                for (int i = 0; i<bricks.getChildren().size(); i++)
                    ((Brick)bricks.getChildren().get(i)).checkIntersects(ball, bricks, scoreLabel);
            }
        };
        gameLoop.start();
    }

    public static void main(String[] args) {
        launch();

    }

    private static void newGame(Group bricks){
        for (int i=0; i<11; i++) {
            bricks.getChildren().add(new SoftBrick(i * 70, 200));
            bricks.getChildren().add(new MediumBrick(i * 70, 150));
            bricks.getChildren().add(new HardBrick(i * 70, 100));
            bricks.getChildren().add(new SuperHardBrick(i * 70, 50));
        }
    }

    private static void checkGameOver(Ball ball, Scene scene, Group bricks, Group root, Group gameOverLabelGr, GameOverLabel gameOverLabel){


        if (ball.getCenterY()+ball.getRadius() >= scene.getHeight()){

            if (ball.isGameStarted()) {
                bricks.getChildren().clear();
                if (gameOverLabelGr.getChildren().isEmpty()) {
                    gameOverLabelGr.getChildren().add(gameOverLabel);
                    root.getChildren().add(gameOverLabelGr);

                }
                scene.setOnMouseClicked(e -> {
                    ball.resetBall();
                    newGame(bricks);
                    gameOverLabelGr.getChildren().removeFirst();
                    scene.setOnMouseClicked(null);

                });
            }
        if (!ball.isGameStarted())
            scene.setOnMouseClicked(e -> {
                ball.getBallMoving();
                scene.setOnMouseClicked(null);
            });

        }
    }
}