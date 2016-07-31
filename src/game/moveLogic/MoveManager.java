package game.moveLogic;

import game.collisions.CollisionManager;
import game.level.Level;
import game.models.Player;
import game.models.interfaces.HumanObject;
import game.moveLogic.interfaces.Movable;
import javafx.scene.shape.Shape;

import java.util.List;

public abstract class MoveManager implements Movable {
    protected HumanObject humanObject;

    public MoveManager(HumanObject humanObject) {
        this.humanObject = humanObject;
    }

    public HumanObject getHumanObject() {
        return this.humanObject;
    }

    public void setHumanObject(HumanObject humanObject) {
        this.humanObject = humanObject;
    }

    @Override
    public void move(int value, Axis axis) {
        boolean isPositiveDirection = value > 0;
        for (int i = 0; i < Math.abs(value); i++) {

            if (checkPlatformsForCollisions(axis, isPositiveDirection, Level.impassableBlockBBoxes)) {
                return;
            }
            if (checkPlatformsForCollisions(axis, isPositiveDirection, Level.destructibleBlockBBoxes)) {
                return;
            }
            for (Shape platform : Level.passableBlockBBoxes) {
                if (CollisionManager.checkWallCollision(this.humanObject, isPositiveDirection, platform, axis, false)) {
                    if (this.humanObject instanceof Player) {
                        //Level.levelNumber++;
                        Level.shouldChangeLevel = true;
                        System.out.println("Should exit");
                        break;
                    }
                }
            }
            if (Level.shouldChangeLevel) {
                //return;
            }

            this.humanObject.isInCollision(false);
            if (axis == Axis.X) {
                this.humanObject.setTranslateX(this.humanObject.getTranslateX() + (isPositiveDirection ? 1 : -1));
                this.humanObject.getBoundingBox()
                        .setTranslateX(this.humanObject.getBoundingBox().getTranslateX() + (isPositiveDirection ? 1 : -1));
            } else {
                this.humanObject.setTranslateY(this.humanObject.getTranslateY() + (isPositiveDirection ? 1 : -1));
                this.humanObject.getBoundingBox()
                        .setTranslateY(this.humanObject.getBoundingBox().getTranslateY() + (isPositiveDirection ? 1 : -1));
            }
        }
    }

    private boolean checkPlatformsForCollisions(Axis axis, boolean isPositiveDirection, List<Shape> blockBBoxes) {
        for (Shape platform : blockBBoxes) {
            if (CollisionManager.checkWallCollision(this.humanObject, isPositiveDirection, platform, axis, true)) {
                this.humanObject.isInCollision(true);
                if (this.humanObject instanceof Player) {

                }
                return true;
            }
        }
        return false;
    }
}
