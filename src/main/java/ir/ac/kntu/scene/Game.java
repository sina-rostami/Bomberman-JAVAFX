package ir.ac.kntu.scene;

import ir.ac.kntu.items.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Game extends Application implements Serializable {
    private List<Player> players;
    private List<Block> blocks;
    private List<Wall> walls;
    private List<PowerUp> powerUps;
    private List<OneWay> oneWays;
    private GameMap gameMap;
    private Scene scene;
    private GridPane pane;
    private boolean isDone;
    private int playersCount;
    private Thread timer;
    public Game(int num, GameMap gameMap) {
        playersCount = num;
        this.gameMap = gameMap;
        scene = gameMap.getScene();
        pane = gameMap.getPane();
        players = new ArrayList<>(gameMap.getPlayers());
        walls = gameMap.getWalls();
        blocks = gameMap.getBlocks();
        powerUps = gameMap.getPowerUps();
        oneWays = gameMap.getOneWays();
    }
    @Override
    public void start(Stage stage) {
        startTimer();
        initScene();
        intiRandomObjects();
        stage.setScene(scene);
        stage.setTitle("Fariboorz Bobmerman");
        stage.show();
    }

    private void startTimer() {
        timer = new Thread(() -> {
            try {
                Thread.sleep(1000 * 60 * 3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!this.isDone()) {
                Platform.runLater(() -> {
                    getPane().addRow(getPane().getRowCount() - 1, new Text("TimeUp"));
                });
                handleEndOfGame();
            }
        });
        timer.start();
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
        isDone = true;

        int max = 0;
        Player winner = null;
        for (Player p : players) {
            if(p.isAlive()) {
                winner = p;
                break;
            }
            if(p.getScore() >= max) {
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
    }
    public boolean isDone() {
        return isDone;
    }
    public GridPane getPane() {
        return pane;
    }
    public GameMap getMap() {
        return gameMap;
    }
}
