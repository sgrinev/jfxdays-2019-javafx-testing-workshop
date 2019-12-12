package sample;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.*;

public class MainTest extends ApplicationTest {

    static {
        System.setProperty("testfx.robot", "glass");
        System.setProperty("testfx.headless", "true");
//        System.setProperty("prism.order", "sw");
//        System.setProperty("prism.text", "t2k");
//        System.setProperty("java.awt.headless", "true");
    }

    @Override
    public void start (Stage stage) throws Exception {

        stage.setScene(Main.createScene());
        stage.show();
        stage.toFront();
    }

    @After
    public void tearDown() throws TimeoutException {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    @Test
    public void start() throws TimeoutException {
        WaitForAsyncUtils.waitFor(10, TimeUnit.SECONDS, () -> {
            final Optional<Node> opNode = lookup("#btn").tryQuery();
            System.out.print(".");
            return opNode.isPresent() && opNode.get().isVisible();
        });
        clickOn("#btn");
    }
}