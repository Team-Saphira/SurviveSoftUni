package game.level;

import game.Constants;
import game.sprites.ImageLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;


public class Block extends Pane {

    //IB changed tiles with mario theme tiles just to test breaking walls
    private Image blocksImg;
    private ImageView block;
    private Shape blockBBox;
    private BlockType blockType;

    public enum BlockType {
        PLATFORM, BRICK, EXIT
    }

    public Block(BlockType blockType, int x, int y) {

        this.setTranslateX(x);
        this.setTranslateY(y);
        this.blockBBox = new Rectangle(Constants.BLOCK_SIZE, Constants.BLOCK_SIZE);
        this.blockBBox.setTranslateX(x);
        this.blockBBox.setTranslateY(y);
        this.blockBBox.setOpacity(0);
        this.blockType = blockType;

        switch (this.blockType) {
            case EXIT:
                this.block = new ImageView(ImageLoader.exitSign);
                this.block.setViewport(new Rectangle2D(0,0,32,32));
                this.block.setFitWidth(Constants.BLOCK_SIZE);
                this.block.setFitHeight(Constants.BLOCK_SIZE);
            case PLATFORM:
                this.block = new ImageView(ImageLoader.marioBlocks);
                this.block.setViewport(new Rectangle2D(0, 0, 16, 16));
                this.block.setFitWidth(Constants.BLOCK_SIZE);
                this.block.setFitHeight(Constants.BLOCK_SIZE);
                break;
            case BRICK:
                this.block = new ImageView(ImageLoader.marioBlocks);
                this.block.setViewport(new Rectangle2D(16, 0, 16, 16));
                this.block.setFitWidth(Constants.BLOCK_SIZE);
                this.block.setFitHeight(Constants.BLOCK_SIZE);
                break;
        }

        this.getChildren().add(block);
        this.getChildren().add(blockBBox);

    }

    public Shape getBlockBBox() {
        return this.blockBBox;
    }

    public BlockType getBlockType() {
        return this.blockType;
    }
}
