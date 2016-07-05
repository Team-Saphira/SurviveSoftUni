package game.menus;

import javafx.scene.layout.VBox;

public class MenuBox extends VBox {
    private static final int SPACING_SIZE = 5;

    public MenuBox(MainMenu... items) {
        setSpacing(SPACING_SIZE);

        for (MainMenu item : items) {
            getChildren().addAll(item);
        }
    }
}
