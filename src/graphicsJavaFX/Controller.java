package graphicsJavaFX;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.io.IOException;

public class Controller extends AnchorPane {


    @FXML                       // this tag gets the javafxid from the fxml file
    AnchorPane bottomPane;

    @FXML
    Button newGameButton;

    @FXML
    TextArea textArea;

    SimpleBooleanProperty initializeNewGame = new SimpleBooleanProperty(false); // this is used to pass information between the controller and the mainGraphics class


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

    public void initialize() {                 // this runs when the controller is initialized. It's called automatically
        textArea.setEditable(false);            // don't let the user edit the textfield
        textArea.setFont(Font.font(java.awt.Font.MONOSPACED)); // use a monospace font to preserve uniform dimensions
        setupNewGameButton();                               // setup the new game button


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


    public void drawNewScreen(char[][] array, int height, int width) {      // draw a new screen based on the variables given
        clearScreen();          // clear the screen
        String drawThis = "";       // make a tempString to store the array in
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {


                if (array[j][i] == '\0') {
                    drawThis += "  ";           // for every empty character make two spaces
                } else {
                    drawThis += array[j][i] + " ";      // if the is a character, put it in the string with an empty space
                }
            }
            drawThis += "\n";           // after each line we need a new line
        }

        textArea.setText(drawThis);     // set this as the text in the textfield
    }

    public void clearScreen() {
        textArea.clear();
    }

    public void printGameOver() {
        textArea.setText("GAME OVER!!!");
    }

}
