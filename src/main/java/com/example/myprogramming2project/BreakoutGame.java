package com.example.myprogramming2project;

import javafx.animation.AnimationTimer;
import javafx.application.Application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;




public class BreakoutGame extends Application {

    // Nodes
    static Ball ball;
    static Scene scene;
    static Group root;
    static GameOverLabel gameOverLabel;
    static ScoreBoard scoreLabel;
    
    static Group bricks;

    // Factory pattern
    static SoftBrickFactory softBrickFactory = new SoftBrickFactory();
    static MediumBrickFactory mediumBrickFactory = new MediumBrickFactory();
    static HardBrickFactory hardBrickFactory = new HardBrickFactory();
    static SuperHardBrickFactory superHardBrickFactory = new SuperHardBrickFactory();

    // current round, changes when no bricks are left
    static int round = 1;

    // Width of the pillars on both side of the game.
    static int borderWidth = 30;

    // Runs once when the program starts
    @Override
    public void start(Stage stage){

        // Root node and scene
        root = new Group();
        scene = new Scene(root, 1280, 600, Color.BLACK);
        stage.setScene(scene);

        //Background
        ImagePattern imagePatternBackground = new ImagePattern(new Image("file:src/Background.png"));
        Rectangle backgroundRect = new Rectangle(0, 0, 1280, 600);
        backgroundRect.setFill(imagePatternBackground);
        root.getChildren().add(backgroundRect);

        // Borders/rectangles placed at Globals.getOffsetX
        Rectangle leftBorder = new Rectangle(Globals.getOffsetX()-borderWidth, 0, borderWidth, 600);
        leftBorder.setFill(Color.GRAY);

        Rectangle rightBorder = new Rectangle(scene.getWidth()-Globals.getOffsetX()+borderWidth, 0, borderWidth, 600);
        rightBorder.setFill(Color.GRAY);

        root.getChildren().add(leftBorder);
        root.getChildren().add(rightBorder);



        // BreakoutgameAJ logos
        ImagePattern image_pattern = new ImagePattern(new Image("file:src/Breakoutgamelogo.png"));
        Rectangle logoRect = new Rectangle(15, 200, Globals.getOffsetX()-borderWidth*2, Globals.getOffsetX()-borderWidth*2);
        logoRect.setFill(image_pattern);
        root.getChildren().add(logoRect);

        Rectangle rightRect = new Rectangle(scene.getWidth()-Globals.getOffsetX()+ borderWidth*2, 200, Globals.getOffsetX()-borderWidth*2, Globals.getOffsetX()-borderWidth*2);
        rightRect.setFill(image_pattern);
        root.getChildren().add(rightRect);

        // Paddle
        Paddle paddle = Paddle.createPaddle(scene);
        root.getChildren().add(paddle);

        // GameOverLabel
        gameOverLabel = new GameOverLabel();
        root.getChildren().add(gameOverLabel);
        root.getChildren().add(gameOverLabel.getStartLabel());
        gameOverLabel.setVisible(false);
        gameOverLabel.getStartLabel().setVisible(false);
        gameOverLabel.animateStartLabel();

        // Ball
        ball = new Ball((int)paddle.getX()+(int)paddle.getWidth()/2, (int)paddle.getY(), scene);
        root.getChildren().add(ball);
        bricks = new Group();

        // ScoreLabel
        scoreLabel = new ScoreBoard();
        root.getChildren().add(scoreLabel);

        // Draws the initial bricks
        newGame();

        // Adds the brick node
        root.getChildren().add(bricks);

        // Displays the window to the user
        stage.show();


        AnimationTimer gameLoop = new AnimationTimer() {
            // Executes every frame
            @Override
            public void handle(long l) {

                //Checks what strategy the ball should use
                checkStrategy();

                checkGameOver();

                // Checks if the round is over
                if (bricks.getChildren().isEmpty()) {
                    newRound();
                    round += 1;
                }

                // Checks if the ball is at the border of the game winddow, and if so changes its direction
                ball.checkBounds(scene);
                // Same as above but for the paddle
                ball.checkPaddle(paddle, scene);
                // Sets the ball position continously
                ball.move(paddle);

                // Loops through bricks and checks if any of them are colliding with the ball
                for (int i = 0; i<bricks.getChildren().size(); i++)
                    ((Brick)bricks.getChildren().get(i)).checkIntersects(ball, bricks, scoreLabel);

            }
        };
        // Starts the AnimationTimer loop
        gameLoop.start();
    }

    public static void main(String[] args) {
        // Runs start()
        launch();

    }

    private static void newGame(){
        bricks.getChildren().clear();

        for (int i=0; i<11; i++) {
            bricks.getChildren().add(softBrickFactory.createBrick(Globals.getOffsetX() + i*72, 200));
            bricks.getChildren().add(mediumBrickFactory.createBrick(Globals.getOffsetX() + i*72, 150));
            bricks.getChildren().add(hardBrickFactory.createBrick(Globals.getOffsetX() + i*72, 100));
        }
    }

    private static void newRound(){
        for (int i=0; i<11; i++) {
            bricks.getChildren().add(softBrickFactory.createBrick(Globals.getOffsetX() + i*72, 200));
            bricks.getChildren().add(mediumBrickFactory.createBrick(Globals.getOffsetX() + i*72, 150));
            bricks.getChildren().add(hardBrickFactory.createBrick(Globals.getOffsetX() + i*72, 100));
            bricks.getChildren().add(superHardBrickFactory.createBrick(Globals.getOffsetX() + i*72, 50));
        }
    }

    private static void checkGameOver(){
        // When the ball leaves the screen
        if (ball.getCenterY()+ball.getRadius() >= scene.getHeight()){

            // When the game has started again
            if (ball.isGameStarted()) {
                gameOverLabel.setVisible(true);
                gameOverLabel.getStartLabel().setVisible(true);
                round = 1;
            }
            // Sets up a listener that runs when clicked.
            scene.setOnMouseClicked(e -> {
                ball.resetBall();
                gameOverLabel.setVisible(false);
                newGame();
                scene.setOnMouseClicked(null);
                });
            }


        if (!ball.isGameStarted()) {
            // Runs the second time you press the mouse after game over
            scene.setOnMouseClicked(e -> {
                scoreLabel.resetScore();
                ball.getBallMoving();
                gameOverLabel.getStartLabel().setVisible(false);
                scene.setOnMouseClicked(null);
            });
        }
    }

    public static void checkStrategy(){
        if (round == 1)
            ball.setStrategy(new NormalBallStrategy((int)ball.getCenterX(), (int)ball.getCenterY(), scene));
        else if (round == 2)
            ball.setStrategy(new FastBallStrategy((int)ball.getCenterX(), (int)ball.getCenterY(), scene));
        else
            ball.setStrategy(new CrazyBallStrategy((int)ball.getCenterX(), (int)ball.getCenterY(), scene));
    }
}