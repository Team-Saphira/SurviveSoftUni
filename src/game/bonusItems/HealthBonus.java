package game.bonusItems;

import game.bonusItems.enums.BonusType;
import game.staticData.Constants;
import game.bonusItems.interfaces.Bonus;
import game.sprites.ImageLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HealthBonus extends BonusImpl {

    public HealthBonus(int posX, int posY) {
        super(posX, posY);
        this.setBonusType(BonusType.HEARTH);
        this.configureImageView(ImageLoader.HEART);
        this.configureBoundingBox();
        this.getChildren().addAll(this.getImageView(), this.getBoundingBox());
    }
}
