package game.bonusItems.interfaces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public interface Bonus {

    Rectangle getBoundingBox();

    ImageView getImageView();

    int getPosX();

    int getPosY();

    Image getImage();
}
