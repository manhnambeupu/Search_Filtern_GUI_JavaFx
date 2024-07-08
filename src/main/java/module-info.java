module search_filter.search_filtern_gui {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens search_filter.search_filtern_gui to javafx.fxml;
    exports search_filter.search_filtern_gui;
}