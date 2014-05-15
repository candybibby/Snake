package graphicsJavaFX;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import package1.*;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.awt.*;
import java.io.IOException;

public class Controller extends AnchorPane {


    @FXML
    // this tag gets the javafxid from the fxml file
            AnchorPane bottomPane;

    @FXML
    Button newGameButton;

    @FXML
    AnchorPane gameAreaContainer;

    SimpleBooleanProperty initializeNewGame = new SimpleBooleanProperty(false); // this is used to pass information between the controller and the mainGraphics class

    Direction[] directions = {
            new Direction(Direction.RIGHT)
    };

    private int height;
    private int width;


    private double gridSquareHeight;
    private double gridSquareWidth;

    GridPane gameGrid;


    public Controller() {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainPane.fxml")); // load the MainPane.fxml which contains the information on the graphical layout of the window
        fxmlLoader.setRoot(this);               // sets the fxml file as the root
        fxmlLoader.setController(this);         // and this as it's controller

        try {
            fxmlLoader.load();                  // tries to load the file
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void initialize() {                 // this runs when the controller is initializedirections[p]. It's called automatically


        setupNewGameButton();                               // setup the new game button
        setupKeyListener();


    }

    public void setupNewGameButton() {
        //initializeNewGame.set(false);

        newGameButton.setOnAction(new EventHandler<ActionEvent>() { // when button is pressed
            @Override
            public void handle(ActionEvent actionEvent) {
                initializeNewGame.set(!initializeNewGame.getValue());       // flip the value of initializeNewGame so the mainGraphics.java knows to start a new game
            }
        });
    }


    public void buildGrid() {
        gameGrid = new GridPane();
        gameGrid.setGridLinesVisible(true);
        for (int i = 0; i < width; i++) {
            gameGrid.getColumnConstraints().add(new ColumnConstraints(gridSquareWidth));
        }

        for (int i = 0; i < height; i++) {
            gameGrid.getRowConstraints().add(new RowConstraints((gridSquareHeight)));
        }


        gameAreaContainer.getChildren().add(gameGrid);
    }


    public void drawNewScreen(char[][] array) {      // draw a new screen based on the variables given
        clearScreen();          // clear the screen

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {


                if (array[j][i] == '\0') {

                } else {


                    Circle circle = new Circle(gridSquareHeight / 2, Color.DARKRED);

                    GridPane.setConstraints(circle, j, i);
                    gameGrid.getChildren().add(circle);

                }
            }

        }


        bottomPane.requestFocus();
    }

    public void clearScreen() {

        gameGrid.getChildren().clear();

    }


    public void setupKeyListener() {

        bottomPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                int p = 0; //Current number of players
                KeyCode keyCode = keyEvent.getCode();
                switch (keyCode) {
                    case UP:
                        if (directions[p].getValue() != Direction.DOWN)  //If you are going down you can't turn up, or you will die.
                            directions[p].setDirection(Direction.UP);
                        break;
                    case DOWN:
                        if (directions[p].getValue() != Direction.UP)
                            directions[p].setDirection(Direction.DOWN);
                        break;
                    case LEFT:
                        if (directions[p].getValue() != Direction.RIGHT)
                            directions[p].setDirection(Direction.LEFT);
                        break;
                    case RIGHT:
                        if (directions[p].getValue() != Direction.LEFT)
                            directions[p].setDirection(Direction.RIGHT);
                        break;
                }

                Synchronizer.setLastButtonPressed(directions);
            }
        });
    }

    public void giveHeightAndWidth(int height, int width) {

        this.height = height;
        this.width = width;
        this.gridSquareWidth = gameAreaContainer.getWidth() / width;
        this.gridSquareHeight = gameAreaContainer.getHeight() / height;
    }

}
