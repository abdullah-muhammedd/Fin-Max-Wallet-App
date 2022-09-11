package fm.fm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    //Stage made static so I can achieve it from  any other class or button
    public static Stage stage;
    //Main data class is made to store all major data of the user and all major functionality
    public static MainData MAINDATA = new MainData();

    public static void main(String[] args) {
        launch();
    }

    //Methods build pages are responsible for changing the scenes
    public static Scene buildLogInPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LogInPage.fxml"));
        stage.setOnCloseRequest(null);
        return new Scene(fxmlLoader.load(), 600, 400);

    }

    public static Scene buildMainPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainPage.fxml"));
        stage.setOnCloseRequest(e -> MainConnection.dropViews());
        return new Scene(fxmlLoader.load(), 1000, 600);

    }

    public static Scene buildWithdrawPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("WithdrawPage.fxml"));
        stage.setOnCloseRequest(e -> MainConnection.dropViews());
        return new Scene(fxmlLoader.load(), 1000, 600);
    }

    public static Scene buildDepositPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("DepositPage.fxml"));
        stage.setOnCloseRequest(e -> MainConnection.dropViews());
        return new Scene(fxmlLoader.load(), 1000, 600);
    }

    public static Scene buildAboutPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AboutPage.fxml"));
        stage.setOnCloseRequest(e -> MainConnection.dropViews());
        return new Scene(fxmlLoader.load(), 1000, 600);
    }

    //main start method (Application driver)
    @Override
    public void start(Stage st) throws IOException {
        stage = st;
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("Asset 8.png"))));
        stage.setTitle("Log In");
        stage.setResizable(false);
        stage.setScene(buildLogInPage());
        stage.show();
    }
}