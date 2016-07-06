package game.moveLogic;

import game.collisions.CollisionManager;
import game.models.HumanObject;
import game.level.Level;
import javafx.scene.shape.Shape;

public abstract class MoveManager implements Movable {
    protected HumanObject humanObject;

    public MoveManager(HumanObject humanObject) {
        this.humanObject = humanObject;
    }

    public HumanObject getHumanObject() {
        return humanObject;
    }

    public void setHumanObject(HumanObject humanObject) {
        this.humanObject = humanObject;
    }

    @Override
    public void moveX(int value) {
        boolean movingRight = value > 0;
        for (int i = 0; i < Math.abs(value); i++) {
            for (Shape platform : Level.impassableBlockBBoxes) {
                if (CollisionManager.checkWallCollision(this.humanObject, movingRight, platform, 'x')) {
                    this.humanObject.setIsInCollision(true);
                    if(this.humanObject.getClass().getSimpleName().equals("Player")) {
                    }
                    return;
                }

            }
            this.humanObject.setIsInCollision(false);
            this.humanObject.setTranslateX(this.humanObject.getTranslateX() + (movingRight ? 1 : -1));
            this.humanObject.getBoundingBox().setTranslateX(this.humanObject.getBoundingBox().getTranslateX() + (movingRight ? 1 : -1));
        }
    }

    @Override
    public void moveY(int value) {
        boolean movingDown = value > 0;
        for (int i = 0; i < Math.abs(value); i++) {
            for (Shape platform : Level.impassableBlockBBoxes) {
                if (CollisionManager.checkWallCollision(this.humanObject, movingDown, platform, 'y')) {
                    this.humanObject.setIsInCollision(true);
                    return;
                }
            }
            this.humanObject.setIsInCollision(false);
            this.humanObject.setTranslateY(this.humanObject.getTranslateY() + (movingDown ? 1 : -1));
            this.humanObject.getBoundingBox().setTranslateY(this.humanObject.getBoundingBox().getTranslateY() + (movingDown ? 1 : -1));
        }
    }
}
