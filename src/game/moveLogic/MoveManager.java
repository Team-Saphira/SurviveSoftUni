package game.moveLogic;

import com.sun.org.apache.xpath.internal.SourceTree;
import game.collisions.CollisionManager;
import game.level.Block;
import game.models.HumanObject;
import game.level.Level;
import game.models.Player;
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
                if (CollisionManager.checkWallCollision(this.humanObject, movingRight, platform, 'x', true)) {
                    this.humanObject.setIsInCollision(true);
                    if (this.humanObject.getClass().getSimpleName().equals("Player")) {
                    }
                    return;
                }
            }
            for (Shape platform : Level.destructibleBlockBBoxes) {
                if (CollisionManager.checkWallCollision(this.humanObject, movingRight, platform, 'x', true)) {
                    this.humanObject.setIsInCollision(true);
                    if (this.humanObject.getClass().getSimpleName().equals("Player")) {
                    }
                    return;
                }
            }
            for (Shape platform : Level.passableBlockBBoxes) {
                if (CollisionManager.checkWallCollision(this.humanObject, movingRight, platform, 'x', false)) {
                    if (this.humanObject.getClass().getSimpleName().equals("Player")) {
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
                if (CollisionManager.checkWallCollision(this.humanObject, movingDown, platform, 'y', true)) {
                    this.humanObject.setIsInCollision(true);
                    return;
                }
            }
            for (Shape platform : Level.destructibleBlockBBoxes) {
                if (CollisionManager.checkWallCollision(this.humanObject, movingDown, platform, 'y', true)) {
                    this.humanObject.setIsInCollision(true);
                    return;
                }
            }
            for (Shape platform : Level.passableBlockBBoxes) {
                if (CollisionManager.checkWallCollision(this.humanObject, movingDown, platform, 'y', false)) {
                    if (this.humanObject.getClass().getSimpleName().equals("Player")) {
                        //Level.levelNumber++;
                        Level.shouldChangeLevel = true;
                        System.out.println("Should exit");
                        break;
                    }
                }
            }

            this.humanObject.setIsInCollision(false);
            this.humanObject.setTranslateY(this.humanObject.getTranslateY() + (movingDown ? 1 : -1));
            this.humanObject.getBoundingBox().setTranslateY(this.humanObject.getBoundingBox().getTranslateY() + (movingDown ? 1 : -1));
        }
    }
}
