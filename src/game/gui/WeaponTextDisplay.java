package game.gui;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class WeaponTextDisplay extends Pane {

    private Text text;
    private String weaponText;

    public WeaponTextDisplay(String weaponName) {
        this.setText("Weapon loaded: " + weaponName);
    }

    public void changeWeaponDisplayText(String weaponName) {
        this.weaponText = String.format("WEAPON:  %s", weaponName);
        this.setText(this.weaponText);
    }

    private void setText(String setWeaponText) {
        this.text = new Text(setWeaponText);
        this.text.setFont(Font.font("Calibri", FontWeight.BOLD, 15));
        this.text.setFill(Color.WHITE);
        this.text.setLayoutX(728);
        this.text.setLayoutY(578);

        if (this.getChildren().size() > 0) {
            this.getChildren().remove(0, 1);
        }
        this.getChildren().add(this.text);
    }
}
