package graphicsJavaFX;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import package1.*;

public class MainGraphics extends Application {

    public Controller controller;       // controller is used to modify output to the main window
    public int frameRate = 50;          // how often the graphics are updated

    public Game game;                   // instance of game, initiated here because this is

    SimpleBooleanProperty initializeNewGame = new SimpleBooleanProperty(false); // this is used to pass information between the controller and the mainGraphics class
    SimpleBooleanProperty startNewGame = new SimpleBooleanProperty(false);

    @Override
    public void start(Stage primaryStage) throws Exception {
        setup();
        primaryStage.setTitle("SNAKE");      // sets the title of the window
        primaryStage.setScene(new Scene(controller));   // sets the controller instance as the scene in the window
        primaryStage.show();        // displays the window


    }


    public void drawScreen() {

        controller.drawNewScreen(Synchronizer.getGameWorld());
        //controller.drawNewScreen(Synchronizer.getGameWorld(),
        //Synchronizer.getGameAreaHeight(), Synchronizer.getGameAreaWidth()); // draws the gameworld array into the textfield in the window

    }

    public void drawGameOver() {
        controller.displayGameOver();
    }

    public void graphicsTimer() { // setup a timer for the graphics, runs every 1000/framerate milliseconds
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000 / frameRate), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if (!Synchronizer.isGameOver()) { // if game not over
                    drawScreen();                   // draw the game screen
                    controller.updateScore(Synchronizer.getScores()[0]);
                    controller.setHighScore(Synchronizer.getHighScore());
                } else {                // else
                    drawGameOver();             // draw the game over text
                }

            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE); // makes the timer run forever
        timeline.play();                            // starts the timer
    }

    public void mainProgram() {
        // do stuff here

        newGame();

    }

    public void newGame() { // initializes everything necessary for the game


        controller.giveHeightAndWidth(Synchronizer.getGameAreaHeight(), Synchronizer.getGameAreaWidth());
        controller.buildGrid();
        game = new Game();

        game.runNewGame(Synchronizer.getDifficulty());             // run the game with the given difficulty
        controller.setHighScore(Synchronizer.highScore);
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void setup() {       // setup everything for the graphics part

        Synchronizer.setup();
        controller = new Controller();      // make a new instance of controller
        controller.menuPane.toFront();      //
        controller.menuPane.setOpacity(1);
        initializeNewGame.bind(controller.initializeNewGame);       // bind the two simplebooleanproperties so we know when the new game button was pushed
        initializeNewGame.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean aBoolean2) {
                game.world.gameEngine.engineTimer.cancel();     // these two lines stop the gameEngine
                game.world.gameEngine.engineTimer.purge();      // otherwise we would have too many threads running in the background
                resetAll();                              // then make new game
            }
        });

        startNewGame.bind(controller.startNewGame);
        startNewGame.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean aBoolean2) {
                controller.sendMenuBack();
                //controller.gameAreaContainer.toFront();
                controller.startGame();
                controller.setMenuOpacity(0);
                controller.setTheme(0);
                mainProgram();
                graphicsTimer();
            }
        });


    }

    public void resetAll() {
        Synchronizer.resetGame();
        controller.hideGameOver();
        game.runNewGame(controller.getGameDifficulty());
    }


}
