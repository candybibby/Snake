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


    @FXML
    AnchorPane bottomPane;

    @FXML
    Button newGameButton;

    @FXML
    TextArea textArea;

    SimpleBooleanProperty initializeNewGame = new SimpleBooleanProperty(false);


    public Controller() {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainPane.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void initialize() {
        textArea.setEditable(false);
        textArea.setFont(Font.font(java.awt.Font.MONOSPACED));
        setupNewGameButton();


    }

    public void setupNewGameButton() {
        //initializeNewGame.set(false);

        newGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                initializeNewGame.set(true);
            }
        });
    }


    public void drawNewScreen(char[][] array, int height, int width) {
        clearScreen();
        String drawThis = "";
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {


                if (array[j][i] == '\0') {
                    drawThis += "  ";
                } else {
                    drawThis += array[j][i] + " ";
                }
            }
            drawThis += "\n";
        }

        textArea.setText(drawThis);
    }

    public void clearScreen() {
        textArea.clear();
    }

    public void printGameOver() {
        textArea.setText("GAME OVER!!!");
    }

}
