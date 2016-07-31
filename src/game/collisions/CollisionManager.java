package game.collisions;

import game.staticData.Constants;
import game.models.interfaces.HumanObject;
import game.moveLogic.Axis;
import javafx.scene.shape.Shape;

public class CollisionManager {

    public static boolean checkWallCollision(HumanObject humanObject,
                                             boolean isPositiveDirection,
                                             Shape platform,
                                             Axis axis,
                                             boolean activeCollision) {
        if (axis == Axis.X) {
            if (humanObject.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                if (!activeCollision){
                    return true;
                }
                if (isPositiveDirection) {
                    if (humanObject.getTranslateX() + humanObject.getObjectSize() == platform.getTranslateX()) {
                        humanObject.setTranslateX(humanObject.getTranslateX() - 1);
                        humanObject.getBoundingBox().setTranslateX(humanObject.getBoundingBox().getTranslateX() - 1);
                        return true;
                    }
                } else {
                    if (humanObject.getTranslateX() == platform.getTranslateX() + Constants.BLOCK_SIZE) {
                        humanObject.setTranslateX(humanObject.getTranslateX() + 1);
                        humanObject.getBoundingBox().setTranslateX(humanObject.getBoundingBox().getTranslateX() + 1);
                        return true;
                    }
                }
            }
        } else {
            if (humanObject.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                if (!activeCollision){
                    return true;
                }
                if (isPositiveDirection) {
                    if (humanObject.getTranslateY() + humanObject.getObjectSize() == platform.getTranslateY()) {
                        humanObject.setTranslateY(humanObject.getTranslateY() - 1);
                        humanObject.getBoundingBox().setTranslateY(humanObject.getBoundingBox().getTranslateY() - 1);

                        return true;
                    }
                } else {
                    if (humanObject.getTranslateY() == platform.getTranslateY() + Constants.BLOCK_SIZE) {
                        humanObject.setTranslateY(humanObject.getTranslateY() + 1);
                        humanObject.getBoundingBox().setTranslateY(humanObject.getBoundingBox().getTranslateY() + 1);
                        return true;

                    }
                }
            }
        }
        return false;
    }
}
