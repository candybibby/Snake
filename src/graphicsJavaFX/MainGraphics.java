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

    public Controller controller;


    public int frameRate = 50;
    public Synchronizer synchronizer;
    Game game;

    SimpleBooleanProperty initializeNewGame = new SimpleBooleanProperty(false);


    @Override
    public void start(Stage primaryStage) throws Exception {
        setup();
        primaryStage.setTitle("MainPane");
        primaryStage.setScene(new Scene(controller));
        primaryStage.show();
        mainProgram();
        graphicsTimer();

    }


    public void drawScreen() {

        controller.drawNewScreen(synchronizer.getGameWorld(), synchronizer.getGameAreaHeight(), synchronizer.getGameAreaWidth());

    }

    public void drawGameOver() {
        controller.printGameOver();
    }

    public void graphicsTimer() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000 / frameRate), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if (!synchronizer.isGameOver()) {
                    drawScreen();
                } else {
                    drawGameOver();
                }

            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void mainProgram() {
        // do stuff here, feed the queue

        newGame();

    }

    public void newGame() {
        synchronizer = new Synchronizer();
        game = new Game(synchronizer);
        game.runNewGame(1);

    }


    public static void main(String[] args) {
        launch(args);
    }

    public void setup() {
        controller = new Controller();


        initializeNewGame.bindBidirectional(controller.initializeNewGame);
        initializeNewGame.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean aBoolean2) {
                game.world.gameEngine.engineTimer.cancel();
                game.world.gameEngine.engineTimer.purge();
                newGame();
            }
        });

    }

}
