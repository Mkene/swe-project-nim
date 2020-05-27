package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import nimgame.results.GameResult;
import nimgame.results.GameResultDao;



import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class Game14RowsController {

    private String play1;
    private String play2;
    private Instant beginGame;
    private int steps;
    private String winner;
    private boolean isSolved = false;
    private GameResultDao gameResultDao;
    private String BLUE1 = "/pictures/blue_play01.jpg";
    private String BLUE2 = "/pictures/blue_play03.jpg";
    private String BLUE3 = "/pictures/blue_play04.jpg";
    private String BLUE4 = "/pictures/blue_play05.jpg";
    private String BLUE5 = "/pictures/blue_play06.jpg";
    private String YELLOW = "/pictures/yellow_play1.jpg";

    @FXML
    private Label solvedLabel;
    @FXML
    private GridPane gameGrid;
    @FXML
    private Circle pl1turn;
    @FXML
    private Circle pl2turn;
    @FXML
    private Label playerSteps;
    @FXML
    private Label player1name;
    @FXML
    private Label player2name;
    @FXML
    private Button doneButton;

    @FXML
    void paneMouseClicked(MouseEvent event) {
        //int rowIndex = (GridPane.getRowIndex((Node) event.getSource()) == null) ? 0 : GridPane.getRowIndex((Node) event.getSource());
        int colIndex = (GridPane.getColumnIndex((Node) event.getSource()) == null) ? 0 : GridPane.getColumnIndex((Node) event.getSource());
      //  if (availablePanesToMove.contains((Pane) event.getSource()) && event.getSource() instanceof Pane){

       // }

    }
    /**
     * Sets the name of the players in game.fxml.
     */
    public void initdata(String play1, String play2) {
        this.play1 = play1;
        this.play2 = play2;

        player1name.setText("Player 1: " + this.play1);
        player2name.setText("Player 2: " + this.play2);
    }

    /**
     * Resets the game to a starting state.
     *
     * @param actionEvent An action which is sent when a button is pressed.
     */
    public void resetGame(ActionEvent actionEvent) {
        initialize();
        beginGame = Instant.now();
        steps = 0;
        playerSteps.setText(String.valueOf(steps));
        solvedLabel.setText("");
        doneButton.setText("Give Up");
        pl1turn.setOpacity(1.0);
        pl2turn.setOpacity(0.0);
        //log.info("Game reset.");
    }

    /**
     * Switches player turns and changes the small red circle to the current player to indicate that it is the player's turn.
     */
    private void switchPlayerTurn() {
        if (pl1turn.getOpacity() == 1) {
            pl1turn.setOpacity(0);
            pl2turn.setOpacity(1);
        } else {
            pl1turn.setOpacity(1);
            pl2turn.setOpacity(0);
        }
    }

    /**
     * Initializes the game by setting a circle in a random position in the board,
     * recording the date of when the game was started and setting the stepCount to 0.
     */

    @FXML
    public void initialize() {
        gameResultDao = GameResultDao.getInstance();

        steps = 0;
        solvedLabel.setText("");
        beginGame = Instant.now();

        pl1turn.setOpacity(1);
        pl2turn.setOpacity(0);

        //gameGrid.add(BLUE1,1 ,0);
        //gameGrid.add(BLUE2,GridPane.getColumnIndex("3",) ,GridPane.getRowIndex("0"));
        //gameGrid.add(BLUE3,GridPane.getColumnIndex("4",) ,GridPane.getRowIndex("0"));
        //gameGrid.add(BLUE4,GridPane.getColumnIndex("6",) ,GridPane.getRowIndex("0"));


    }
    /**
     * Increases the stepCount and changes it in game.fxml.
     */
    public void incrementStepCount() {
        steps++;
        playerSteps.setText(String.valueOf(steps));
    }

    /**
     *
     * @param actionEvent An action which is sent when a button is pressed.
     * @throws IOException An exception that is caught if an error occurs.
     */
    public void finishGame(ActionEvent actionEvent) throws IOException {
        if (isSolved) {
            gameResultDao.persist(getResult());
        }

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/top_ten.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
        //log.info("Finished game, loading Top Ten scene.");
    }
    private GameResult getResult() {

        GameResult result = GameResult.builder()
                .player(winner)
                .solved(isSolved)
                .duration(java.time.Duration.between(beginGame, Instant.now()))
                .steps(steps)
                .build();
        return result;
    }
}
   // private Image image1 = new Image(getClass().getResource("/pictures/blue_play01.jpg").toExternalForm());
    //private Image image2 = new Image(getClass().getResource("/pictures/blue_play03.jpg").toExternalForm());
    //private Image image3 = new Image(getClass().getResource("/pictures/blue_play04.jpg").toExternalForm());
    //private Image image4 = new Image(getClass().getResource("/pictures/blue_play05.jpg").toExternalForm());
    //private Image image5 = new Image(getClass().getResource("/pictures/blue_play06.jpg").toExternalForm());
    //private Image image = new Image(getClass().getResource("/pictures/yellow_play1.jpg").toExternalForm());
