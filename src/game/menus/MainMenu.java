package game.menus;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MainMenu extends StackPane {
    private static final String TEXT_FONT_TYPE = "Tw Cen MT Condensed";
    private static final int TEXT_FONT_SIZE = 22;
    private static final int RECTANGLE_WIDTH = 200;
    private static final int RECTANGLE_HEIGHT = 30;
    private static final double RECTANGLE_OPACITY = 0.4;

    public MainMenu(String name) {
        LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, new Stop[]{
                new Stop(0, Color.DARKVIOLET),
                new Stop(0.1, Color.BLACK),
                new Stop(0.9, Color.BLACK),
                new Stop(1, Color.DARKVIOLET),
        });

        Rectangle rect = new Rectangle(RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
        rect.setOpacity(RECTANGLE_OPACITY);

        Text text = new Text(name);
        text.setFill(Color.DARKGRAY);
        text.setFont(Font.font(TEXT_FONT_TYPE, FontWeight.SEMI_BOLD, TEXT_FONT_SIZE));

        setAlignment(Pos.CENTER);
        getChildren().addAll(rect, text);

        setOnMouseEntered(event -> {
            rect.setFill(gradient);
            text.setFill(Color.WHITE);
        });

        setOnMouseExited(event -> {
            rect.setFill(Color.BLACK);
            text.setFill(Color.DARKGRAY);
        });

        setOnMousePressed(event -> rect.setFill(Color.DARKVIOLET));

        setOnMouseReleased(event -> rect.setFill(gradient));
    }
}
