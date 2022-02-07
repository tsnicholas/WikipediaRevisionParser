package edu.bsu.cs222.view;

import edu.bsu.cs222.model.wikiPage;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.MalformedURLException;

public class MainWindow extends Application {

    private final TextField textField = new TextField();
    private final Button button = new Button("Search");
    private final Label label = new Label();
    private final wikiPage revision = new wikiPage();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(createUI()));
        primaryStage.show();
    }

    private Parent createUI() {
        button.setOnAction((event) -> {
             button.setDisable(true);
             textField.setDisable(true);
             getInput();
        });

        VBox vbox = new VBox();
        vbox.getChildren().addAll(
                textField,
                button,
                label
        );

        return vbox;
    }

    private void getInput() {
        try {
            String wikipediaName = textField.getText();
            revision.findRevisionURL(wikipediaName);
        }
        catch(MalformedURLException nonExistentWikipediaPage) {
            ErrorWindow URLError = new ErrorWindow("Wikipedia page doesn't exist");
            URLError.displayError();
        }
    }
}
