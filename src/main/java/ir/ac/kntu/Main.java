package ir.ac.kntu;

import ir.ac.kntu.scene.GameMap;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application implements Runnable {

    public static void main(String[] args) {
//        Game game = new Game();
//        game.run();

        launch(args);

    }

    public void start(Stage stage) throws Exception {
//        Pane root = new Pane();
//        root.setStyle("-fx-border-width: 0 0 5 0;");
//        Pane pane = new Pane();
//        Scene scene = new Scene(pane, 300, 300, Color.WHITE);
//        ArrayList<Player> players = new ArrayList<>();
//        Player one = new Player(1, pane, scene);
//        Player two = new Player(2, pane, scene);
//        Player three = new Player(3, pane, scene);
//        Player four = new Player(4, pane, scene);
//        players.add(one);
//        players.add(two);
//        players.add(three);
//        players.add(four);
//
//        scene.setOnKeyPressed(keyEvent -> {
//            KeyCode temp = keyEvent.getCode();
//            for(Player p : players) {
//                if(p.getKeys().contains(temp)) {
//                    p.handleMove(temp);
//                    break;
//                }
//            }
//        });
        GameMap gm = new GameMap();

        stage.setScene(gm.getScene());
        //stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Fariborz Bobmerman");
        stage.show();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
