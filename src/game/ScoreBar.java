package game;

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
        this.text.setTranslateX(20);
        this.text.setTranslateY(60);

        //IB super ugly!!! I don't know how else to remove
        // the score bar so that new score won't overlap old one.
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
