package jfxdays.testing.artshopapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import java.util.Arrays;
import java.util.Collection;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

@RunWith(Parameterized.class)
public class TemplateTest extends ApplicationTest {

    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("landing.fxml"));
        Parent root = loader.load();
        stage.setTitle("Art Shop");
        stage.setScene(new Scene(root, 400, 500));
        stage.show();
    }

    // prepare fields


    // prepare constructor

    @Parameterized.Parameters
    public static Collection values() {
        return Arrays.asList(new Object[][]{
                {"blue", "1"},
                {"green", "4"},
                {"tortoise", "2"}
        });
    }

    @Test
    public void testFilter() {
        // TASK: filter test which verifies count using fields
    }

    @After
    public void tearDown() throws Exception {
        FxToolkit.cleanupStages();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }
}