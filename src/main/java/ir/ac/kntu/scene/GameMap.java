package ir.ac.kntu.scene;

import ir.ac.kntu.items.Player;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameMap implements Presentable {
    private int[][] items;
    private final GridPane pane;
    private final ArrayList<Player> players;
    private final ArrayList<Node> blocks;
    private final ArrayList<Node> walls;
    private final ArrayList<Node> powerUps;
    private final ArrayList<Node> oneWays;
    private final Scene scene;

    public GameMap() {
        players = new ArrayList<>();
        blocks = new ArrayList<>();
        walls = new ArrayList<>();
        powerUps = new ArrayList<>();
        oneWays = new ArrayList<>();
        pane = new GridPane();
        loadMapFromFile();
        initBackGround();
        scene = new Scene(pane,  Color.WHITE);
    }



    public void loadMapFromFile() {
        File file = new File("src/main/resources/maps/map1.txt");
        if (file.exists()) {
            try (Scanner in = new Scanner(file)) {
                items = new int[in.nextInt()][in.nextInt()];
                for (int i = 0; i < items.length; i++) {
                    for (int j = 0; j < items[i].length; j++) {
                        items[i][j] = in.nextInt();
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Scene getScene() {
        return scene;
    }

    public void initBackGround() {
        for (int i = 0; i < items.length; i++) {
            for (int j = 0; j < items[0].length; j++) {
                pane.add(new ImageView(new Image("assets/map/normal.png")), j, i);
            }
        }
        initPlayers();
    }
    public void initPlayers() {
        for (int i = 0; i < items.length; i++) {
            for (int j = 0; j < items[0].length; j++) {
                Node temp = null;
                switch (items[i][j]) {
                    case 0:
                        temp = new ImageView(new Image("assets/map/wall.png"));
                        walls.add(temp);
                        break;
                    case 1:
                        temp = new ImageView(new Image("assets/player/player_down_standing.png"));
                        players.add(new Player(1, pane, scene, temp));
                        break;
                    case 2:
                        temp = new ImageView(new Image("assets/player_black/player_black_down_standing.png"));
                        players.add(new Player(2, pane, scene, temp));
                        break;
                    case 3:
                        temp = new ImageView(new Image("assets/player_red/player_red_down_standing.png"));
                        players.add(new Player(3, pane, scene, temp));
                        break;
                    case 4:
                        temp = new ImageView(new Image("assets/player_yellow/player_yellow_down_standing.png"));
                        players.add(new Player(4, pane, scene, temp));
                        break;
                    case 5:
                        temp = new ImageView(new Image("assets/map/oneway/oneway_up.png"));
                        oneWays.add(temp);
                        break;
                    case 6:
                        temp = new ImageView(new Image("assets/map/oneway/oneway_left.png"));
                        oneWays.add(temp);
                        break;
                    case 7:
                        temp = new ImageView(new Image("assets/map/powerup.png"));
                        powerUps.add(temp);
                        break;
                    case 8:
                        temp = new ImageView(new Image("assets/map/block.png"));
                        blocks.add(temp);
                        break;
                    case 10:
                        temp = new ImageView(new Image("assets/map/oneway/oneway_right.png"));
                        oneWays.add(temp);
                        break;
                    case 11:
                        temp = new ImageView(new Image("assets/map/oneway/oneway_down.png"));
                        oneWays.add(temp);
                        break;
                    default:
                        break;
                }
                if(temp != null) {
                    pane.add(temp, j, i);
                }
            }
        }
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Node> getBlocks() {
        return blocks;
    }

    public ArrayList<Node> getWalls() {
        return walls;
    }

    public ArrayList<Node> getPowerUps() {
        return powerUps;
    }

    public ArrayList<Node> getOneWays() {
        return oneWays;
    }

    public GridPane getPane() {
        return pane;
    }
}
