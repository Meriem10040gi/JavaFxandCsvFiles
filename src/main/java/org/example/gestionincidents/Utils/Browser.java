package org.example.gestionincidents.Utils;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Browser {
    public String Find(String ext){
        FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choisir une image");
            fileChooser.getExtensionFilters().addAll(
                new javafx.stage.FileChooser.ExtensionFilter("Files", "*."+ext)
        );
    File selectedFile = fileChooser.showOpenDialog(new Stage());
    return selectedFile.getAbsolutePath();
    }
}
