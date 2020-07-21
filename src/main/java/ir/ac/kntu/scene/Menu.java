package ir.ac.kntu.scene;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Menu extends Application {
    private Scene scene;
    private BorderPane pane;

    @Override
    public void start(Stage stage){
        pane = new BorderPane();
        Node logo = new ImageView(new Image("assets/menu/logo.gif", 500, 200, false, true));
        pane.setTop(logo);
        BorderPane.setAlignment(logo, Pos.CENTER);
        Node man = new ImageView(new Image("assets/menu/man.gif", 200, 200, false, true));
        pane.setLeft(man);
        Node running = new ImageView(new Image("assets/menu/running.gif", 500, 200, false, true));
        pane.setBottom(running);
        BorderPane.setAlignment(running, Pos.CENTER);

        Button btn = new Button("Play");
        BorderPane.setAlignment(btn, Pos.CENTER);
        pane.setCenter(btn);

        scene = new Scene(pane, Color.BLACK);
        scene.setFill(Color.GREEN);
        stage.setScene(scene);
        stage.show();
    }
}
