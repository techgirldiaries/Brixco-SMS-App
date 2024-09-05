module com.mycompany.app4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens com.mycompany.app4 to javafx.fxml;
    exports com.mycompany.app4;
}
