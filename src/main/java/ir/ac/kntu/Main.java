package ir.ac.kntu;
import ir.ac.kntu.scene.Game;
import ir.ac.kntu.scene.GameMap;
import ir.ac.kntu.scene.Menu;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class Main extends Application {
    private Game game;
    private Menu menu;
    private Stage stage;

    public Main() {
        menu = new Menu();
    }
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        play(stage);
    }

    private void play(Stage stage) {
        this.stage = stage;
        Platform.runLater(() -> {
            menu.start(stage);
            menu.getPlayButton().setOnMouseClicked(mouseEvent -> {
                if (menu.getChoiceBox().getValue() != null) {
                    gamePlay(stage, menu.getChoiceBox().getValue());
                    menu.changeMusic();
                }
            });
        });
    }

    public void gamePlay(Stage stage, int cnt) {
        Runnable checkEnd = () -> {
            try {
                Thread.sleep(900);
                while (true) {
                    Thread.sleep(160);
                    if (game.isDone()) {
                        gameDone();
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Platform.runLater(() -> {
            game = new Game(cnt, new GameMap(cnt));
            game.start(stage);
            new Thread(checkEnd).start();
        });

    }

    private void gameDone() {
        Button quitBtn = new Button("Quit");
        Button resBtn = new Button("Restart");
        Platform.runLater(() -> {
            game.getPane().addRow(0, resBtn);
            game.getPane().addRow(1, quitBtn);
            resBtn.setOnMouseClicked(mouseEvent -> {
                stage.close();
                play(new Stage());
            });
            quitBtn.setOnMouseClicked(mouseEvent -> {
                System.exit(0);
            });
        });
    }

}
