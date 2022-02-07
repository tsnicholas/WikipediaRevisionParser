package edu.bsu.cs222.view;

import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ErrorWindow {

    private final Stage errorStage;
    private final Alert error;

    public ErrorWindow(String errorReason) {
        errorStage = new Stage();
        error = new Alert(Alert.AlertType.ERROR, "");
        error.initModality(Modality.APPLICATION_MODAL);

        error.getDialogPane().setContentText(errorReason);
        error.getDialogPane().setHeaderText("ERROR");
        error.showAndWait();
    }
}
