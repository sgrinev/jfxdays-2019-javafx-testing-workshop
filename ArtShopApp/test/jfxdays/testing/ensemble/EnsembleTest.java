package jfxdays.testing.ensemble;

import javafx.scene.Node;
import jfxdays.testing.util.ScreenshotsSupport;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;

import java.io.IOException;

public class EnsembleTest extends FxRobot {

    @Before
    public void setup() throws Exception {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(ensemble.EnsembleApp.class);
    }
    @Test
    public void testChart() throws IOException {
        clickOn(".pagination");
        Node chart = lookup(".chart-plot-background").query();
        ScreenshotsSupport.assertSnapshotsNotEqual("chart", chart, 0.01);
    }


}