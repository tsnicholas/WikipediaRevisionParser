package edu.bsu.cs222.view;

import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ErrorWindow {

    private AnchorPane myAnchorPane;

    public ErrorWindow(String errorReason) {
        Stage stage = (Stage) myAnchorPane.getScene().getWindow();

        Alert error = new Alert(Alert.AlertType.ERROR, "");
        error.initModality(Modality.APPLICATION_MODAL);
        error.initOwner(stage);

        error.getDialogPane().setContentText(errorReason);
        error.getDialogPane().setHeaderText("ERROR");
        error.showAndWait();
    }
}
