package ir.ac.kntu.items;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlayerTest {
    private GridPane pane = new GridPane();
    private Node node = new Text("Wall");
    private Player obj = new Player(1, pane, node);

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
    public void setKilled() {
        obj.setKilled();
        assertFalse(obj.isAlive());
    }

    @Test
    public void setScore() {
        obj.setScore(12);
        assertEquals(12, obj.getScore());
    }

    @Test
    public void getKeys() {
        ArrayList<KeyCode> keys = new ArrayList<>();
        keys.add(KeyCode.UP);
        assertEquals(keys.get(0), obj.getKeys().get(0));
    }

    @Test
    public void isAlive() {
        assertTrue(obj.isAlive());
    }

    @Test
    public void getScore() {
        assertEquals(0, obj.getScore());
    }

}