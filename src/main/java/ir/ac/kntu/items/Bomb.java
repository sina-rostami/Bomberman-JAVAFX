package ir.ac.kntu.items;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class Bomb {
    private List<Player> players;
    private List<Block> blocks;
    private List<Wall> walls;
    private ArrayList<Block> deadBlocks;
    private ArrayList<Player> deadPlayers;
    private GridPane pane;
    private int columnIndex;
    private int rowIndex;
    private Node bomb;
    private List<Node> explodings;
    private int score;
    private int radius;

    public Bomb(GridPane pane, List<Player> players, List<Block> blocks, List<Wall> walls,
                int columnIndex, int rowIndex, int radius) {
        this.players = players;
        this.blocks = blocks;
        this.walls = walls;
        this.pane = pane;
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.radius = radius;
        score = 0;
        explodings = new ArrayList<>();
        deadPlayers = new ArrayList<>();
        deadBlocks = new ArrayList<>();
        bomb = new ImageView(new Image("assets/map/bomb.png"));

    }

    public void setBomb() {
        pane.add(bomb, columnIndex, rowIndex);
    }

    public int getScore() {
        return score;
    }

    private void setScore(int sc) {
        score = sc;
    }

    public void explode() {
        expLeft();
        expRight();
        expUp();
        expDown();
        setScore(deadPlayers.size());
        for (Block b : deadBlocks) {
            blocks.remove(b);
        }
    }

    public void expUp() {
        for (int i = rowIndex; i > 0 && i > rowIndex - (radius + 1); i--) {
            Node temp = new ImageView(new Image("assets/map/explosion/exp.png"));
            int finalI = i;
            if (validBlock(columnIndex, finalI)) {
                pane.add(temp, columnIndex, finalI);
                explodings.add(temp);
            } else {
                break;
            }
            for (Player p : players) {
                if (p.isAlive() && p.getRowIndex() == i && p.getColumnIndex() == columnIndex) {
                    p.setKilled();
                    deadPlayers.add(p);
                }
            }
            boolean stop = false;
            for (Block b : blocks) {
                if (b.getRowIndex() == i && b.getColumnIndex() == columnIndex) {
                    b.destroy();
                    deadBlocks.add(b);
                    stop = true;
                }
            }
            if (stop) {
                break;
            }
        }
    }

    public void expRight() {
        for (int i = columnIndex + 1; i < pane.getColumnCount() - 1 && i < columnIndex + (radius + 1); i++) {
            Node temp = new ImageView(new Image("assets/map/explosion/exp.png"));
            int finalI = i;
            if (validBlock(finalI, rowIndex)) {
                pane.add(temp, finalI, rowIndex);
                explodings.add(temp);
            } else {
                break;
            }
            for (Player p : players) {
                if (p.isAlive() && p.getRowIndex() == rowIndex && p.getColumnIndex() == i) {
                    p.setKilled();
                    deadPlayers.add(p);
                }
            }
            boolean stop = false;
            for (Block b : blocks) {
                if (b.getRowIndex() == rowIndex && b.getColumnIndex() == i) {
                    b.destroy();
                    deadBlocks.add(b);
                    stop = true;
                }
            }
            if (stop) {
                break;
            }
        }
    }

    public void expDown() {
        for (int i = rowIndex + 1; i < pane.getRowCount() && i < rowIndex + (radius + 1); i++) {
            Node temp = new ImageView(new Image("assets/map/explosion/exp.png"));
            int finalI = i;
            if (validBlock(columnIndex, finalI)) {
                pane.add(temp, columnIndex, finalI);
                explodings.add(temp);
            } else {
                break;
            }
            for (Player p : players) {
                if (p.isAlive() && p.getRowIndex() == i && p.getColumnIndex() == columnIndex) {
                    p.setKilled();
                    deadPlayers.add(p);
                }
            }
            boolean stop = false;
            for (Block b : blocks) {
                if (b.getRowIndex() == i && b.getColumnIndex() == columnIndex) {
                    b.destroy();
                    deadBlocks.add(b);
                    stop = true;
                }
            }
            if (stop) {
                break;
            }
        }
    }

    public void expLeft() {
        for (int i = columnIndex - 1; i > 0 && i > columnIndex - (radius + 1); i--) {
            Node temp = new ImageView(new Image("assets/map/explosion/exp.png"));
            int finalI = i;
            if (validBlock(finalI, rowIndex)) {
                pane.add(temp, finalI, rowIndex);
                explodings.add(temp);
            } else {
                break;
            }
            for (Player p : players) {
                if (p.isAlive() && p.getRowIndex() == rowIndex && p.getColumnIndex() == i) {
                    p.setKilled();
                    deadPlayers.add(p);
                }
            }
            boolean stop = false;
            for (Block b : blocks) {
                if (b.getRowIndex() == rowIndex && b.getColumnIndex() == i) {
                    b.destroy();
                    deadBlocks.add(b);
                    stop = true;
                }
            }
            if (stop) {
                break;
            }
        }
    }

    private boolean validBlock(int columnIndex, int rowIndex) {
        for (Wall w : walls) {
            if (w.getColumnIndex() == columnIndex && w.getRowIndex() == rowIndex) {
                return false;
            }
        }
        return true;
    }

    public void clear() {
        pane.getChildren().remove(bomb);
        for (Node n : explodings) {
            pane.getChildren().remove(n);
        }
        for(Player p : deadPlayers) {
            players.remove(p);
        }
    }
}
