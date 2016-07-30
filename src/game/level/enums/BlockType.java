package game.level.enums;

import game.sprites.ImageLoader;
import javafx.scene.image.Image;

public enum BlockType {

    PLATFORM(ImageLoader.blocksImg, 160, 224, 16, 16),
    BRICK(ImageLoader.blocksImg, 320, 192, 16, 16),
    EXIT(ImageLoader.exitSign, 0, 0, 32, 32),
    BLOOD_TABLE_1(ImageLoader.bloodTable, 185, 100, 20, 30),
    BLOOD_TABLE_2(ImageLoader.bloodTable, 205, 100, 20, 30),
    DESK_1(ImageLoader.desks, 395, 12, 65, 95),
    DESK_2(ImageLoader.desks, 395, 107, 65, 95),
    DESK_3(ImageLoader.desks, 10, 120, 96, 80),
    DESK_4(ImageLoader.desks, 106, 120, 96, 80),
    BILLIARD(ImageLoader.billiard, 0, 0, 145, 105),
    WINDOWS(ImageLoader.windows, 292, 35, 90, 45),
    SINK(ImageLoader.sink, 200, 80, 25, 50),
    TOILET(ImageLoader.houseStuff, 780, 965, 190, 185),
    COUCH_1(ImageLoader.couch, 383, 128, 34, 32),
    COUCH_2(ImageLoader.couch, 417, 128, 34, 32),
    COUCH_3(ImageLoader.couch, 383, 164, 34, 32),
    COUCH_4(ImageLoader.couch, 417, 164, 34, 32),
    TABLE_1(ImageLoader.table, 295, 510, 85, 95),
    TABLE_2(ImageLoader.table, 380, 510, 85, 95);

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