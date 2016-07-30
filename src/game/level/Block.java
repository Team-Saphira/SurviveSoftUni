package game.level;

import game.Constants;
import game.level.enums.BlockType;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;


public class Block extends Pane {

    private Shape blockBBox;
    private BlockType blockType;
    private ImageView imageView;

    public Block(BlockType blockType, int x, int y) {

        this.blockType = blockType;
        this.imageView = new ImageView(this.blockType.getBlockImage());
        this.configureImageViewPort();
        this.setTranslateX(x);
        this.setTranslateY(y);
        this.blockBBox = this.createBlockBoundingBox(x, y);

        this.getChildren().add(this.imageView);
        this.getChildren().add(this.getBlockBBox());
    }

    private void configureImageViewPort() {
        int offsetX = this.blockType.getOffsetX();
        int offsetY = this.blockType.getOffsetY();
        int sizeX = this.blockType.getSizeX();
        int sizeY = this.blockType.getSizeY();

        this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, sizeX, sizeY));
        this.imageView.setFitWidth(Constants.BLOCK_SIZE);
        this.imageView.setFitHeight(Constants.BLOCK_SIZE);
    }

    public Shape getBlockBBox() {
        return this.blockBBox;
    }

    public BlockType getBlockType() {
        return this.blockType;
    }

    private Shape createBlockBoundingBox(int x, int y) {
        Shape bbox = new Rectangle(Constants.BLOCK_SIZE, Constants.BLOCK_SIZE);
        bbox.setTranslateX(x);
        bbox.setTranslateY(y);
        bbox.setOpacity(0);

        return bbox;
    }
}
