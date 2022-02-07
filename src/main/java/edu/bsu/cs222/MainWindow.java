package edu.bsu.cs222;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow extends Application {

    private final TextField textField = new TextField();
    private final Button button = new Button("Search");
    private final Label label = new Label();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(createUI()));
        primaryStage.show();
    }

    // TODO: Add error message when there's no wikipedia page with the name
    private Parent createUI() {
        button.setOnAction((event) -> {
            String wikipediaName = textField.getText();
            label.setText(wikipediaName);
        });

        VBox vbox = new VBox();
        vbox.getChildren().addAll(
                textField,
                button,
                label
        );

        return vbox;
    }
}
