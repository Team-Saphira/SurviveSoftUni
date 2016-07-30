package game.level;

import game.Constants;
import game.level.enums.BlockType;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class Level extends Pane {
    public static int levelNumber = 0;
    public static boolean shouldChangeLevel = false;

    private static int levelWidth;
    private static int levelHeight;
    public static int levelBlockWidth;
    public static int levelBlockHeight;
    public static int[][] levelBlockMatrix;
    public static ArrayList<Block> impassableBlocks = new ArrayList<>();
    public static ArrayList<Shape> impassableBlockBBoxes = new ArrayList<>();
    public static ArrayList<Block> destructibleBlocks = new ArrayList<>();
    public static ArrayList<Shape> destructibleBlockBBoxes = new ArrayList<>();
    public static ArrayList<Block> passableBlocks = new ArrayList<>();
    public static ArrayList<Shape> passableBlockBBoxes = new ArrayList<>();

    public static int getLevelWidth() {
        return levelWidth;
    }

    public static int getLevelHeight() {
        return levelHeight;
    }

    public static void initLevel(LevelData newLevelData) {
        LevelData levelData = newLevelData;

        levelWidth = levelData.getLevels().get(levelNumber)[0].split("\\s+").length * Constants.BLOCK_SIZE;
        levelHeight = levelData.getLevels().get(levelNumber).length * Constants.BLOCK_SIZE;
        levelBlockWidth = levelData.getLevels().get(levelNumber)[0].split("\\s+").length;
        levelBlockHeight = levelData.getLevels().get(levelNumber).length;

        System.out.println(levelBlockHeight +" "+ levelBlockWidth);
        System.out.println(levelHeight+" "+levelWidth);
        levelBlockMatrix = new int[levelBlockWidth][levelBlockHeight];


        for (int i = 0; i < levelData.getLevels().get(levelNumber).length; i++) {
            String line[] = levelData.getLevels().get(levelNumber)[i].split("\\s+");
            for (int j = 0; j < line.length; j++) {
                levelBlockMatrix[j][i] = Integer.parseInt(line[j]);
                switch (line[j]) {
//                    case '0':
//                        break;
                    case "2":
                        Block exit = new Block(BlockType.EXIT, j * Constants.BLOCK_SIZE, i * Constants.BLOCK_SIZE);
                        passableBlocks.add(exit);
                        passableBlockBBoxes.add(exit.getBlockBBox());
                        break;
                    case "3":
                        Block platformFloor = new Block(BlockType.PLATFORM, j * Constants.BLOCK_SIZE, i * Constants.BLOCK_SIZE);
                        impassableBlocks.add(platformFloor);
                        impassableBlockBBoxes.add(platformFloor.getBlockBBox());
                        break;
                    case "4":
                        Block brick = new Block(BlockType.BRICK, j * Constants.BLOCK_SIZE, i * Constants.BLOCK_SIZE);
                        destructibleBlocks.add(brick);
                        destructibleBlockBBoxes.add(brick.getBlockBBox());
                        break;
                    case "5":
                        Block desk1 = new Block(BlockType.DESK_1, j * Constants.BLOCK_SIZE, i * Constants.BLOCK_SIZE);
                        impassableBlocks.add(desk1);
                        impassableBlockBBoxes.add(desk1.getBlockBBox());
                        break;
                    case "6":
                        Block desk2 = new Block(BlockType.DESK_2, j * Constants.BLOCK_SIZE, i * Constants.BLOCK_SIZE);
                        impassableBlocks.add(desk2);
                        impassableBlockBBoxes.add(desk2.getBlockBBox());
                        break;
                    case "7":
                        Block desk3 = new Block(BlockType.DESK_3, j * Constants.BLOCK_SIZE, i * Constants.BLOCK_SIZE);
                        impassableBlocks.add(desk3);
                        impassableBlockBBoxes.add(desk3.getBlockBBox());
                        break;
                    case "8":
                        Block desk4 = new Block(BlockType.DESK_4, j * Constants.BLOCK_SIZE, i * Constants.BLOCK_SIZE);
                        impassableBlocks.add(desk4);
                        impassableBlockBBoxes.add(desk4.getBlockBBox());
                        break;
                    case "9":
                        Block billiard = new Block(BlockType.BILLIARD, j * Constants.BLOCK_SIZE, i * Constants.BLOCK_SIZE);
                        impassableBlocks.add(billiard);
                        impassableBlockBBoxes.add(billiard.getBlockBBox());
                        break;
                    case "10":
                        Block windows = new Block(BlockType.WINDOWS, j * Constants.BLOCK_SIZE, i * Constants.BLOCK_SIZE);
                        impassableBlocks.add(windows);
                        impassableBlockBBoxes.add(windows.getBlockBBox());
                        break;
                    case "11":
                        Block bloodTable1 = new Block(BlockType.BLOOD_TABLE_1, j * Constants.BLOCK_SIZE, i * Constants.BLOCK_SIZE);
                        impassableBlocks.add(bloodTable1);
                        impassableBlockBBoxes.add(bloodTable1.getBlockBBox());
                        break;
                    case "12":
                        Block bloodTable2 = new Block(BlockType.BLOOD_TABLE_2, j * Constants.BLOCK_SIZE, i * Constants.BLOCK_SIZE);
                        impassableBlocks.add(bloodTable2);
                        impassableBlockBBoxes.add(bloodTable2.getBlockBBox());
                        break;
                    case "13":
                        Block sink = new Block(BlockType.SINK, j * Constants.BLOCK_SIZE, i * Constants.BLOCK_SIZE);
                        impassableBlocks.add(sink);
                        impassableBlockBBoxes.add(sink.getBlockBBox());
                        break;
                    case "14":
                        Block couch1 = new Block(BlockType.COUCH_1, j * Constants.BLOCK_SIZE, i * Constants.BLOCK_SIZE);
                        impassableBlocks.add(couch1);
                        impassableBlockBBoxes.add(couch1.getBlockBBox());
                        break;
                    case "15":
                        Block couch2 = new Block(BlockType.COUCH_2, j * Constants.BLOCK_SIZE, i * Constants.BLOCK_SIZE);
                        impassableBlocks.add(couch2);
                        impassableBlockBBoxes.add(couch2.getBlockBBox());
                        break;
                    case "16":
                        Block couch3 = new Block(BlockType.COUCH_3, j * Constants.BLOCK_SIZE, i * Constants.BLOCK_SIZE);
                        impassableBlocks.add(couch3);
                        impassableBlockBBoxes.add(couch3.getBlockBBox());
                        break;
                    case "17":
                        Block couch4 = new Block(BlockType.COUCH_4, j * Constants.BLOCK_SIZE, i * Constants.BLOCK_SIZE);
                        impassableBlocks.add(couch4);
                        impassableBlockBBoxes.add(couch4.getBlockBBox());
                        break;
                    case "18":
                        Block toilet = new Block(BlockType.TOILET, j * Constants.BLOCK_SIZE, i * Constants.BLOCK_SIZE);
                        impassableBlocks.add(toilet);
                        impassableBlockBBoxes.add(toilet.getBlockBBox());
                        break;
                    case "19":
                        Block table1 = new Block(BlockType.TABLE_1, j * Constants.BLOCK_SIZE, i * Constants.BLOCK_SIZE);
                        impassableBlocks.add(table1);
                        impassableBlockBBoxes.add(table1.getBlockBBox());
                        break;
                    case "20":
                        Block table2 = new Block(BlockType.TABLE_2, j * Constants.BLOCK_SIZE, i * Constants.BLOCK_SIZE);
                        impassableBlocks.add(table2);
                        impassableBlockBBoxes.add(table2.getBlockBBox());
                        break;
//                    case "21":
//                        Block office1 = new Block(BlockType.OFFICE_1, j * Constants.BLOCK_SIZE, i * Constants.BLOCK_SIZE);
//                        impassableBlocks.add(office1);
//                        boxes.add(office1.getBlockBBox());
//                        break;
//                    case "22":
//                        Block office2 = new Block(BlockType.OFFICE_2, j * Constants.BLOCK_SIZE, i * Constants.BLOCK_SIZE);
//                        impassableBlocks.add(office2);
//                        boxes.add(office2.getBlockBBox());
//                        break;
//                    case "23":
//                        Block office3 = new Block(BlockType.OFFICE_3, j * Constants.BLOCK_SIZE, i * Constants.BLOCK_SIZE);
//                        impassableBlocks.add(office3);
//                        boxes.add(office3.getBlockBBox());
//                        break;
//                    case "24":
//                        Block office4 = new Block(BlockType.OFFICE_4, j * Constants.BLOCK_SIZE, i * Constants.BLOCK_SIZE);
//                        impassableBlocks.add(office4);
//                        boxes.add(office4.getBlockBBox());
//                        break;
                }
            }
        }
    }
}
