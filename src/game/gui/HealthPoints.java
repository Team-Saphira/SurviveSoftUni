package game.gui;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class HealthPoints extends Pane {
    private Text text;
    private String healthText;

    public HealthPoints(int health) {
        this.setText("Health: " + health);
    }

    public void changeHealthPoints(int newHealth) {
        this.healthText = String.format("HEALTH:  %d", newHealth);
        this.setText(this.healthText);
    }

    private void setText(String setHealth) {
        this.text = new Text(setHealth);
        this.text.setFont(Font.font("Calibri", FontWeight.BOLD, 15));
        this.text.setFill(Color.WHITE);
        this.text.setLayoutX(25);
        this.text.setLayoutY(600);

        if (this.getChildren().size() > 0) {
            this.getChildren().remove(0, 1);
        }
        this.getChildren().add(this.text);
    }
}
