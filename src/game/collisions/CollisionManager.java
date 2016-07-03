package game.collisions;

import game.Constants;
import game.level.Block;
import game.models.HumanObject;
import javafx.scene.shape.Shape;

public class CollisionManager {

    public static boolean checkWallCollision(HumanObject humanObject, boolean movingBoolean, Shape platform, char axis) {
        if (axis == 'x') {
            if (humanObject.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                if (movingBoolean) {
                    if (humanObject.getTranslateX() + humanObject.getObjectSize() == platform.getTranslateX()) {
                        humanObject.setTranslateX(humanObject.getTranslateX() - 1);
                        return true;
                    }
                } else {
                    if (humanObject.getTranslateX() == platform.getTranslateX() + Constants.BLOCK_SIZE) {
                        humanObject.setTranslateX(humanObject.getTranslateX() + 1);
                        return true;
                    }
                }
            }
        } else {
            if (humanObject.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                if (movingBoolean) {
                    if (humanObject.getTranslateY() + humanObject.getObjectSize() == platform.getTranslateY()) {
                        humanObject.setTranslateY(humanObject.getTranslateY() - 1);
                        return true;
                    }
                } else {
                    if (humanObject.getTranslateY() == platform.getTranslateY() + Constants.BLOCK_SIZE) {
                        humanObject.setTranslateY(humanObject.getTranslateY() + 1);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
