package game.models;

import game.sprites.SpriteAnimation;
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


    private int objectSize;

    // for object ID
    private int id;

    //for random movement and others TODO: move to enemy class? Should also change collision manager in this case
    private boolean isInCollision;

    //the position on the matrix
    private int posX;
    private int posY;

    private SpriteAnimation animation;

    protected HumanObject(int setTranslateX, int setTranslateY){
        this.setTranslateX(setTranslateX);
        this.setTranslateY(setTranslateY);
        this.setIsInCollision(true);
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

    public boolean getIsInCollision() {
        return isInCollision;
    }

    public void setIsInCollision(boolean inCollision) {
        isInCollision = inCollision;
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


    public int getObjectSize() {
        return objectSize;
    }

    public void setObjectSize(int objectSize) {
        this.objectSize = objectSize;
    }

    protected Shape calcBoundingBox(int size) {
        Circle circleBBox = new Circle(this.getTranslateX() + size/2, this.getTranslateY() + size/2, size/2-4);
        circleBBox.setOpacity(0);

        return circleBBox;
    }
}
