package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.CryptService;
import service.impl.CryptServiceImpl;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        CryptService cryptService = new CryptServiceImpl();
        System.out.println(cryptService.decryptColumn("zczczczddawdawdawdadaw", "werwerwerwer"));

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sample.fxml")));
        primaryStage.setTitle("lab01_TI_051007");
        primaryStage.setScene(new Scene(root, 887, 656));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
