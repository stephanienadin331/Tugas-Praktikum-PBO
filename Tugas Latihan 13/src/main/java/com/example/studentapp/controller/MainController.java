package com.example.studentapp.controller;

import com.example.studentapp.factory.StudentFactory;
import com.example.studentapp.model.*;
import com.example.studentapp.repository.InMemoryStudentRepository;
import com.example.studentapp.service.StudentService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MainController {
    @FXML private TextField npmField;
    @FXML private TextField nameField;
    @FXML private TextField gpaField;
    @FXML private ComboBox<String> typeCombo;
    @FXML private TextField extraField;

    @FXML private TableView<Student> tableView;
    @FXML private TableColumn<Student, String> npmCol;
    @FXML private TableColumn<Student, String> nameCol;
    @FXML private TableColumn<Student, String> gpaCol;
    @FXML private TableColumn<Student, String> extraCol;

    @FXML private TextField tuitionField;
    @FXML private Label infoLabel;

    private final StudentService service;
    private final ObservableList<Student> data = FXCollections.observableArrayList();

    public MainController() {
        this.service = new StudentService(new InMemoryStudentRepository());
    }

    @FXML
    public void initialize() {
        typeCombo.setItems(FXCollections.observableArrayList("REGULAR", "EXCHANGE", "SCHOLARSHIP"));
        typeCombo.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
            if ("EXCHANGE".equals(newV)) {
                extraField.setPromptText("homeUniversity");
            } else if ("SCHOLARSHIP".equals(newV)) {
                extraField.setPromptText("scholarship percentage (e.g. 25.0)");
            } else {
                extraField.setPromptText("");
            }
        });

        npmCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getNpm()));
        nameCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getName()));
        gpaCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(String.valueOf(cell.getValue().getGpa())));
        extraCol.setCellValueFactory(cell -> {
            Student s = cell.getValue();
            if (s instanceof ExchangeStudent) return new javafx.beans.property.SimpleStringProperty(((ExchangeStudent)s).getHomeUniversity());
            if (s instanceof ScholarshipStudent) return new javafx.beans.property.SimpleStringProperty(((ScholarshipStudent)s).getScholarshipPercentage() + "%");
            return new javafx.beans.property.SimpleStringProperty("");
        });

        tableView.setItems(data);
    }

    @FXML
    private void onAddClicked(ActionEvent evt) {
        try {
            String npm = npmField.getText().trim();
            String name = nameField.getText().trim();
            double gpa = Double.parseDouble(gpaField.getText().trim());
            String typeStr = typeCombo.getValue();
            if (typeStr == null) throw new IllegalArgumentException("Pilih type");

            StudentFactory.Type type = StudentFactory.Type.valueOf(typeStr);
            String extra = extraField.getText().trim().isEmpty() ? null : extraField.getText().trim();

            Student s = service.createStudent(npm, name, gpa, type, extra);
            data.add(s);
            infoLabel.setText("Added: " + s.getNpm());
            npmField.clear(); nameField.clear(); gpaField.clear(); extraField.clear(); typeCombo.getSelectionModel().clearSelection();
        } catch (Exception ex) {
            infoLabel.setText("Error: " + ex.getMessage());
        }
    }

    @FXML
    private void onShowTotalScholarship(ActionEvent evt) {
        try {
            double tuition = Double.parseDouble(tuitionField.getText().trim());
            double totalNominal = service.totalScholarshipForTuition(tuition);
            infoLabel.setText(String.format("Total scholarship nominal: %.2f", totalNominal));
        } catch (Exception ex) {
            infoLabel.setText("Error: " + ex.getMessage());
        }
    }
}