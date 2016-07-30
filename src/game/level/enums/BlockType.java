package game.level.enums;

import game.sprites.ImageLoader;
import javafx.scene.image.Image;

public enum BlockType {

    PLATFORM(ImageLoader.BLOCKS_IMG, 160, 224, 16, 16),
    BRICK(ImageLoader.BLOCKS_IMG, 320, 192, 16, 16),
    EXIT(ImageLoader.EXIT_SIGN, 0, 0, 32, 32),
    BLOOD_TABLE_1(ImageLoader.BLOOD_TABLE, 185, 100, 20, 30),
    BLOOD_TABLE_2(ImageLoader.BLOOD_TABLE, 205, 100, 20, 30),
    DESK_1(ImageLoader.DESKS, 395, 12, 65, 95),
    DESK_2(ImageLoader.DESKS, 395, 107, 65, 95),
    DESK_3(ImageLoader.DESKS, 10, 120, 96, 80),
    DESK_4(ImageLoader.DESKS, 106, 120, 96, 80),
    BILLIARD(ImageLoader.BILLIARD, 0, 0, 145, 105),
    WINDOWS(ImageLoader.WINDOWS, 292, 35, 90, 45),
    SINK(ImageLoader.SINK, 200, 80, 25, 50),
    TOILET(ImageLoader.HOUSE_STUFF, 780, 965, 190, 185),
    COUCH_1(ImageLoader.COUCH, 383, 128, 34, 32),
    COUCH_2(ImageLoader.COUCH, 417, 128, 34, 32),
    COUCH_3(ImageLoader.COUCH, 383, 164, 34, 32),
    COUCH_4(ImageLoader.COUCH, 417, 164, 34, 32),
    TABLE_1(ImageLoader.TABLE, 295, 510, 85, 95),
    TABLE_2(ImageLoader.TABLE, 380, 510, 85, 95);

    private Image blockImage;
    private int offsetX;
    private int offsetY;
    private int sizeX;
    private int sizeY;

    BlockType(Image image, int offsetX, int offsetY, int sizeX, int sizeY) {
        this.blockImage = image;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }


    public Image getBlockImage() {
        return this.blockImage;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }
}