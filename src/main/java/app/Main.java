package app;

import infra.db.DatabaseManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        DatabaseManager.getInstance();
        Router.init(stage);
        Router.showPesquisa();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
