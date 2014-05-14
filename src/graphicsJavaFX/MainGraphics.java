package graphicsJavaFX;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import package1.*;

public class MainGraphics extends Application {

    Controller bottomFrame;

    int blockingQueueSize = 100;
    int frameRate = 20;



    @Override
    public void start(Stage primaryStage) throws Exception {
        bottomFrame = new Controller();
        primaryStage.setTitle("MainPane");
        primaryStage.setScene(new Scene(bottomFrame));
        primaryStage.show();
        graphicsTimer();
        mainProgram();
    }



    public void drawScreen(){



    }

    public void graphicsTimer(){
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000/frameRate), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                drawScreen();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void mainProgram(){
        // do stuff here, feed the queue

        Game game = new Game();

        game.runNewGame(1);

    }






    public static void main(String[] args) {
        launch(args);
    }
}
