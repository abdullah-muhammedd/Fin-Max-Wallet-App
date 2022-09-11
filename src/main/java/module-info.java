module fm.fm {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;
    requires java.desktop;


    opens fm.fm to javafx.fxml;
    exports fm.fm;
}