package edu.bsu.cs222.view;

import edu.bsu.cs222.model.wikiPage;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.MalformedURLException;

public class MainWindow extends Application {

    private final TextField textField = new TextField();
    private final Button button = new Button("Search");
    private final Label label = new Label("Enter a Wikipedia Page");
    private final wikiPage revision = new wikiPage();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(createUI()));
        primaryStage.setTitle("Wikipedia Revision Getter");
        primaryStage.getIcons().add(new Image("Wikipedia Icon.jpg"));
        primaryStage.setWidth(500.0);
        primaryStage.show();
    }

    private Parent createUI() {
        label.setFont(Font.font("Comic Sans", 14));
        button.setOnAction((event) -> {
            getInput();
        });

        VBox vbox = new VBox();
        vbox.getChildren().addAll(
                label,
                textField,
                button
        );

        return vbox;
    }

    private void getInput() {
        String wikipediaName = textField.getText();

        try {
            revision.findRevisionURL(wikipediaName);
        }
        catch(MalformedURLException nonExistentWikipediaPage) {
            ErrorWindow URLError = new ErrorWindow(wikipediaName + " doesn't exist");
            URLError.displayError();
        }
    }
}
