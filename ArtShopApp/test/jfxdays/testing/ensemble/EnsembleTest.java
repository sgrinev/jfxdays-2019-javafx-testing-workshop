package jfxdays.testing.ensemble;

import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;

public class EnsembleTest extends FxRobot {

    @Before
    public void setup() throws Exception {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(ensemble.EnsembleApp.class);
    }
    @Test
    public void testSearchBox() {
        clickOn(".search-box");
        write("chart");
    }


}