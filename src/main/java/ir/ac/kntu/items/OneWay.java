package ir.ac.kntu.items;

import ir.ac.kntu.util.Direction;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class OneWay extends Item{
    private Direction direction;

    public OneWay(GridPane pane, Node node, boolean isPassable, Direction direction) {
        super(pane, node, isPassable, false);
        this.direction = direction;
    }
}
