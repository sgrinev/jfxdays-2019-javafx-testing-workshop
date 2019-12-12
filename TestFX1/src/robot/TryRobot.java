package robot;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.robot.Robot;
import javafx.stage.Stage;

public class TryRobot extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Hello Robot");
        Button btn = new Button("Hello");
        btn.setOnAction((event -> {
            System.out.println("click");
        }));
        StackPane root = new StackPane(btn);
        btn.setId("btn");
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();

        Robot robot = new Robot();
        Point2D point2D = btn.localToScreen(btn.getTranslateX() + 5, btn.getTranslateY() + 5);
        System.out.println(point2D);

        Platform.runLater(() -> {
                    robot.mouseMove(point2D);
                    robot.mouseClick(MouseButton.PRIMARY);
                }

        );
    }
}
