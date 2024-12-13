module org.example.gestionincidents {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires jdk.jfr;
    requires static lombok;

    opens org.example.gestionincidents to javafx.fxml;
    exports org.example.gestionincidents;
    exports org.example.gestionincidents.Controller;
    opens org.example.gestionincidents.Controller to javafx.fxml;
}