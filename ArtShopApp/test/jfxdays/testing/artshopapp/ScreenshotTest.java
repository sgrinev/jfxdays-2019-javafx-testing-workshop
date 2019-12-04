package jfxdays.testing.artshopapp;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jfxdays.testing.util.ScreenshotsSupport;
import org.junit.After;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.ColorMatchers;
import org.testfx.util.WaitForAsyncUtils;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import static org.testfx.util.DebugUtils.*;

public class ScreenshotTest extends ApplicationTest {
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
    public void testBlue() throws IOException {
        clickOn("#tfSearch");
        write("green");

        Label node = lookup("#labelCount").query();

        // TASK: setup golden folder
        // TASK: test images in the table instead
        ScreenshotsSupport.assertSnapshotsEqual("testBlue", node, 0.01);
    }

    @Test
    public void testNotBlue() throws IOException {
        clickOn("#tfSearch");
        write("tort");

        Label node = lookup("#labelCount").query();

        // TASK: setup golden folder
        // TASK: test images in the table instead
        ScreenshotsSupport.assertSnapshotsNotEqual("testBlue", node, 0.01);
    }

    @After
    public void tearDown () throws Exception {
        FxToolkit.cleanupStages();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }
}