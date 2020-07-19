package ir.ac.kntu.items;

import javafx.application.Platform;
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
    private GridPane pane;
    private int columnIndex;
    private int rowIndex;
    private Node bomb;
    private List<Node> explodings;

    public Bomb(GridPane pane, List<Player> players, List<Block> blocks, List<Wall> walls,
                int columnIndex, int rowIndex) {
        this.players = players;
        this.blocks = blocks;
        this.walls = walls;
        this.pane = pane;
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        explodings = new ArrayList<>();
        bomb = new ImageView(new Image("assets/map/bomb.png"));
    }

    public void setBomb() {
        Platform.runLater(() -> pane.add(bomb, columnIndex, rowIndex));
    }

    public void explode() {
        ArrayList<Block> deadBlocks = new ArrayList<>();
        expLeft(deadBlocks);
        expRight(deadBlocks);
        expUp(deadBlocks);
        expDown(deadBlocks);
        for (Block b : deadBlocks) {
            blocks.remove(b);
        }
    }

    public void expUp(ArrayList<Block> deadBlocks) {
        for (int i = rowIndex; i > 0 && i > rowIndex - 4; i--) {
            Node temp = new ImageView(new Image("assets/map/explosion/exp.png"));
            explodings.add(temp);
            int finalI = i;
            if (validBlock(columnIndex, finalI)) {
                Platform.runLater(() -> pane.add(temp, columnIndex, finalI));
            } else {
                break;
            }
            for (Player p : players) {
                if (p.getRowIndex() == i && p.getColumnIndex() == columnIndex) {
                    p.setKilled();
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

    public void expRight(ArrayList<Block> deadBlocks) {
        for (int i = columnIndex; i < pane.getColumnCount() - 1 && i < columnIndex + 4; i++) {
            Node temp = new ImageView(new Image("assets/map/explosion/exp.png"));
            explodings.add(temp);
            int finalI = i;
            if (validBlock(finalI, rowIndex)) {
                Platform.runLater(() -> pane.add(temp, finalI, rowIndex));
            } else {
                break;
            }
            for (Player p : players) {
                if (p.getRowIndex() == rowIndex && p.getColumnIndex() == i) {
                    p.setKilled();
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

    public void expDown(ArrayList<Block> deadBlocks) {
        for (int i = rowIndex; i < pane.getRowCount() && i < rowIndex + 4; i++) {
            Node temp = new ImageView(new Image("assets/map/explosion/exp.png"));
            explodings.add(temp);
            int finalI = i;
            if (validBlock(columnIndex, finalI)) {
                Platform.runLater(() -> pane.add(temp, columnIndex, finalI));
            } else {
                break;
            }
            for (Player p : players) {
                if (p.getRowIndex() == i && p.getColumnIndex() == columnIndex) {
                    p.setKilled();
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

    public void expLeft(ArrayList<Block> deadBlocks) {
        for (int i = columnIndex; i > 0 && i > columnIndex - 4; i--) {
            Node temp = new ImageView(new Image("assets/map/explosion/exp.png"));
            explodings.add(temp);
            int finalI = i;
            if (validBlock(finalI, rowIndex)) {
                Platform.runLater(() -> pane.add(temp, finalI, rowIndex));
            } else {
                break;
            }
            for (Player p : players) {
                if (p.getRowIndex() == rowIndex && p.getColumnIndex() == i) {
                    p.setKilled();
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
        Platform.runLater(() -> pane.getChildren().remove(bomb));
        for (Node n : explodings) {
            Platform.runLater(() -> pane.getChildren().remove(n));
        }
    }
}
