package game.menus;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Title extends StackPane {
    private static final String TEXT_FONT_TYPE = "Tw Cen MT Condensed";
    private static final int TEXT_FONT_SIZE = 50;
    private static final int RECTANGLE_WIDTH = 640;
    private static final int RECTANGLE_HEIGHT = 60;
    private static final int RECTANGLE_BORDER_WIDTH = 2;

    public Title(String name) {
        Rectangle rect = new Rectangle(RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
        rect.setStroke(Color.WHITE);
        rect.setStrokeWidth(RECTANGLE_BORDER_WIDTH);
        rect.setFill(null);

        Text text = new Text(name);
        text.setFill(Color.WHITE);
        text.setFont(Font.font(TEXT_FONT_TYPE, FontWeight.EXTRA_BOLD, TEXT_FONT_SIZE));

        setAlignment(Pos.CENTER);
        getChildren().addAll(rect, text);
    }
}
