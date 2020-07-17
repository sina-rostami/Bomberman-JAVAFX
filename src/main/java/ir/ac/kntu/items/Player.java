package ir.ac.kntu.items;

import ir.ac.kntu.util.Direction;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;


public class Player {
    private Scene scene;
    private GridPane pane;
    private Node node;
    private int rowIndex;
    private int columnIndex;
    private List<KeyCode> keys;
    private String rootAddress = "assets/";
    private String address;
    private String name;
    private String state;
    private ImageView iv;
    private Image img;
    private final int id;

    public Player(int id, GridPane pane, Scene scene, Node node) {
        this.id = id;
        this.pane = pane;
        this.scene = scene;
        this.node = node;
        keys = new ArrayList<>();
        makeName();
        state = "down_standing";
        address = rootAddress + name + state + ".png";
        initKeys();
    }

    private void initKeys() {
        if (id == 1) {
            keys.add(KeyCode.UP);
            keys.add(KeyCode.RIGHT);
            keys.add(KeyCode.DOWN);
            keys.add(KeyCode.LEFT);
        } else if (id == 2) {
            keys.add(KeyCode.W);
            keys.add(KeyCode.D);
            keys.add(KeyCode.S);
            keys.add(KeyCode.A);
        } else if (id == 3) {
            keys.add(KeyCode.NUMPAD8);
            keys.add(KeyCode.NUMPAD6);
            keys.add(KeyCode.NUMPAD5);
            keys.add(KeyCode.NUMPAD4);
        } else if (id == 4) {
            keys.add(KeyCode.I);
            keys.add(KeyCode.L);
            keys.add(KeyCode.K);
            keys.add(KeyCode.J);
        }
    }

    public Node getPlayer() {
        return iv;
    }

    private void makeName() {
        switch (id) {
            case 1:
                name = "player/player_";
                break;
            case 2:
                name = "player_black/player_black_";
                break;
            case 3:
                name = "player_red/player_red_";
                break;
            case 4:
                name = "player_yellow/player_yellow_";
                break;
            default:
                break;

        }

    }

    public void change(Direction dir) {
        switch (dir) {
            case UP:
                --rowIndex;
                break;
            case DOWN:
                ++rowIndex;
                break;
            case LEFT:
                --columnIndex;
                break;
            case RIGHT:
                ++columnIndex;
                break;
            default:
                break;
        }
        setState(dir);
        address = rootAddress + name + state + ".png";
        node = new ImageView(new Image(address));
    }

    private void setState(Direction dir) {
        switch (dir) {
            case UP:
                state = "up_moving";
                break;
            case DOWN:
                state = "down_moving";
                break;
            case LEFT:
                state = "left_moving";
                break;
            case RIGHT:
                state = "right_moving";
                break;
            default:
                break;
        }
    }

    public ArrayList<KeyCode> getKeys() {
        return (ArrayList<KeyCode>) keys;
    }

    public void handleMove(KeyCode temp) {
        rowIndex = pane.getRowIndex(node);
        columnIndex = pane.getColumnIndex(node);
        pane.getChildren().remove(node);
        if (temp.equals(keys.get(0))) {
            change(Direction.UP);
            pane.add(node, columnIndex, rowIndex);
        } else if (temp.equals(keys.get(1))) {
            change(Direction.RIGHT);
            pane.add(node, columnIndex, rowIndex);
        } else if (temp.equals(keys.get(2))) {
            change(Direction.DOWN);
            pane.add(node, columnIndex, rowIndex);
        } else if (temp.equals(keys.get(3))) {
            change(Direction.LEFT);
            pane.add(node, columnIndex, rowIndex);
        } else {
            System.out.println("Wrong for player " + id);
        }
    }
}
