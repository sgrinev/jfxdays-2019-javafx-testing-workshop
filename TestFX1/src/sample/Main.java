package sample;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    public static Scene createScene() {
        StackPane root = new StackPane();


        PauseTransition pt = new PauseTransition(Duration.seconds(2));
        pt.setOnFinished((event -> {
            Button btn = new Button("Hello");
            btn.setId("btn");
            root.getChildren().add(btn);
        }));
        pt.play();
        return new Scene(root, 300, 275);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(createScene());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
