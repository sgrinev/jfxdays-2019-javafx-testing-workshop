package jfxdays.testing.artshopapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.ColorMatchers;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.control.TextMatchers;

import java.util.Optional;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import static org.testfx.util.DebugUtils.*;



public class LandingTest extends ApplicationTest {
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
    public void testBlueHasOnlyOneEntry() {
        clickOn("#tfSearch").write("blue");
        verifyThat("#labelCount", hasText("1"));

    }

    @Test
    public void testGreen() {
        // TASK: use text "green" and check count, but don't use #tfSearch and #labelCount
    }

    @Test
    public void testReset() {
        // TASK: test reset button functionality
    }

    @Test
    public void testRowsCount() {
        verifyThat(lookup(".sheet"),
                NodeMatchers.hasChildren(8, ".table-row-cell"));
    }

    @Test
    public void betterTableViewValidation() {
        clickOn("#tfSearch").write("blue");
        lookup(".table-row-cell").queryAll().forEach(node -> {
            Optional<Node> blue_star = from(node).lookup("blue star").tryQuery();

            if (blue_star.isEmpty())
                Assert.assertEquals(
                        from(node).
                                lookup(".table-cell").nth(1).lookup(".text").queryText().getText(), "");
        });
    }

    @Test
    public void testTableContent() {
        verifyThat(lookup(".table-row-cell").nth(2).lookup(".table-cell").nth(1), hasText("green flower"));
    }

    @Test
    public void testTableWithOneRow() {

    }

    @Test
    public void testRedText() {
        clickOn("#tfSearch").write("green2");
        Assert.assertEquals((Color)lookup("#tfSearch").lookup(".text").queryText().getFill(),Color.RED);
        // TASK: test text became red if everything is filtered out (use ScenicView) and
        // ColorMatchers.isColor(Color.RED)
    }

    @Test
    public void testBlackText() {
        clickOn("#tfSearch").write("green");
        Assert.assertNotEquals((Color)lookup("#tfSearch").lookup(".text").queryText().getFill(),Color.RED);
        // TASK: test text became red if everything is filtered out (use ScenicView) and
        // ColorMatchers.isColor(Color.RED)
    }

    @After
    public void tearDown () throws Exception {
        FxToolkit.cleanupStages();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }
}