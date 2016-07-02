package game.moveLogic;

import game.models.HumanObject;
import game.level.Block;
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
    public void moveX(int value, int size) {
        boolean movingRight = value > 0;
        for (int i = 0; i < Math.abs(value); i++) {
            for (Shape platform : Level.bboxes) {
                if (this.humanObject.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                    if (movingRight) {
                        if (this.humanObject.getTranslateX() + size == platform.getTranslateX()) {
                            this.humanObject.setTranslateX(this.humanObject.getTranslateX() - 1);
                            return;
                        }
                    } else {
                        if (this.humanObject.getTranslateX() == platform.getTranslateX() + Block.BLOCK_SIZE) {
                            this.humanObject.setTranslateX(this.humanObject.getTranslateX() + 1);
                            return;
                        }
                    }
                }
            }
            this.humanObject.setTranslateX(this.humanObject.getTranslateX() + (movingRight ? 1 : -1));
            this.humanObject.getBoundingBox().setTranslateX(this.humanObject.getBoundingBox().getTranslateX() + (movingRight ? 1 : -1));
        }
    }

    @Override
    public void moveY(int value, int size) {
        boolean movingDown = value > 0;
        for (int i = 0; i < Math.abs(value); i++) {
            for (Shape platform : Level.bboxes) {
                if (this.humanObject.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                    if (movingDown) {
                        if (this.humanObject.getTranslateY() + size == platform.getTranslateY()) {
                            this.humanObject.setTranslateY(this.humanObject.getTranslateY() - 1);
                            return;
                        }
                    } else {
                        if (this.humanObject.getTranslateY() == platform.getTranslateY() + Block.BLOCK_SIZE) {
                            this.humanObject.setTranslateY(this.humanObject.getTranslateY() + 1);
                            return;
                        }
                    }
                }
            }
            this.humanObject.setTranslateY(this.humanObject.getTranslateY() + (movingDown ? 1 : -1));
            this.humanObject.getBoundingBox().setTranslateY(this.humanObject.getBoundingBox().getTranslateY() + (movingDown ? 1 : -1));
        }
    }
}
