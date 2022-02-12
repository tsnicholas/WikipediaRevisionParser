package edu.bsu.cs222.view;

import edu.bsu.cs222.model.WikiPageRevisionReader;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.MalformedURLException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainWindow extends Application {

    private final TextField textField = new TextField("");
    private final Button searchButton = new Button("Search");
    private final Label instruction = new Label("Enter the name of Wikipedia Page");
    private final Text revisions = new Text("");
    private WikiPageRevisionReader wikiPageRevisionData;

    private final Executor executor = Executors.newSingleThreadExecutor();


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(createUI()));
        primaryStage.setTitle("Wikipedia Revision Getter");
        primaryStage.getIcons().add(new Image("Wikipedia Icon.jpg"));
        primaryStage.setWidth(500.0);
        primaryStage.show();
    }

    private Parent createUI() {
        searchButton.setAlignment(Pos.CENTER);

        searchButton.setOnAction((event) -> {
            textField.setDisable(true);
            searchButton.setDisable(true);
            executor.execute(() -> {
                String wikiName = textField.getText();
                processWikipedia(wikiName);
                revisions.setText(wikiPageRevisionData.toString());

                Platform.runLater(() -> {
                    textField.setDisable(false);
                    searchButton.setDisable(false);
                });
            });
        });

        VBox vbox = new VBox();
        vbox.getChildren().addAll(
                instruction,
                textField,
                searchButton,
                revisions
        );

        return vbox;
    }

    private void processWikipedia(String wikiName) {
        try {
            wikiPageRevisionData = new WikiPageRevisionReader(wikiName);
        }
        catch(MalformedURLException malformedURLException) {
            System.err.println(malformedURLException.getMessage());
            ErrorWindow URLError = new ErrorWindow("An URL Error has occurred");
            URLError.displayError();
            System.exit(0);
        }
    }
}
