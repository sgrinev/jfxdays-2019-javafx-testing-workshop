package jfxdays.testing.util;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import org.junit.Assert;
import org.testfx.util.WaitForAsyncUtils;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.stream.IntStream;

public class ScreenshotsSupport {
    public static void saveScreenshot(WritableImage img, String name) throws IOException {
        ImageIO.write(SwingFXUtils.fromFXImage(img, null), "png", new File(name + ".png"    ));
    }

    public static void saveScreenshot(Node node, String name) throws IOException {
        saveScreenshot(getImage(node), null);
    }

    public static double screenshotDistance(final Image image1, final Image image2) {
        final int width = (int) image1.getWidth();
        final int height = (int) image1.getHeight();
        // TODO: validate image2 size
        final PixelReader reader1 = image1.getPixelReader();
        final PixelReader reader2 = image2.getPixelReader();

        final double sum = IntStream.range(0, width).parallel().
                mapToDouble(i -> {
                    return IntStream.range(0, height).parallel().mapToDouble(j -> {
                        double res = 0;
                        int argb1 = reader1.getArgb(i, j);
                        int argb2 = reader2.getArgb(i, j);
                        if (argb1 == argb2) {
                            return 0;
                        } else {
                            // ignoring opacity for now
                            for (int k = 0; k < 3; k++) {
                                int d = argb1 & 0xFF - argb2 & 0xFF;
                                res += d * d / 65536d;
                                argb1 >>= 8;
                                argb2 >>= 8;
                            }
//                            System.out.println(Math.sqrt(res));
                            return Math.sqrt(res);
                        }
                    }).sum();
                }).sum();

        return sum / (width * height);
    }

    private static WritableImage getImage(Node node) {
        Bounds bounds = node.getBoundsInLocal();
        final WritableImage img = new WritableImage((int) bounds.getWidth(), (int) bounds.getHeight());
        Platform.runLater(() -> node.snapshot(null, img));
        WaitForAsyncUtils.waitForFxEvents();
        return img;
    }

    public static void assertSnapshotsEqual(final String name, final Node nodeUnderTest, final double threshold) throws IOException {
        final WritableImage image = getImage(nodeUnderTest);
        final Image golden = new Image("file:golden/" + name + ".png");
        if (golden.isError()) {
            saveScreenshot(image, name);
            Assert.fail( "Golden image doesn't exist for " + name);
        }
        double dist = screenshotDistance(golden, getImage(nodeUnderTest));
        System.out.printf("Distance: " + dist);
        if (dist > threshold) {
            saveScreenshot(image, name);
            Assert.fail( "The two snapshots differ with distance " + dist);
        }
    }

    public static void assertSnapshotsNotEqual(final String name, final Node nodeUnderTest, final double threshold) throws IOException {
        final WritableImage image = getImage(nodeUnderTest);
        final Image golden = new Image("file:golden/" + name + ".png");
        if (golden.isError()) {
            saveScreenshot(image, name);
            Assert.fail( "Golden image doesn't exist for " + name);
        }
        double dist = screenshotDistance(golden, getImage(nodeUnderTest));
        System.out.printf("Distance: " + dist);
        if (dist <= threshold) {
            saveScreenshot(image, name);
            Assert.fail( "The two snapshots differ too much. Distance: " + dist);
        }
    }
}
