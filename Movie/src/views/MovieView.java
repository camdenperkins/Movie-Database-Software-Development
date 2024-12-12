package views;

// Camden Perkins 
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.LoadData;
import controller.Queries;

//MovieView class sets up the JavaFX application for interacting with a movie database.

public class MovieView extends Application {
    private Queries queries;

    @Override
    public void start(Stage primaryStage) {
        queries = new Queries(new LoadData());

        primaryStage.setTitle("Movie Database");

        Label instructionLabel = new Label("Choose an option:");

        Button earningsButton = new Button("Get total US box office earnings in a single year");
        Button directorsButton = new Button("Get the count of movies for each director");
        Button topDirectorsButton = new Button("Get a list of top N directors by number of appearances");
        Button rankInfoButton = new Button("Find the director or cast of a movie by rank");
        Button highestRatedButton = new Button("Get the highest-rated movie in a given year");

        TextField inputField = new TextField();
        inputField.setVisible(false);

        TextField rankTypeField = new TextField();
        rankTypeField.setPromptText("Enter rank type ('boxOffice' or 'topRated')");
        rankTypeField.setVisible(false);

        TextField infoTypeField = new TextField();
        infoTypeField.setPromptText("Enter 'director' or 'cast'");
        infoTypeField.setVisible(false);

        Button submitButton = new Button("Submit");
        submitButton.setVisible(false);

        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);

        ButtonHandler buttonHandler = new ButtonHandler(queries, inputField, rankTypeField, infoTypeField, resultArea, submitButton);

        // Set up event handlers for each button
        buttonHandler.handleEarningsButton(earningsButton);
        buttonHandler.handleDirectorsButton(directorsButton);
        buttonHandler.handleTopDirectorsButton(topDirectorsButton);
        buttonHandler.handleRankInfoButton(rankInfoButton);
        buttonHandler.handleHighestRatedButton(highestRatedButton);

        // Layout the UI components in a vertical box
        VBox vbox = new VBox(10, instructionLabel, earningsButton, directorsButton, topDirectorsButton, rankInfoButton, highestRatedButton, inputField, rankTypeField, infoTypeField, submitButton, resultArea);
        Scene scene = new Scene(vbox, 600, 400);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
