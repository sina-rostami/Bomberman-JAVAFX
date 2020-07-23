package ir.ac.kntu.items;

import ir.ac.kntu.scene.Game;
import ir.ac.kntu.util.Direction;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class Player implements Serializable {
    private int id;
    private GridPane pane;
    private Node node;
    private Game game;
    private int rowIndex;
    private int columnIndex;
    private List<KeyCode> keys;
    private List<Wall> walls;
    private List<Block> blocks;
    private List<OneWay> oneWays;
    private List<Player> players;
    private String rootAddress = "assets/";
    private String address;
    private String name;
    private String state;
    private int score;
    private boolean isAlive;
    private boolean hasActiveBomb;
    private boolean hasPower;

    public Player(int id, GridPane pane, Node node) {
        this.id = id;
        this.pane = pane;
        this.node = node;
        score = 0;
        isAlive = true;
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
            keys.add(KeyCode.SHIFT);
        } else if (id == 2) {
            keys.add(KeyCode.W);
            keys.add(KeyCode.D);
            keys.add(KeyCode.S);
            keys.add(KeyCode.A);
            keys.add(KeyCode.Q);
        } else if (id == 3) {
            keys.add(KeyCode.NUMPAD8);
            keys.add(KeyCode.NUMPAD6);
            keys.add(KeyCode.NUMPAD5);
            keys.add(KeyCode.NUMPAD4);
            keys.add(KeyCode.NUMPAD7);
        } else if (id == 4) {
            keys.add(KeyCode.I);
            keys.add(KeyCode.L);
            keys.add(KeyCode.K);
            keys.add(KeyCode.J);
            keys.add(KeyCode.U);
        }
    }
    public int getRowIndex() {
        return pane.getRowIndex(node);
    }
    public int getColumnIndex() {
        return pane.getColumnIndex(node);
    }
    public void setKilled() {
        isAlive = false;
    }
    public void setScore(int score) {
        this.score = score;
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
        if (!canMove(dir)) {
            return;
        }
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
        if(game.getMap().hasPower(rowIndex, columnIndex)) {
            new Thread(() -> {
                hasPower = true;
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                hasPower = false;
            }).start();
        }
        setState(dir);
        address = rootAddress + name + state + ".png";
        node = new ImageView(new Image(address));
    }
    private boolean canMove(Direction dir) {
        switch (dir) {
            case UP:
                if (rowIndex == 0 || thereIsImpassableItem(columnIndex, rowIndex - 1, dir)) {
                    return false;
                }
                break;
            case DOWN:
                if (rowIndex == pane.getRowCount() - 1 || thereIsImpassableItem(columnIndex, rowIndex + 1, dir)) {
                    return false;
                }
                break;
            case LEFT:
                if (columnIndex == 0 || thereIsImpassableItem(columnIndex - 1, rowIndex, dir)) {
                    return false;
                }
                break;
            case RIGHT:
                if (columnIndex == pane.getColumnCount() - 1 ||
                        thereIsImpassableItem(columnIndex + 1, rowIndex, dir)) {
                    return false;
                }
                break;
            default:
                break;
        }
        return true;
    }
    private boolean thereIsImpassableItem(int columnIndex, int rowIndex, Direction dir) {
        for (Wall i : walls) {
            if (i.isAlive() && !i.isPassable() && i.getColumnIndex() == columnIndex && i.getRowIndex() == rowIndex) {
                return true;
            }
        }
        for (Block b : blocks) {
            if (b.isAlive() && !b.isPassable() && b.getColumnIndex() == columnIndex && b.getRowIndex() == rowIndex) {
                return true;
            }
        }
        for(OneWay o : oneWays) {
            if(o.getColumnIndex() == columnIndex && o.getRowIndex() == rowIndex && o.getDirection() != dir) {
                return true;
            }
        }
        return false;
    }
    public void setLists(ArrayList<Wall> walls, ArrayList<Block> blocks, ArrayList<OneWay> oneWays,
                         ArrayList<Player> players) {
        this.walls = walls;
        this.blocks = blocks;
        this.oneWays = oneWays;
        this.players = players;
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
    public void handleMove(KeyCode temp) {
        rowIndex = pane.getRowIndex(node);
        columnIndex = pane.getColumnIndex(node);
        pane.getChildren().remove(node);
        if (temp.equals(keys.get(0))) {
            change(Direction.UP);
        } else if (temp.equals(keys.get(1))) {
            change(Direction.RIGHT);
        } else if (temp.equals(keys.get(2))) {
            change(Direction.DOWN);
        } else if (temp.equals(keys.get(3))) {
            change(Direction.LEFT);
        } else if (temp.equals(keys.get(4))) {
            leaveBomb();
        } else {
            System.out.println("Wrong for player " + id);
        }
        pane.add(node, columnIndex, rowIndex);

        Runnable setDownState = () -> {
            state = "down_standing";
            address = rootAddress + name + state + ".png";
            pane.getChildren().remove(node);
            node = new ImageView(new Image(address));
            pane.add(node, columnIndex, rowIndex);
        };

        new Thread(() -> {
            try {
                Thread.sleep(200);
                Platform.runLater(setDownState);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
    private void leaveBomb() {
        if (hasActiveBomb) {
            return;
        }
        Bomb bomb = new Bomb(pane, players, blocks, walls, columnIndex, rowIndex, hasPower ? 5 : 3);
        new Thread(() -> {
            try {
                hasActiveBomb = true;
                Platform.runLater(() -> {
                    bomb.setBomb();
                });
                Thread.sleep(1500);
                Platform.runLater(() -> {
                    bomb.explode();
                });
                Thread.sleep(300);
                Platform.runLater(() -> {
                    bomb.clear();
                });
                Thread.sleep(100);
                hasActiveBomb = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(getMaxScore() == players.size() - 1) {
                game.handleEndOfGame();
            }
        }).start();
    }
    private int getMaxScore() {
        int max = 0;
        for(Player p : players) {
            if(p.getScore() > max) {
                max = p.getScore();
            }
        }
        return max;
    }
    public ArrayList<KeyCode> getKeys() {
        return (ArrayList<KeyCode>) keys;
    }
    public boolean isAlive() {
        return isAlive;
    }
    public int getScore() {
        return score;
    }
    public void setGame(Game g) {
        game = g;
    }
    public Node getNode() {
        return node;
    }
}
