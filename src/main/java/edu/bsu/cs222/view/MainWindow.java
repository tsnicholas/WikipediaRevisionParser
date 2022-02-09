package edu.bsu.cs222.view;

import edu.bsu.cs222.model.WikiPageReader;
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
    private final Button searchButton = new Button("Search");
    private final Label instruction = new Label("Enter the name of Wikipedia Page");

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(createUI()));
        primaryStage.setTitle("Wikipedia Revision Getter");
        primaryStage.getIcons().add(new Image("Wikipedia Icon.jpg"));
        primaryStage.setWidth(500.0);
        primaryStage.show();
    }

    private Parent createUI() {
        instruction.setFont(Font.font("Comic Sans", 14));
        searchButton.setOnAction((event) -> {
            String wikiName = textField.getText();
            processWikipedia(wikiName);
        });

        VBox vbox = new VBox();
        vbox.getChildren().addAll(
                instruction,
                textField,
                searchButton
        );

        return vbox;
    }

    private void processWikipedia(String wikiName) {
        try {
            WikiPageReader wikiPage = new WikiPageReader(wikiName);
            wikiPage.connect();
            wikiPage.getRevisions();
        }
        catch(MalformedURLException malformedURLException) {
            ErrorWindow URLError = new ErrorWindow("An error has occurred.");
            URLError.displayError();
        }
        catch(Exception e) {
            ErrorWindow ConnectionError = new ErrorWindow("A network error has occurred");
            ConnectionError.displayError();
        }
    }
}
