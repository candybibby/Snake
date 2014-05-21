package graphicsJavaFX;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import package1.Direction;
import package1.Synchronizer;
import sounds.SoundMaker;

import java.io.IOException;

public class Controller extends AnchorPane {


    @FXML
    // this tag gets the javafxid from the fxml file
            AnchorPane bottomPane;

    @FXML
    AnchorPane newGamePane;

    @FXML
    AnchorPane RulesPane;

    @FXML
    AnchorPane multiplayerPane;

    @FXML
    AnchorPane CreditsPane;

    @FXML
    AnchorPane optionPane;


    @FXML
    Button creditsButton;

    @FXML
    Button rulesButton;

    @FXML
    Button optionsButton;

    @FXML
    Button easyButton;

    @FXML
    Button hardButton;

    @FXML
    Button mediumButton;

    @FXML
    Button expertButton;

    @FXML
    Button backButton;

    @FXML
    Button backButtonRules;

    @FXML
    Button player1Button;

    @FXML
    Button player2Button;

    @FXML
    Button backButtonmulti;

    @FXML
    Button backButtonCredits;

    @FXML
    Button backButtonOptions;

    @FXML
    Slider lengthSlider;

    @FXML
    Slider speedSlider;


    @FXML
    AnchorPane gameAreaContainer;


    @FXML
    Button startGameButton;

    @FXML
    AnchorPane menuPane;

    @FXML
    Label scoreLabel;
    @FXML
    Label highScoreLabel;

    @FXML
    TextArea textGameOver;

    @FXML
    VBox gameAreaMainVBox;

    SimpleBooleanProperty initializeNewGame = new SimpleBooleanProperty(false); // this is used to pass information between the controller and the mainGraphics class
    SimpleBooleanProperty startNewGame = new SimpleBooleanProperty(false);


    private int height;
    private int width;
    private int theme = 0;      // 1 is for light theme, 0 is for dark theme


    private double gridSquareHeight;
    private double gridSquareWidth;

    public int difficultyLevel;

    GridPane gameGrid;


    SoundMaker ButtonffectPlayer;


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

        //textGameOver.toBack();
        //menuPane.toBack();

        ButtonffectPlayer = new SoundMaker();
        setupMainMenu();
        setupDifficultyPane();
        setupRulesAndCreditPanes();
        setupMultiplayerPane();
        setupKeyListener();
        setupGameArea();
        setupOptionsPane();


    }

    public void setupMainMenu() {
        //initializeNewGame.set(false);


        startGameButton.setOnAction(new EventHandler<ActionEvent>() { // when button is pressed
            @Override
            public void handle(ActionEvent actionEvent) {
                multiplayerPane.toFront();
                ButtonffectPlayer.clickButton();

            }
        });

        rulesButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                RulesPane.toFront();
                ButtonffectPlayer.clickButton();
            }
        });

        optionsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                optionPane.toFront();
                ButtonffectPlayer.clickButton();
            }
        });

        creditsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                CreditsPane.toFront();
                ButtonffectPlayer.clickButton();
            }
        });
    }

    public void setupRulesAndCreditPanes() {
        backButtonCredits.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                CreditsPane.toBack();
                ButtonffectPlayer.backButton();
            }
        });

        backButtonRules.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                RulesPane.toBack();
                ButtonffectPlayer.backButton();
            }
        });
    }

    public void setupMultiplayerPane() {
        backButtonmulti.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                multiplayerPane.toBack();
                ButtonffectPlayer.backButton();
            }
        });


        player1Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ButtonffectPlayer.clickButton();
                newGamePane.toFront();
            }
        });

        player2Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ButtonffectPlayer.clickButton();
                multiplayerPane.toBack();
                newGamePane.toFront();
                Synchronizer.setNumberOfPlayer(2);
                Synchronizer.setTwoPlayerGame();
            }
        });


    }

    public void setupDifficultyPane() {
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                newGamePane.toBack();
                ButtonffectPlayer.backButton();
            }
        });


        easyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                startNewGame.setValue(true);
                ButtonffectPlayer.clickButton();

            }
        });

        mediumButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ButtonffectPlayer.clickButton();
                Synchronizer.setDifficulty(1);
                startNewGame.setValue(true);
            }
        });

        hardButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ButtonffectPlayer.clickButton();
                Synchronizer.setDifficulty(2);
                startNewGame.setValue(true);
            }
        });

        expertButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ButtonffectPlayer.clickButton();
                Synchronizer.setDifficulty(3);
                startNewGame.setValue(true);
            }
        });

    }

    public void setupOptionsPane() {
        backButtonOptions.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                optionPane.toBack();
                ButtonffectPlayer.backButton();
            }
        });


        lengthSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                System.out.println(lengthSlider.getValue());
            }
        });

        speedSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                System.out.println(speedSlider.getValue());
                Synchronizer.setSquaresPerSecond((int) speedSlider.getValue());
            }
        });


    }


    public void buildGrid() {
        gameGrid = new GridPane();
        //gameGrid.setGridLinesVisible(true);
        for (int i = 0; i < width; i++) {
            gameGrid.getColumnConstraints().add(new ColumnConstraints(gridSquareWidth));
        }

        for (int i = 0; i < height; i++) {
            gameGrid.getRowConstraints().add(new RowConstraints((gridSquareHeight)));
        }

        if (theme == 1) {
            gameGrid.getStyleClass().add("whiteBackgroundStyle");
        } else {
            gameGrid.getStyleClass().add("blackBackgroundStyle");
        }

        gameAreaContainer.getChildren().add(gameGrid);
    }


    public void drawNewScreen(char[][] array) {      // draw a new screen based on the variables given
        clearScreen();          // clear the screen

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {


                if (array[j][i] == '\0') {

                } else {
                    Shape shape = chooseColor(array, j, i);
                    GridPane.setConstraints(shape, j, i);
                    gameGrid.getChildren().add(shape);


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
                KeyCode keyCode = keyEvent.getCode();
                switch (keyCode) {
                    case UP:
                        if (Synchronizer.getLastButtonPressed(0).getValue() != Direction.DOWN)
                            Synchronizer.setLastButtonPressed(new Direction(Direction.UP), 0);
                        break;
                    case DOWN:
                        if (Synchronizer.getLastButtonPressed(0).getValue() != Direction.UP)
                            Synchronizer.setLastButtonPressed(new Direction(Direction.DOWN), 0);
                        break;
                    case LEFT:
                        if (Synchronizer.getLastButtonPressed(0).getValue() != Direction.RIGHT)
                            Synchronizer.setLastButtonPressed(new Direction(Direction.LEFT), 0);
                        break;
                    case RIGHT:
                        if (Synchronizer.getLastButtonPressed(0).getValue() != Direction.LEFT)
                            Synchronizer.setLastButtonPressed(new Direction(Direction.RIGHT), 0);
                        break;
                    case W:
                        if (Synchronizer.getNumberOfPlayer() == 2 && Synchronizer.getLastButtonPressed(1).getValue() != Direction.DOWN)
                            Synchronizer.setLastButtonPressed(new Direction(Direction.UP), 1);
                        break;
                    case S:
                        if (Synchronizer.getNumberOfPlayer() == 2 && Synchronizer.getLastButtonPressed(1).getValue() != Direction.UP)
                            Synchronizer.setLastButtonPressed(new Direction(Direction.DOWN), 1);
                        break;
                    case A:
                        if (Synchronizer.getNumberOfPlayer() == 2 && Synchronizer.getLastButtonPressed(1).getValue() != Direction.RIGHT)
                            Synchronizer.setLastButtonPressed(new Direction(Direction.LEFT), 1);
                        break;
                    case D:
                        if (Synchronizer.getNumberOfPlayer() == 2 && Synchronizer.getLastButtonPressed(1).getValue() != Direction.LEFT)
                            Synchronizer.setLastButtonPressed(new Direction(Direction.RIGHT), 1);
                        break;
                }
            }
        });
    }

    public void giveHeightAndWidth(int height, int width) {

        this.height = height;
        this.width = width;
        this.gridSquareWidth = (gameAreaContainer.getWidth() / width);
        this.gridSquareHeight = (gameAreaContainer.getHeight() / (height));
    }

    public void setupGameArea() {
        gameAreaContainer.setStyle("-fx-background-color: Black");
    }

    public void sendMenuBack() {
        menuPane.toBack();
    }

    public int getGameDifficulty() {
        return difficultyLevel;
    }

    private Shape chooseColor(char[][] matrix, int x, int y) {
        Shape shape = null;
        switch (matrix[x][y]) {
            case 'S':
                shape = new Circle(gridSquareHeight * 0.6, Color.BLUE);
                break;
            case 'o':
                shape = new Circle(gridSquareHeight * 0.6, Color.GREEN);
                break;
            case 'T':
                shape = new Circle(gridSquareHeight * 0.6, Color.BLUE);
                break;
            case 'e':
                shape = new Circle(gridSquareHeight * 0.6, Color.ORANGE);
                break;
            case 'X':
                shape = new Rectangle(gridSquareWidth - 1, gridSquareHeight - 1, Color.GREY);
                break;
            case 'm':
                shape = new Circle(gridSquareHeight / 2, Color.RED);
                break;
        }
        return shape;
    }

    public void updateScore(int score) {
        scoreLabel.setText(String.valueOf(score));
    }

    public void setHighScore(int score) {
        highScoreLabel.setText(String.valueOf(score));
    }

    /*public void displayGameOver() {
        textGameOver.toFront();
        gameAreaContainer.setOpacity(.3);

    }*/

    public void setMenuOpacity(double opacity) {
        menuPane.setOpacity(opacity);
    }

    public void hideGameOver() {
        textGameOver.toBack();
        gameAreaContainer.setOpacity(1);
    }

    public void setTheme(int theme) {
        this.theme = theme;


    }

    public void startGame() {
        newGamePane.toBack();
        gameAreaMainVBox.toFront();

    }

}
