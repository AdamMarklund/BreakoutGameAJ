module com.example.myprogramming2project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.myprogramming2project to javafx.fxml;
    exports com.example.myprogramming2project;
}