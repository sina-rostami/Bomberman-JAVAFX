package ir.ac.kntu.scene;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.File;


public class Menu extends Application {
    private Scene scene;
    private BorderPane pane;
    private ChoiceBox<Integer> choiceBox;
    private Button btn;
    private MediaPlayer mediaPlayer;
    @Override
    public void start(Stage stage){
        pane = new BorderPane();
        scene = new Scene(pane, Color.GREEN);
        stage.setScene(scene);
        Node logo = new ImageView(new Image("assets/menu/logo.gif", 500, 200, false, true));
        pane.setTop(logo);
        BorderPane.setAlignment(logo, Pos.CENTER);
        Node man = new ImageView(new Image("assets/menu/man.gif", 200, 200, false, true));
        pane.setLeft(man);
        Node running = new ImageView(new Image("assets/menu/running.gif", 500, 200, false, true));
        pane.setBottom(running);
        BorderPane.setAlignment(running, Pos.CENTER);
        btn = new Button("Play");
        choiceBox = new ChoiceBox<>(FXCollections.observableArrayList(2, 3, 4));
        Text text =  new Text("Players :");
        text.setFill(Color.WHITE);
        HBox hBox = new HBox();
        hBox.getChildren().add(text);
        hBox.getChildren().add(choiceBox);
        hBox.getChildren().add(btn);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(5);
        Node moon = new ImageView(new Image("assets/menu/moon.gif", 300, 200, false, true));
        GridPane gridPane = new GridPane();
        gridPane.add(moon, 0, 0);
        gridPane.add(hBox, 0, 0);
        GridPane.setHalignment(hBox, HPos.CENTER);
        pane.setRight(gridPane);
        File f = new File("src/main/resources/music/o.wav");
        String path = f.toURI().toString();
        Media media = new Media(path);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        stage.show();
    }

    public void changeMusic() {
        mediaPlayer.stop();
        mediaPlayer = new MediaPlayer(new Media(new File("src/main/resources/music/game.wav").toURI().toString()));
        mediaPlayer.play();
    }

    public ChoiceBox<Integer> getChoiceBox() {
        return choiceBox;
    }

    public Button getPlayButton() {
        return btn;
    }

}
