module com.chealown.csa {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.chealown.csa to javafx.fxml;
    exports com.chealown.csa;
    opens com.chealown.csa.Controllers to javafx.fxml;
    exports com.chealown.csa.Controllers;
}