package ir.ac.kntu.scene;

import ir.ac.kntu.items.*;
import ir.ac.kntu.util.Direction;
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
    private GridPane pane;
    private ArrayList<Player> players;
    private ArrayList<Block> blocks;
    private ArrayList<Wall> walls;
    private ArrayList<PowerUp> powerUps;
    private ArrayList<OneWay> oneWays;
    private Scene scene;

    public GameMap() {
        players = new ArrayList<>();
        blocks = new ArrayList<>();
        walls = new ArrayList<>();
        powerUps = new ArrayList<>();
        oneWays = new ArrayList<>();
        pane = new GridPane();
        pane.setVgap(1);
        pane.setHgap(1);
        initMap();
        scene = new Scene(pane, Color.WHITE);
    }

    private void initMap() {
        loadMapFromFile();
        initBackGround();
        initPlayers();
        initOtherItems();
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
    }

    private void initOtherItems() {
        for (int i = 0; i < items.length; i++) {
            for (int j = 0; j < items[0].length; j++) {
                Node temp = null;
                switch (items[i][j]) {
                    case 0:
                        temp = new ImageView(new Image("assets/map/wall.png"));
                        walls.add(new Wall(pane, temp, false));
                        break;
                    case 5:
                        temp = new ImageView(new Image("assets/map/oneway/oneway_up.png"));
                        oneWays.add(new OneWay(pane, temp, true, Direction.UP));
                        break;
                    case 6:
                        temp = new ImageView(new Image("assets/map/oneway/oneway_left.png"));
                        oneWays.add(new OneWay(pane, temp, true, Direction.LEFT));
                        break;
                    case 10:
                        temp = new ImageView(new Image("assets/map/oneway/oneway_right.png"));
                        oneWays.add(new OneWay(pane, temp, true, Direction.RIGHT));
                        break;
                    case 11:
                        temp = new ImageView(new Image("assets/map/oneway/oneway_down.png"));
                        oneWays.add(new OneWay(pane, temp, true, Direction.DOWN));
                        break;
                    case 7:
                        temp = new ImageView(new Image("assets/map/powerup.png"));
                        powerUps.add(new PowerUp(pane, temp, true));
                        break;
                    case 8:
                        temp = new ImageView(new Image("assets/map/block.png"));
                        blocks.add(new Block(pane, temp, false));
                        break;
                    default:
                        break;
                }
                if (temp != null) {
                    pane.add(temp, j, i);
                }
            }
        }
    }

    public void initPlayers() {
        for (int i = 0; i < items.length; i++) {
            for (int j = 0; j < items[0].length; j++) {
                Node temp = null;
                switch (items[i][j]) {
                    case 1:
                        temp = new ImageView(new Image("assets/player/player_down_standing.png"));
                        players.add(new Player(1, pane, temp));
                        break;
                    case 2:
                        temp = new ImageView(new Image("assets/player_black/player_black_down_standing.png"));
                        players.add(new Player(2, pane, temp));
                        break;
                    case 3:
                        temp = new ImageView(new Image("assets/player_red/player_red_down_standing.png"));
                        players.add(new Player(3, pane, temp));
                        break;
                    case 4:
                        temp = new ImageView(new Image("assets/player_yellow/player_yellow_down_standing.png"));
                        players.add(new Player(4, pane, temp));
                        break;
                    default:
                        break;
                }
                if (temp != null) {
                    pane.add(temp, j, i);
                }
            }
        }
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

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }

    public ArrayList<PowerUp> getPowerUps() {
        return powerUps;
    }

    public ArrayList<OneWay> getOneWays() {
        return oneWays;
    }

    public GridPane getPane() {
        return pane;
    }
}
