package ir.ac.kntu.items;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import org.junit.Test;

import static org.junit.Assert.*;

public class BlockTest {
    private GridPane pane = new GridPane();
    private Node node = new Text("Block");
    private Block obj = new Block(pane, node, false);

    @Test
    public void getRowIndexTest() {
        pane.add(node, 0, 0);
        assertEquals(0, obj.getRowIndex());
    }
    @Test
    public void getColIndexTest() {
        pane.add(node, 0, 0);
        assertEquals(0, obj.getColumnIndex());
    }

    @Test
    public void getNodeTest() {
        assertEquals(node, obj.getNode());
    }

    @Test
    public void isAliveTest() {
        assertTrue(obj.isAlive());
    }
    @Test
    public void setAliveTest() {
        obj.setAlive(false);
        assertFalse(obj.isAlive());
    }

    @Test
    public void setNodeTest() {
        node = new Text("BlockT");
        obj.setNode(node);
        assertEquals(node, obj.getNode());
    }
    @Test
    public void isPassableTest() {
        assertEquals(false, obj.isPassable());
    }
    @Test
    public void getPaneTest() {
        assertEquals(pane, obj.getPane());
    }
    @Test
    public void setPaneTest() {
        pane = new GridPane();
        obj.setPane(pane);
        assertEquals(pane, obj.getPane());
    }

}