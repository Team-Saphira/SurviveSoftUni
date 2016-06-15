package game.Level;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;


public class Block extends Pane {
    Image blocksImg = new Image(getClass().getResourceAsStream("res/tile.png"));
    ImageView block;
    Shape blockBBox;

    public static final int BLOCK_SIZE = 45;

    public enum BlockType {
        PLATFORM, BRICK
    }

    public Block(BlockType blockType, int x, int y) {
        block = new ImageView(blocksImg);
        block.setFitWidth(BLOCK_SIZE);
        block.setFitHeight(BLOCK_SIZE);
        setTranslateX(x);
        setTranslateY(y);
        blockBBox = new Rectangle(BLOCK_SIZE, BLOCK_SIZE);
        blockBBox.setTranslateX(x);
        blockBBox.setTranslateY(y);
        blockBBox.setOpacity(0);

//        switch (blockType) {
//            case PLATFORM:
//                block.setViewport(new Rectangle2D(0, 0, 16, 16));
//                break;
//            case BRICK:
//                block.setViewport(new Rectangle2D(16, 0, 16, 16));
//                break;
//
//        }
        getChildren().add(block);
        getChildren().add(blockBBox);
        Level.platforms.add(this);
        Level.bboxes.add(blockBBox);
//        Level.gameRoot.getChildren().add(this);
    }
}
