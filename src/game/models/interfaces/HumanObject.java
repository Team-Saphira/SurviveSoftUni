package game.models.interfaces;

import game.sprites.SpriteAnimation;
import javafx.geometry.Bounds;
import javafx.scene.shape.Shape;

public interface HumanObject {

    int getSpriteCount();

    int getSpriteColumns();

    int getSpriteOffsetX();

    int getSpriteOffsetY();

    int getSpriteWidth();

    int getSpriteHeight();

    Shape getBoundingBox();

    boolean getIsInCollision();

    int getPosX();

    int getPosY();

    SpriteAnimation getAnimation();

    int getObjectSize();

    Shape calcBoundingBox(int size);

    void changeSpriteCount(int spriteCount);

    void changeSpriteColumns(int spriteColumns);

    void changeSpriteOffsetX(int spriteOffsetX);

    void changeSpriteOffsetY(int spriteOffsetY);

    void changeSpriteWidth(int spriteSpriteWidth);

    void changeSpriteHeight(int spriteSpriteHeight);

    void changeBoundingBox(Shape boundingBox);

    void isInCollision(boolean inCollision);

    void changeId(int id);

    void changePosXGrid(int posX);

    void changePosYGrid(int posY);

    void changeAnimation(SpriteAnimation animation);

    void changeObjectSize(int objectSize);

    //JavaFX methods
    Bounds getBoundsInLocal();

    Bounds localToParent(Bounds localBounds);

    Bounds getBoundsInParent();

    double getTranslateX();

    void setTranslateX(double value);

    double getTranslateY();

    void setTranslateY(double value);
}
