package graphicsJavaFX;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Controller extends AnchorPane {


    @FXML
    AnchorPane bottomPane;

    @FXML
    Button newGameButton;

    @FXML
    TextArea textArea;



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

    


}
