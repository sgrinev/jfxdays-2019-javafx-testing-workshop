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
public class TemplateTableTest extends ApplicationTest {

    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("landing.fxml"));
        Parent root = loader.load();
        stage.setTitle("Art Shop");
        stage.setScene(new Scene(root, 400, 500));
        stage.show();
    }

    public String name;
    public String info;

    

    public TemplateTableTest(String name, String info)

    @Parameterized.Parameters
    public static Collection values() {
        return Arrays.asList(new Object[][]{
                {0, "green flower", "72"},
                {1, "green forest", "72"},
                {2, "beige leaf", "38"}
        //    TASK: if you are done fast
        //    uncomment next line to solve a tricky one
        //    ,{2, "green tortoise", "6"}
        });
    }

    @Test
    public void testNameAndInfo() {
        // TASK: update class to became proper parametrized test
        // verify that table has art name and info according to the array
    }

    @After
    public void tearDown() throws Exception {
        FxToolkit.cleanupStages();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }
}