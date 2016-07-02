package game;

import game.Level.Block;
import game.Level.Level;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public abstract class HumanObject extends Pane {
    private int spriteCount;
    private int spriteColumns;
    private int spriteOffsetX;
    private int spriteOffsetY;
    private int spriteWidth;
    private int spriteHeight;

    private Shape boundingBox;

    // for object ID
    private int id;

    //the position on the matrix
    private int posX;
    private int posY;

    private SpriteAnimation animation;

    protected HumanObject(int setTranslateX, int setTranslateY){
        this.setTranslateX(setTranslateX);
        this.setTranslateY(setTranslateY);
    }

    public int getSpriteCount() {
        return spriteCount;
    }

    public void setSpriteCount(int spriteCount) {
        this.spriteCount = spriteCount;
    }

    public int getSpriteColumns() {
        return spriteColumns;
    }

    public void setSpriteColumns(int spriteColumns) {
        this.spriteColumns = spriteColumns;
    }

    public int getSpriteOffsetX() {
        return spriteOffsetX;
    }

    public void setSpriteOffsetX(int spriteOffsetX) {
        this.spriteOffsetX = spriteOffsetX;
    }

    public int getSpriteOffsetY() {
        return spriteOffsetY;
    }

    public void setSpriteOffsetY(int spriteOffsetY) {
        this.spriteOffsetY = spriteOffsetY;
    }

    public int getSpriteWidth() {
        return spriteWidth;
    }

    public void setSpriteWidth(int spriteSpriteWidth) {
        this.spriteWidth = spriteSpriteWidth;
    }

    public int getSpriteHeight() {
        return spriteHeight;
    }

    public void setSpriteHeight(int spriteSpriteHeight) {
        this.spriteHeight = spriteSpriteHeight;
    }

    public Shape getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(Shape boundingBox) {
        this.boundingBox = boundingBox;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public SpriteAnimation getAnimation() {
        return animation;
    }

    public void setAnimation(SpriteAnimation animation) {
        this.animation = animation;
    }

    public void moveX(int value, int size) {
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
