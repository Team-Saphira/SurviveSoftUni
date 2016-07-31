package game.level.interfaces;

import game.level.enums.BlockType;
import javafx.scene.shape.Shape;

public interface Block {

    Shape getBlockBBox();

    BlockType getBlockType();
}
