package jfxdays.testing.artshopapp;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.robot.Robot;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;

public class RobotTest extends ApplicationTest {
    @Before
    public void setUp() {

    }
    Scene scene;
    public void start (Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("landing.fxml"));
        Parent root = loader.load();
        stage.setTitle("Art Shop");
        stage.setScene(scene = new Scene(root, 400, 500));
        stage.show();
    }

    @Test
    public void testBlue() {
        clickOn("#tfSearch");
        write("blue");

        Label label = (Label) lookup("#labelCount").query();
        assertEquals(label.getText(), "1");
    }

    Robot robot;
    @Test
    public void testBlueRobot() throws InterruptedException{
            // create robot and move mouse to the text field
            Platform.runLater(() -> {
                robot = new Robot();
                Node tfSearch = scene.lookup("#tfSearch");
                robot.mouseMove(getScreenCoordinates(tfSearch));
                robot.mouseClick(MouseButton.PRIMARY);
            });
            // press keyboard button to type "blue"
            Platform.runLater(() -> {
                robot.keyType(KeyCode.B);
                robot.keyType(KeyCode.L);
                robot.keyType(KeyCode.U);
                robot.keyType(KeyCode.E);
            });

            // allocate time for UI to response
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {}

            // verify the uppercased value in the left text field
            Platform.runLater(() -> {
                Label labelCount = (Label) scene.lookup("#labelCount");
                // pseudo-error reporting
                System.out.println(labelCount.getText().equals("1") ? "PASS" : "FAIL");
            });
    }

    private static Point2D getScreenCoordinates(Node node) {
        return node.localToScreen(node.getTranslateX() + 5, node.getTranslateY() + 5);
    }

    @After
    public void tearDown () throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }
}