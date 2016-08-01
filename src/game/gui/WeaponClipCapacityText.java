package game.gui;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class WeaponClipCapacityText extends Pane {

    private Text text;
    private String clipCapacityText;

    public WeaponClipCapacityText(int bulletsLeft) {
        this.setText("Capacity: " + bulletsLeft);
    }

    public void changeClipCapacity(int currentCapacity, int totalCapacity) {
        this.clipCapacityText = String.format("%d/%d", currentCapacity, totalCapacity);
        this.setText(this.clipCapacityText);
    }

    private void setText(String setCapacity) {
        this.text = new Text(setCapacity);
        this.text.setFont(Font.font("Calibri", FontWeight.BOLD, 15));
        this.text.setFill(Color.WHITE);
        this.text.setLayoutX(857);
        this.text.setLayoutY(578);

        if (this.getChildren().size() > 0) {
            this.getChildren().remove(0, 1);
        }
        this.getChildren().add(this.text);
    }
}
