package views;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import controller.Queries;

public class ButtonHandler {
    // References to Queries instance and UI components
    private Queries queries;
    private TextField inputField;
    private TextField rankTypeField;
    private TextField infoTypeField;
    private TextArea resultArea;
    private Button submitButton;

    // Constructor to initialize the ButtonHandler with the necessary components
    public ButtonHandler(Queries queries, TextField inputField, TextField rankTypeField, TextField infoTypeField, TextArea resultArea, Button submitButton) {
        this.queries = queries;
        this.inputField = inputField;
        this.rankTypeField = rankTypeField;
        this.infoTypeField = infoTypeField;
        this.resultArea = resultArea;
        this.submitButton = submitButton;
    }

    // Method to handle actions for the Earnings button
    public void handleEarningsButton(Button button) {
        button.setOnAction(e -> {
            inputField.setPromptText("Enter year");
            inputField.setVisible(true); 
            rankTypeField.setVisible(false);
            infoTypeField.setVisible(false);
            submitButton.setVisible(true); 
            submitButton.setOnAction(event -> {
                try {
                    int year = Integer.parseInt(inputField.getText()); // Parse year from input
                    long totalEarnings = queries.getTotalEarningsInYear(year); // Get total earnings for the year
                    resultArea.setText("Total box office earnings in " + year + ": $" + totalEarnings); // Display result
                } catch (NumberFormatException ex) {
                    resultArea.setText("Please enter a valid year."); // Handle invalid input
                }
            });
        });
    }

    // Method to handle actions for the Directors button
    public void handleDirectorsButton(Button button) {
        button.setOnAction(e -> {
            inputField.setVisible(false); 
            rankTypeField.setVisible(false);
            infoTypeField.setVisible(false);
            submitButton.setVisible(false);
            // Get and display sorted director movie counts
            String result = queries.getSortedDirectorsString(
                queries.sortDirectors(queries.getDirectors()),
                "Director movie counts (sorted alphabetically):"
            );
            resultArea.setText(result);
        });
    }

    // Method to handle actions for the Top Directors button
    public void handleTopDirectorsButton(Button button) {
        button.setOnAction(e -> {
            inputField.setPromptText("Enter number of top directors"); // Set prompt text for input field
            inputField.setVisible(true); 
            rankTypeField.setVisible(false); 
            infoTypeField.setVisible(false); 
            submitButton.setVisible(true); 
            submitButton.setOnAction(event -> {
                try {
                    int topN = Integer.parseInt(inputField.getText()); // Parse number of top directors from input
                    // Get and display top directors by number of appearances
                    String result = queries.getSortedDirectorsString(
                        queries.getTopDirectors(topN),
                        "Top " + topN + " directors by number of appearances:"
                    );
                    resultArea.setText(result);
                } catch (NumberFormatException ex) {
                    resultArea.setText("Please enter a valid number."); // Handle invalid input
                }
            });
        });
    }

    // Method to handle actions for the Rank Info button
    public void handleRankInfoButton(Button button) {
        button.setOnAction(e -> {
            inputField.setPromptText("Enter rank"); // Set prompt text for input field
            inputField.setVisible(true); 
            rankTypeField.setVisible(true); 
            infoTypeField.setVisible(true);
            submitButton.setVisible(true); 
            submitButton.setOnAction(event -> {
                try {
                    int rank = Integer.parseInt(inputField.getText()); // Parse rank from input
                    String rankType = rankTypeField.getText(); // Get rank type from input
                    String infoType = infoTypeField.getText(); // Get info type from input
                    String result;
                    // Determine which info type to retrieve based on input
                    if (infoType.equalsIgnoreCase("director")) {
                        result = queries.getDirectorByRank(rank, rankType); // Get director by rank
                    } else if (infoType.equalsIgnoreCase("cast")) {
                        result = queries.getCastByRank(rank, rankType); // Get cast by rank
                    } else {
                        result = "Invalid option. Please specify 'director' or 'cast'."; // Handle invalid input
                    }
                    resultArea.setText(result);
                } catch (NumberFormatException ex) {
                    resultArea.setText("Please enter a valid rank."); // Handle invalid input
                }
            });
        });
    }

    // Method to handle actions for the Highest Rated button
    public void handleHighestRatedButton(Button button) {
        button.setOnAction(e -> {
            inputField.setPromptText("Enter year"); // Set prompt text for input field
            inputField.setVisible(true); 
            rankTypeField.setVisible(false);
            infoTypeField.setVisible(false); 
            submitButton.setVisible(true);
            submitButton.setOnAction(event -> {
                try {
                    int year = Integer.parseInt(inputField.getText()); // Parse year from input
                    String result = queries.getHighestRatedInYear(year); // Get highest rated movie in the year
                    resultArea.setText(result);
                } catch (NumberFormatException ex) {
                    resultArea.setText("Please enter a valid year."); // Handle invalid input
                }
            });
        });
    }
}
