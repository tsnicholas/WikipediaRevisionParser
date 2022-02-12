package edu.bsu.cs222.view;

import edu.bsu.cs222.model.PageDoesNotExistException;
import edu.bsu.cs222.model.RevisionData;
import edu.bsu.cs222.model.WikiPageReader;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainWindow extends Application {
    private final TextField searchInput = new TextField();
    private final Button searchButton = new Button("Search");
    private final Label instruction = new Label("Enter the name of Wikipedia Page");
    private final Text redirectInfo = new Text("");
    private final Text usernameColumn = new Text("");
    private final Text timestampColumn = new Text("");
    private WikiPageReader wikiRevisionPage;
    private String nameOfWiki;

    private final Executor executor = Executors.newSingleThreadExecutor();

    @Override
    public void start(Stage primaryStage) {
        setUpWindowBasics(primaryStage);
        setUpExit(primaryStage);
        setUpDefaultSize(primaryStage);
        primaryStage.show();
    }

    private void setUpWindowBasics(Stage primaryStage) {
        primaryStage.setScene(new Scene(createMainWindow()));
        primaryStage.setTitle("Wikipedia Revision Getter");
        primaryStage.getIcons().add(new Image("Wikipedia Icon.jpg"));
    }

    private void setUpExit(Stage primaryStage) {
        primaryStage.setOnCloseRequest(X -> {
            Platform.exit();
            System.exit(0);
        });
    }

    private void setUpDefaultSize(Stage primaryStage) {
        primaryStage.setWidth(550.0);
        primaryStage.setHeight(400.0);
    }

    private Parent createMainWindow() {
        VBox mainWindow = new VBox();
        mainWindow.setAlignment(Pos.TOP_CENTER);
        setUpTextFormatting();
        mainWindow.getChildren().addAll(
                instruction,
                createSearchBar(),
                redirectInfo,
                createResultDisplay()
        );
        return mainWindow;
    }

    private void setUpTextFormatting() {
        instruction.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 14));
        redirectInfo.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 14));
        usernameColumn.setFont(Font.font("Verdana", 13));
        timestampColumn.setFont(Font.font("Verdana", 13));
    }

    private Parent createSearchBar() {
        setUpButton();
        searchInput.setPrefWidth(485.0);
        HBox searchBar = new HBox();
        searchBar.getChildren().addAll(
                searchInput,
                searchButton
        );
        return searchBar;
    }

    private Parent createResultDisplay() {
        ScrollPane resultDisplay = new ScrollPane();
        HBox printedText = new HBox();
        Text buffer = new Text("    ");
        printedText.getChildren().addAll(
                usernameColumn,
                buffer,
                timestampColumn
        );
        resultDisplay.setContent(printedText);
        return resultDisplay;
    }

    private void setUpButton() {
        searchButton.setOnAction((event) -> {
            searchInput.setDisable(true);
            searchButton.setDisable(true);

            executor.execute(() -> {
                nameOfWiki = searchInput.getText();
                processWikiPage();
                getRevisionData();

                Platform.runLater(() -> {
                    searchInput.setDisable(false);
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
            RevisionData revisionData = wikiRevisionPage.retrieveRevisionData();
            redirectInfo.setText(revisionData.getRedirectInfo());
            usernameColumn.setText(revisionData.getUsernames());
            timestampColumn.setText(revisionData.getTimestamps());
        }
        catch(PageDoesNotExistException doesNotExistException) {
            redirectInfo.setText("Page does not exist");
            usernameColumn.setText("");
            timestampColumn.setText("");
        }
        catch(IOException ioException) {
            System.err.println("Error processing data: \n" + ioException.getMessage());
            genericError();
        }
    }

    private void genericError() {
        ErrorWindow errorWindow = new ErrorWindow("An error has occurred");
        errorWindow.displayError();
        System.exit(0);
    }
}
