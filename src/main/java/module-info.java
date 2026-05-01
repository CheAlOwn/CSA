module com.chealown.csa {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jbcrypt;
    requires org.apache.tika.parser.microsoft;
    requires org.apache.tika.core;
    requires org.apache.tika.parser.miscoffice;
    requires org.apache.poi.ooxml;
    requires java.desktop;
    requires odfdom.java;

    opens com.chealown.csa to javafx.fxml;
    exports com.chealown.csa;
    opens com.chealown.csa.Controllers to javafx.fxml;
    exports com.chealown.csa.Controllers;
    opens com.chealown.csa.Controllers.Filters to javafx.fxml;
    exports com.chealown.csa.Controllers.Filters;
    exports com.chealown.csa.Controllers.AddEdit;
    opens com.chealown.csa.Controllers.AddEdit to javafx.fxml;
    exports com.chealown.csa.DataBase.Models;
}