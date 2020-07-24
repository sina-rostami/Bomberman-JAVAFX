package ir.ac.kntu.items;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import org.junit.Test;

import static org.junit.Assert.*;

public class WallTest {

    private GridPane pane = new GridPane();
    private Node node = new Text("Wall");
    private Wall obj = new Wall(pane, node, true);

    @Test
    public void getRowIndex() {
        pane.add(node, 0, 0);
        assertEquals(0, obj.getRowIndex());
    }

    @Test
    public void getColumnIndex() {
        pane.add(node, 0, 0);
        assertEquals(0, obj.getColumnIndex());
    }

    @Test
    public void getNode() {
        assertEquals(node, obj.getNode());
    }

    @Test
    public void setNode() {
        node = new Text("BlockT");
        obj.setNode(node);
        assertEquals(node, obj.getNode());
    }

    @Test
    public void isAlive() {
        assertTrue(obj.isAlive());
    }

    @Test
    public void setAlive() {
        obj.setAlive(false);
        assertFalse(obj.isAlive());
    }

    @Test
    public void isPassable() {
        assertEquals(true, obj.isPassable());
    }

    @Test
    public void setPassable() {
        obj.setPassable(false);
        assertEquals(false, obj.isPassable());
    }

    @Test
    public void getPane() {
        assertEquals(pane, obj.getPane());
    }

    @Test
    public void setPane() {
        pane = new GridPane();
        obj.setPane(pane);
        assertEquals(pane, obj.getPane());
    }

}