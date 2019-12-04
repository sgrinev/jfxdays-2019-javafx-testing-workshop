package jfxdays.testing.artshopapp;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.function.Predicate;

public class LandingPageController {

    public static final int PREVIEW_SIZE = 100;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private TableView<ImageData> imagesTable;
    @FXML
    private TextField tfSearch;
    @FXML
    private Label labelCount;

    //    private final Map<String, ImageView> images = new HashMap<>();
    ObservableList<ImageData> images = FXCollections.observableArrayList();

    // Images are courtesy of @selvaminiatures
    public static final String[] filenames = {"beige_leaf.jpg",
            "blue_star.jpg",
            "green_flower.jpg",
            "green_forest.jpg",
            "green_tortoise.jpg",
            "purple_flowers.jpg",
            "saladgreen_tortoise.jpg",
            "turquoise_octopus.jpg"
    };

    @FXML
    public void initialize() {
        // Loading images
        for (String filename : filenames) {
            String name = filename.toLowerCase().replace("_", " ");
            name = name.substring(0, name.lastIndexOf("."));
            images.add(new ImageData(filename, name,
                    "" + filename.length()*117%100)); // "random" number
        }

        FilteredList<ImageData> filteredData = new FilteredList<>(images);
        filteredData.predicateProperty().bind(Bindings.createObjectBinding(() -> (Predicate<ImageData>) imageData -> {
            String st = tfSearch.getText();
            return imageData.getName().contains(st) || imageData.getInfo().contains(st);
        }, tfSearch.textProperty()));

        TableColumn<ImageData,String> columnPreview = (TableColumn<ImageData,String>) imagesTable.getColumns().get(0);
        columnPreview.setCellFactory(param -> new TableCell<>() {
            public void updateItem(String image, boolean empty) {
                super.updateItem(image, empty);

                new Thread(()-> {

                }).start();
                final ImageView imageview = new ImageView();
                imageview.setFitHeight(PREVIEW_SIZE);
                imageview.setFitWidth(PREVIEW_SIZE);
                if (image != null) {
                    // System.out.println(image); // debug-info
                    imageview.setImage(new Image(getClass().getResourceAsStream("images/" + image)));
                    setGraphic(imageview);
                } else {
                    setGraphic(null);
                }
            }
        });
        columnPreview.setCellValueFactory(cellData -> cellData.getValue().filenameProperty());
        imagesTable.setItems(filteredData);

        labelCount.textProperty().bind(new SimpleListProperty<>(filteredData).sizeProperty().asString());

        tfSearch.styleProperty().bind(
                Bindings.when(labelCount.textProperty().isEqualTo("0"))
                        .then("-fx-text-inner-color: red;")
                        .otherwise("-fx-text-inner-color: black;"));


        // The lengths we need to go to avoid empty rows in a TableView...
        NumberBinding tableHeightBinding =
                Bindings.max(new SimpleListProperty<>(filteredData).sizeProperty(), 1)
                    .multiply(PREVIEW_SIZE+1)
                    .add(49); // header + padding, calculating those is page long, so... no

        imagesTable.minHeightProperty().bind(tableHeightBinding);
        imagesTable.prefHeightProperty().bind(tableHeightBinding);
        imagesTable.maxHeightProperty().bind(tableHeightBinding);
    }

    private void ensureVisible(Node node) {
        double h = scrollPane.getContent().getBoundsInLocal().getHeight();
        double y = (node.getBoundsInParent().getMaxY() +
                node.getBoundsInParent().getMinY()) / 2.0;
        double v = scrollPane.getViewportBounds().getHeight();
        scrollPane.setVvalue(scrollPane.getVmax() * ((y - 0.5 * v) / (h - v)));
    }

    @FXML
    private void doReset(ActionEvent actionEvent) {
        tfSearch.clear();
        tfSearch.requestFocus();
    }
}
