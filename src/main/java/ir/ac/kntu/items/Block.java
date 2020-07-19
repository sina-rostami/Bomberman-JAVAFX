package ir.ac.kntu.items;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class Block extends Item{

    public Block(GridPane pane, Node node, boolean isPassable) {
        super(pane, node, isPassable, true);
    }
}
