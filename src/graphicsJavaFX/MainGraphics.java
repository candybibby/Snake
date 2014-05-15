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
    public Synchronizer synchronizer;   // instance of Synchronizer, used to sync everything
    public Game game;                   // instance of game, initiated here because this is

    SimpleBooleanProperty initializeNewGame = new SimpleBooleanProperty(false); // this is used to pass information between the controller and the mainGraphics class


    @Override
    public void start(Stage primaryStage) throws Exception {
        setup();
        primaryStage.setTitle("MainPane");      // sets the title of the window
        primaryStage.setScene(new Scene(controller));   // sets the controller instance as the scene in the window
        primaryStage.show();        // displays the window
        mainProgram();
        graphicsTimer();

    }


    public void drawScreen() {

        controller.drawNewScreen(synchronizer.getGameWorld());
        //controller.drawNewScreen(synchronizer.getGameWorld(),
                //synchronizer.getGameAreaHeight(), synchronizer.getGameAreaWidth()); // draws the gameworld array into the textfield in the window

    }

    public void drawGameOver() {
        //controller.printGameOver(); // if
    }

    public void graphicsTimer() { // setup a timer for the graphics, runs every 1000/framerate milliseconds
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000 / frameRate), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if (!synchronizer.isGameOver()) { // if game not over
                    drawScreen();                   // draw the game screen
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

        synchronizer = new Synchronizer();
        controller.giveHeightAndWidth(synchronizer.getGameAreaHeight(), synchronizer.getGameAreaWidth());
        controller.buildGrid();
        game = new Game(synchronizer);
        game.runNewGame(1);             // run the game with the given difficulty

    }


    public static void main(String[] args) {
        launch(args);
    }

    public void setup() {       // setup everything for the graphics part


        controller = new Controller();      // make a new instance of controller
        initializeNewGame.bind(controller.initializeNewGame);       // bind the two simplebooleanproperties so we know when the new game button was pushed
        initializeNewGame.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean aBoolean2) {
                game.world.gameEngine.engineTimer.cancel();     // these two lines stop the gameEngine
                game.world.gameEngine.engineTimer.purge();      // otherwise we would have too many threads running in the background
                      resetAll();                              // then make new game
            }
        });

    }

    public void resetAll(){
        synchronizer.resetGame();
        game.runNewGame(0);
    }

}
