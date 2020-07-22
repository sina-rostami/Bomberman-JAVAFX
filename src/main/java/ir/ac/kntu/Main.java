package ir.ac.kntu;

import ir.ac.kntu.scene.Game;
import ir.ac.kntu.scene.Menu;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {
    private Game game;
    private Menu menu;
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        play(stage);
    }

    private void play(Stage stage) {
        Platform.runLater(() -> {
            menu = new Menu();
            menu.start(stage);
            menu.getPlayButton().setOnMouseClicked(mouseEvent -> {
                if(menu.getChoiceBox().getValue() != null) {
                    gamePlay(stage, menu.getChoiceBox().getValue());
                    menu.changeMusic();
                }
            });
        });
    }

    public void gamePlay(Stage stage, int cnt) {
        Platform.runLater(() -> {
            game = new Game(cnt);
            game.start(stage);
        });
        new Thread(() -> {
            try {
                Thread.sleep(1000 * 50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(!game.isDone()) {
                Platform.runLater(() -> {
                    game.getPane().addRow(game.getPane().getRowCount() - 1, new Text("TimeUp"));
                });
                game.handleEndOfGame();
            }
        }).start();
    }

}
