package edu.bsu.cs222.view;

import edu.bsu.cs222.model.RevisionData;
import edu.bsu.cs222.model.WikiPageReader;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainWindow extends Application {
    private final TextField textField = new TextField();
    private final Button searchButton = new Button("Search");
    private final Label instruction = new Label("Enter the name of Wikipedia Page");
    private final Text revisions = new Text("");
    private WikiPageReader wikiRevisionPage;
    private String nameOfWiki;

    private final Executor executor = Executors.newSingleThreadExecutor();

    @Override
    public void start(Stage primaryStage) {
        setUpWindowBasics(primaryStage);
        setSizes(primaryStage);
        primaryStage.show();
    }

    private void setUpWindowBasics(Stage primaryStage) {
        primaryStage.setScene(new Scene(createMainWindow()));
        primaryStage.setTitle("Wikipedia Revision Getter");
        primaryStage.getIcons().add(new Image("Wikipedia Icon.jpg"));
        primaryStage.setOnCloseRequest(X -> {
            Platform.exit();
            System.exit(0);
        });
    }

    private void setSizes(Stage primaryStage) {
        primaryStage.setWidth(300.0);
        primaryStage.setHeight(350.0);
    }

    private Parent createMainWindow() {
        VBox mainWindow = new VBox();
        mainWindow.getChildren().addAll(
                instruction,
                createSearchBar(),
                createResultDisplay()
        );
        return mainWindow;
    }

    private Parent createSearchBar() {
        setUpButton();
        HBox searchBar = new HBox();
        searchBar.getChildren().addAll(
                textField,
                searchButton
        );
        return searchBar;
    }

    private Parent createResultDisplay() {
        ScrollPane resultDisplay = new ScrollPane();
        VBox printedText = new VBox();
        printedText.getChildren().add(revisions);
        resultDisplay.setContent(printedText);
        return resultDisplay;
    }

    private void setUpButton() {
        searchButton.setOnAction((event) -> {
            textField.setDisable(true);
            searchButton.setDisable(true);

            executor.execute(() -> {
                nameOfWiki = textField.getText();
                processWikiPage();
                getRevisionData();

                Platform.runLater(() -> {
                    textField.setDisable(false);
                    searchButton.setDisable(false);
                });
            });
        });
    }

    private void processWikiPage() {
        try {
            wikiRevisionPage = new WikiPageReader(nameOfWiki);
            wikiRevisionPage.connectToNetwork();
        }
        catch(MalformedURLException malformedURLException) {
            System.err.println("URL Error: \n" + malformedURLException.getMessage());
            genericError();
        }
        catch(IOException networkError) {
            System.err.println("Network error: \n" + networkError.getMessage());
            ErrorWindow networkErrorAlert = new ErrorWindow("A network error has occurred");
            networkErrorAlert.displayError();
            System.exit(0);
        }
    }

    private void getRevisionData() {
        try {
            revisions.setText(revisionData());
        }
        catch(IOException ioException) {
            System.err.println("Error processing data: \n" + ioException.getMessage());
            genericError();
        }
    }

    private String revisionData() throws IOException {
        RevisionData revisionData = wikiRevisionPage.retrieveRevisionData();
        return revisionData.toString();
    }

    private void genericError() {
        ErrorWindow errorWindow = new ErrorWindow("An error has occurred");
        errorWindow.displayError();
        System.exit(0);
    }
}
