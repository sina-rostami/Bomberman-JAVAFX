package ir.ac.kntu;

import ir.ac.kntu.items.*;
import ir.ac.kntu.scene.GameMap;
import ir.ac.kntu.scene.Menu;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.util.ArrayList;
import java.util.List;

public class Game extends Application {
    private final List<Player> players;
    private List<Bomb> bombs;
    private List<Block> blocks;
    private List<Wall> walls;
    private List<PowerUp> powerUps;
    private List<OneWay> oneWays;
    private GameMap gameMap;
    private Menu menu;
    private Scene scene;
    private GridPane pane;
    private Thread timer;

    public Game() {
        gameMap = new GameMap();
        scene = gameMap.getScene();
        pane = gameMap.getPane();
        players = new ArrayList<>(gameMap.getPlayers());
        walls = gameMap.getWalls();
        blocks = gameMap.getBlocks();
        powerUps = gameMap.getPowerUps();
        oneWays = gameMap.getOneWays();

    }
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        initScene();
        intiRandomObjects();
        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Fariborz Bobmerman");
        stage.show();
    }
    private void intiRandomObjects() {

    }
    public void initScene() {
        for(Player p : players) {
            p.setGame(this);
        }
        scene.setOnKeyPressed(keyEvent -> {
            KeyCode temp = keyEvent.getCode();
            for (Player p : players) {
                if (p.isAlive() && p.getKeys().contains(temp)) {
                    p.handleMove(temp);
                    return;
                }
            }
            if (temp == KeyCode.ESCAPE) {
                System.out.println("Exited");
                System.exit(0);
            }
        });
    }

    public void handleEndOfGame() {
        int max = 0;
        Player winner = null;
        for (Player p : players) {
            if(p.getScore() > max) {
                max = p.getScore();
                winner = p;
            }
        }
        Player finalWinner = winner;
        Platform.runLater(() -> {
            int i = -2;
            for(Player p : players) {
                if(p.isAlive()) {
                    p.setKilled();
                }
                if(pane.getChildren().contains(p.getNode())) {
                    pane.getChildren().remove(p.getNode());
                }
                pane.addColumn(i += 2, p.getNode());
                Text t = new Text(p.getScore() + (p == finalWinner ? " Win" : ""));
                pane.addColumn( i + 1, t);
                GridPane.setHalignment(p.getNode(), HPos.CENTER);
                GridPane.setHalignment(t, HPos.CENTER);
            }
        });
        System.out.println(winner.getName() + " score " + max);
    }
}
