package jfxdays.testing.artshopapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
import org.testfx.util.WaitForAsyncUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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



    public TemplateTableTest(String name, String info) {
        this.name = name;
        this.info = info;
    }

    @Parameterized.Parameters
    public static Collection values() {
        return Arrays.asList(new Object[][]{
                {"green flower", "72"},
                {"green forest", "72"},
                {"beige leaf", "38"}
        //    TASK: if you are done fast
        //    uncomment next line to solve a tricky one
        //    ,{"green tortoise", "6"}
        });
    }

    @Test
    public void testNameAndInfo() throws TimeoutException {
        clickOn("#tfSearch").write(name);

        Node table = lookup(".sheet").query();

        WaitForAsyncUtils.waitFor(10, TimeUnit.SECONDS, () -> {
            System.out.print(".");
            Optional<Node> nodeText
                    = from(table).lookup(".table-cell").nth(1).lookup(name).tryQuery();
            Optional<Node> nodeInfo =
                    from(table).lookup(".table-cell").nth(2).lookup(info).tryQuery();

            return nodeText.isPresent() && nodeInfo.isPresent();
        });
//        verifyThat( from(table).lookup(".table-cell").nth(1) , hasText(name));
//        verifyThat( from(table).lookup(".table-cell").nth(2) , hasText(info));
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