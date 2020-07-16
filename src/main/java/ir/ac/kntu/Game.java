package ir.ac.kntu;

import ir.ac.kntu.items.*;
import ir.ac.kntu.scene.GameMap;
import ir.ac.kntu.scene.Menu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.List;

public class Game extends Application {
    private final List<Player> players;
    private List<Bomb> bombs;
    private List<Blcok> blocks;
    private List<Wall> walls;
    private List<Normal> normals;
    private List<PowerUp> powerUps;
    private List<OneWay> oneWays;
    private final GameMap gameMap;
    private Menu menu;
    private final Scene scene;
    private Pane pane;
    private Thread timer;


    public Game() {
        gameMap = new GameMap();
        scene = gameMap.getScene();
        players = gameMap.getPlayers();
        initScene();
    }

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Fariborz Bobmerman");

        stage.show();
    }

    public void initScene() {


        scene.setOnKeyPressed(keyEvent -> {
            KeyCode temp = keyEvent.getCode();
            for (Player p : players) {
                if (p.getKeys().contains(temp)) {
                    p.handleMove(temp);
                    break;
                }
            }
        });
    }


    public void run() {

    }
}
