package ir.ac.kntu.items;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class Item {
    private GridPane pane;
    private Node node;
    private boolean isAlive;
    private boolean isPassable;


    public Item() {

    }

    public Item(GridPane pane, Node node, boolean isPassable) {
        this.pane = pane;
        this.node = node;
        this.isAlive = true;
        this.isPassable = isPassable;

    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean isPassable() {
        return isPassable;
    }

    public void setPassable(boolean passable) {
        isPassable = passable;
    }
    public GridPane getPane() {
        return pane;
    }

    public void setPane(GridPane pane) {
        this.pane = pane;
    }
}
