package search_filter.search_filtern_gui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PrimaryController {

    @FXML
    private TextField searchField;

    @FXML
    private TableView<Eisenbahnunternehmen> dataTableView;

    @FXML
    private TableColumn<Eisenbahnunternehmen, String> columnUnternehmen;

    @FXML
    private TableColumn<Eisenbahnunternehmen, String> columnStrasse;

    @FXML
    private TableColumn<Eisenbahnunternehmen, String> columnPLZ;

    @FXML
    private TableColumn<Eisenbahnunternehmen, String> columnOrt;

    @FXML
    private TableColumn<Eisenbahnunternehmen, String> columnGueterverkehr;

    @FXML
    private TableColumn<Eisenbahnunternehmen, String> columnPersonenverkehr;

    @FXML
    private ChoiceBox<String> searchStrategyChoiceBox;

    @FXML
    private CheckBox filterGueterverkehr;

    @FXML
    private CheckBox filterPersonenverkehr;

    @FXML
    private Button loadButton;

    private EisenbahnunternehmenListModel model;

    @FXML
    private void initialize() {
        model = new EisenbahnunternehmenListModel();

        // Đặt giá trị của các cột
        columnUnternehmen.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnStrasse.setCellValueFactory(new PropertyValueFactory<>("straße"));
        columnPLZ.setCellValueFactory(new PropertyValueFactory<>("postLeitZahl"));
        columnOrt.setCellValueFactory(new PropertyValueFactory<>("stadt"));
        columnGueterverkehr.setCellValueFactory(new PropertyValueFactory<>("gueterVerkehr"));
        columnPersonenverkehr.setCellValueFactory(new PropertyValueFactory<>("personenVerkehr"));

        // Thiết lập các chiến lược tìm kiếm
       // searchStrategyChoiceBox.getItems().addAll("Case Sensitive", "Case Insensitive");
        searchStrategyChoiceBox.setOnAction(event -> updateSearchStrategy());

        // Lắng nghe sự thay đổi trong ô nhập văn bản tìm kiếm
        searchField.textProperty().addListener((observable, oldValue, newValue) -> filterData());

        // Lắng nghe sự thay đổi trong các CheckBox
        filterGueterverkehr.selectedProperty().addListener((observable, oldValue, newValue) -> filterData());
        filterPersonenverkehr.selectedProperty().addListener((observable, oldValue, newValue) -> filterData());
    }

    private void updateSearchStrategy() {
        String selectedStrategy = searchStrategyChoiceBox.getValue();
        if ("Case Sensitive".equals(selectedStrategy)) {
            model.setSearchStrategy(new LowUpSearchStrategy());
        } else if ("Case Insensitive".equals(selectedStrategy)) {
            model.setSearchStrategy(new NonLowUpSearchStrategy());
        }
    }

    private void filterData() {
        String searchText = searchField.getText();
        String gueterVerkehrFilter = filterGueterverkehr.isSelected() ? "Ja" : "";
        String personenVerkehrFilter = filterPersonenverkehr.isSelected() ? "Ja" : "";

        ObservableList<Eisenbahnunternehmen> filteredData = model.filterCompanies(searchText, gueterVerkehrFilter, personenVerkehrFilter);
        dataTableView.setItems(filteredData);
    }

    @FXML
    private void handleLoadButtonAction() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open CSV File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            List<String> lines = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    lines.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            model.loadData(lines);
            dataTableView.setItems(model.getCompanies());
        }
    }
}
