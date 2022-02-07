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
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainWindow extends Application {

    private final TextField textField = new TextField();
    private final Button button = new Button("Search");
    private final Label label = new Label();
    private final wikiPage revision = new wikiPage();

    private final Executor executor = Executors.newSingleThreadExecutor();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(createUI()));
        primaryStage.show();
    }

    // TODO: Add error message when there's no wikipedia page with the name
    private Parent createUI() {
        button.setOnAction((event) -> {
             button.setDisable(true);
             textField.setDisable(true);

            executor.execute(() -> {
                obtainInputURL();
            });
        });

        VBox vbox = new VBox();
        vbox.getChildren().addAll(
                textField,
                button,
                label
        );

        return vbox;
    }

    private void obtainInputURL() {
        try {
            String wikipediaName = textField.getText();
            revision.findRevisionURL(wikipediaName);
        }
        catch(MalformedURLException malformedURLException) {
            URLErrorWindow error = new URLErrorWindow();
        }
    }
}
