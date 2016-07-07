package game.level;

import game.Constants;
import game.sprites.ImageLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;


public class Block extends Pane {

    //IB changed tiles with mario theme tiles just to test breaking walls
    private ImageView element;
    private Shape blockBBox;
    private BlockType blockType;

    public enum BlockType {
        PLATFORM, BRICK, EXIT, BLOOD_TABLE_1, BLOOD_TABLE_2, DESK_1, DESK_2, DESK_3, DESK_4, BILLIARD, WINDOWS,
        SINK, TOILET, COUCH_1, COUCH_2, COUCH_3, COUCH_4, TABLE_1, TABLE_2
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
                this.element = new ImageView(ImageLoader.exitSign);
                this.element.setViewport(new Rectangle2D(0,0,32,32));
                this.element.setFitWidth(Constants.BLOCK_SIZE);
                this.element.setFitHeight(Constants.BLOCK_SIZE);
                break;
            case PLATFORM:
                this.element = new ImageView(ImageLoader.blocksImg);
                this.element.setViewport(new Rectangle2D(160, 224, 16, 16));
                this.element.setFitWidth(Constants.BLOCK_SIZE);
                this.element.setFitHeight(Constants.BLOCK_SIZE);
                break;
            case BRICK:
                //this.element = new ImageView(ImageLoader.marioBlocks);
                this.element = new ImageView(ImageLoader.blocksImg);
                this.element.setViewport(new Rectangle2D(320, 192, 16, 16));
                this.element.setFitWidth(Constants.BLOCK_SIZE);
                this.element.setFitHeight(Constants.BLOCK_SIZE);
                break;
            case BLOOD_TABLE_1:
                this.element = new ImageView(ImageLoader.bloodTable);
                this.element.setFitWidth(Constants.BLOCK_SIZE);
                this.element.setFitHeight(Constants.BLOCK_SIZE);
                this.element.setViewport(new Rectangle2D(185, 100, 20, 30));
                break;
            case BLOOD_TABLE_2:
                this.element = new ImageView(ImageLoader.bloodTable);
                this.element.setFitWidth(Constants.BLOCK_SIZE);
                this.element.setFitHeight(Constants.BLOCK_SIZE);
                this.element.setViewport(new Rectangle2D(205, 100, 20, 30));
                break;
            case DESK_1:
                this.element = new ImageView(ImageLoader.desks);
                this.element.setFitWidth(Constants.BLOCK_SIZE);
                this.element.setFitHeight(Constants.BLOCK_SIZE);
                this.element.setViewport(new Rectangle2D(395, 12, 65, 95));
                break;
            case DESK_2:
                this.element = new ImageView(ImageLoader.desks);
                this.element.setFitWidth(Constants.BLOCK_SIZE);
                this.element.setFitHeight(Constants.BLOCK_SIZE);
                this.element.setViewport(new Rectangle2D(395, 107, 65, 95));
                break;
            case DESK_3:
                this.element = new ImageView(ImageLoader.desks);
                this.element.setFitWidth(Constants.BLOCK_SIZE);
                this.element.setFitHeight(Constants.BLOCK_SIZE);
                this.element.setViewport(new Rectangle2D(10, 120, 96, 80));
                break;
            case DESK_4:
                this.element = new ImageView(ImageLoader.desks);
                this.element.setFitWidth(Constants.BLOCK_SIZE);
                this.element.setFitHeight(Constants.BLOCK_SIZE);
                this.element.setViewport(new Rectangle2D(106, 120, 96, 80));
                break;
            case BILLIARD:
                this.element = new ImageView(ImageLoader.billiard);
                this.element.setFitWidth(Constants.BLOCK_SIZE * 3);
                this.element.setFitHeight(Constants.BLOCK_SIZE * 2);
                this.element.setViewport(new Rectangle2D(0, 0, 145, 105));
                break;
            case SINK:
                this.element = new ImageView(ImageLoader.sink);
                this.element.setFitWidth(Constants.BLOCK_SIZE);
                this.element.setFitHeight(Constants.BLOCK_SIZE);
                this.element.setViewport(new Rectangle2D(200, 80, 25, 50));
                break;
            case WINDOWS:
                this.element = new ImageView(ImageLoader.windows);
                this.element.setFitWidth(Constants.BLOCK_SIZE);
                this.element.setFitHeight(Constants.BLOCK_SIZE);
                this.element.setViewport(new Rectangle2D(292, 35, 90, 45));
                break;
            case COUCH_1:
                this.element = new ImageView(ImageLoader.couch);
                this.element.setFitWidth(Constants.BLOCK_SIZE);
                this.element.setFitHeight(Constants.BLOCK_SIZE);
                this.element.setViewport(new Rectangle2D(383, 128, 34, 32));
                break;
            case COUCH_2:
                this.element = new ImageView(ImageLoader.couch);
                this.element.setFitWidth(Constants.BLOCK_SIZE);
                this.element.setFitHeight(Constants.BLOCK_SIZE);
                this.element.setViewport(new Rectangle2D(417, 128, 34, 32));
                break;
            case COUCH_3:
                this.element = new ImageView(ImageLoader.couch);
                this.element.setFitWidth(Constants.BLOCK_SIZE);
                this.element.setFitHeight(Constants.BLOCK_SIZE);
                this.element.setViewport(new Rectangle2D(383, 164, 34, 32));
                break;
            case COUCH_4:
                this.element = new ImageView(ImageLoader.couch);
                this.element.setFitWidth(Constants.BLOCK_SIZE);
                this.element.setFitHeight(Constants.BLOCK_SIZE);
                this.element.setViewport(new Rectangle2D(417, 164, 34, 32));
                break;
            case TOILET:
                this.element = new ImageView(ImageLoader.houseStuff);
                this.element.setFitWidth(Constants.BLOCK_SIZE);
                this.element.setFitHeight(Constants.BLOCK_SIZE);
                this.element.setViewport(new Rectangle2D(780, 965, 190, 185));
                break;
            case TABLE_1:
                this.element = new ImageView(ImageLoader.table);
                this.element.setFitWidth(Constants.BLOCK_SIZE);
                this.element.setFitHeight(Constants.BLOCK_SIZE);
                this.element.setViewport(new Rectangle2D(295, 510, 85, 95));
                break;
            case TABLE_2:
                this.element = new ImageView(ImageLoader.table);
                this.element.setFitWidth(Constants.BLOCK_SIZE);
                this.element.setFitHeight(Constants.BLOCK_SIZE);
                this.element.setViewport(new Rectangle2D(380, 510, 85, 95));
                break;
        }

        this.getChildren().add(element);
        this.getChildren().add(blockBBox);

    }

    public Shape getBlockBBox() {
        return this.blockBBox;
    }

    public BlockType getBlockType() {
        return this.blockType;
    }
}
