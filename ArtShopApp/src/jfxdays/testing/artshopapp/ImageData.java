package jfxdays.testing.artshopapp;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ImageData {
    private StringProperty filename;
    private StringProperty name;
    private StringProperty info;

    public ImageData(String filename, String name, String info) {
        filenameProperty().set(filename);
        nameProperty().set(name);
        infoProperty().set(info);
    }

    public void setFilename(String value) {
        filenameProperty().set(value);
    }

    public String getFilename() {
        return filenameProperty().get();
    }

    public StringProperty filenameProperty() {
        if (filename == null) filename = new SimpleStringProperty(this, "filename");
        return filename;
    }

    public void setName(String value) {
        nameProperty().set(value);
    }

    public String getName() {
        return nameProperty().get();
    }

    public StringProperty nameProperty() {
        if (name == null) name = new SimpleStringProperty(this, "name");
        return name;
    }

    public void setInfo(String value) {
        infoProperty().set(value);
    }

    public String getInfo() {
        return infoProperty().get();
    }

    public StringProperty infoProperty() {
        if (info == null) info = new SimpleStringProperty(this, "info");
        return info;
    }
}
