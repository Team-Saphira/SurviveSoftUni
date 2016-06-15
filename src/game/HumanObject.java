package game;

import game.Level.Block;
import game.Level.Level;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public abstract class HumanObject extends Pane {
    int count;
    int columns;
    int offsetX;
    int offsetY;
    int width;
    int height;

    public Shape boundingBox;

    // for object ID
    int id;

    //the position on the matrix
    int posX;
    int posY;

    public SpriteAnimation animation;

    public void moveX(int value,int size) {
        boolean movingRight = value > 0;
        for (int i = 0; i < Math.abs(value); i++) {
            for (Shape platform : Level.bboxes) {
                if (this.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                    if (movingRight) {
                        if (this.getTranslateX() + size == platform.getTranslateX()) {
                            this.setTranslateX(this.getTranslateX() - 1);
                            return;
                        }
                    } else {
                        if (this.getTranslateX() == platform.getTranslateX() + Block.BLOCK_SIZE) {
                            this.setTranslateX(this.getTranslateX() + 1);
                            return;
                        }
                    }
                }
            }
            this.setTranslateX(this.getTranslateX() + (movingRight ? 1 : -1));
            this.boundingBox.setTranslateX(this.boundingBox.getTranslateX() + (movingRight ? 1 : -1));
        }
    }

    public void moveY(int value, int size) {
        boolean movingDown = value > 0;
        for (int i = 0; i < Math.abs(value); i++) {
            for (Shape platform : Level.bboxes) {
                if (getBoundsInParent().intersects(platform.getBoundsInParent())) {
                    if (movingDown) {
                        if (this.getTranslateY() + size == platform.getTranslateY()) {
                            this.setTranslateY(this.getTranslateY() - 1);
                            return;
                        }
                    } else {
                        if (this.getTranslateY() == platform.getTranslateY() + Block.BLOCK_SIZE) {
                            this.setTranslateY(this.getTranslateY() + 1);
                            return;
                        }
                    }
                }
            }
            this.setTranslateY(this.getTranslateY() + (movingDown ? 1 : -1));
            this.boundingBox.setTranslateY(this.boundingBox.getTranslateY() + (movingDown ? 1 : -1));
        }
    }
    protected Shape calcBoundingBox(int size) {
        Circle circleBBox = new Circle(this.getTranslateX() + size/2, this.getTranslateY() + size/2, size/2+4);
        circleBBox.setOpacity(0);

        return circleBBox;
    }
}
