package game.gui;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ScoreBar extends Pane {
    private Text text;
    private String scoreText;

    public ScoreBar(int score) {
        this.setText("Score: " + score);

    }

    public void setText(String setScore) {
        this.text = new Text(setScore);
        this.text.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        this.text.setFill(Color.GREEN);
        this.text.setStroke(Color.BLACK);
        this.text.setLayoutX(20);
        this.text.setLayoutY(60);

        if (this.getChildren().size()>0) {
            this.getChildren().remove(0,1);
        }
        this.getChildren().add(this.text);
    }

    public void changeScore(int newScore) {
        this.scoreText = String.format("Score: %d", newScore);
        this.setText(this.scoreText);
    }
}
