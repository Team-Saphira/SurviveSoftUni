package game.level;

import game.Constants;
import game.level.enums.BlockType;
import game.level.enums.DestructionType;
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

        System.out.println(levelBlockHeight + " " + levelBlockWidth);
        System.out.println(levelHeight + " " + levelWidth);
        levelBlockMatrix = new int[levelBlockWidth][levelBlockHeight];


        for (int i = 0; i < levelData.getLevels().get(levelNumber).length; i++) {
            String line[] = levelData.getLevels().get(levelNumber)[i].split("\\s+");
            for (int j = 0; j < line.length; j++) {
                levelBlockMatrix[j][i] = Integer.parseInt(line[j]);

                int id = Integer.valueOf(line[j]);
                BlockType newBlockType = null;

                if (id ==1 || id==0) {
                    continue;
                }

                for (BlockType blockType : BlockType.values()) {
                    if (blockType.getId() == id) {
                        newBlockType = blockType;
                        break;
                    }
                }

                Block block = new Block(newBlockType, j * Constants.BLOCK_SIZE, i * Constants.BLOCK_SIZE);
                if (block.getBlockType().getDestructionType()== DestructionType.IMPASSABLE) {
                    impassableBlocks.add(block);
                    impassableBlockBBoxes.add(block.getBlockBBox());
                } else if (block.getBlockType().getDestructionType()== DestructionType.PASSABLE) {
                    passableBlocks.add(block);
                    passableBlockBBoxes.add(block.getBlockBBox());
                } else {
                    destructibleBlocks.add(block);
                    destructibleBlockBBoxes.add(block.getBlockBBox());
                }

//                    case "21":
//                        Block office1 = new Block(BlockType.OFFICE_1, j * Constants.BLOCK_SIZE, i * Constants
// .BLOCK_SIZE);
//                        impassableBlocks.add(office1);
//                        boxes.add(office1.getBlockBBox());
//                        break;
//                    case "22":
//                        Block office2 = new Block(BlockType.OFFICE_2, j * Constants.BLOCK_SIZE, i * Constants
// .BLOCK_SIZE);
//                        impassableBlocks.add(office2);
//                        boxes.add(office2.getBlockBBox());
//                        break;
//                    case "23":
//                        Block office3 = new Block(BlockType.OFFICE_3, j * Constants.BLOCK_SIZE, i * Constants
// .BLOCK_SIZE);
//                        impassableBlocks.add(office3);
//                        boxes.add(office3.getBlockBBox());
//                        break;
//                    case "24":
//                        Block office4 = new Block(BlockType.OFFICE_4, j * Constants.BLOCK_SIZE, i * Constants
// .BLOCK_SIZE);
//                        impassableBlocks.add(office4);
//                        boxes.add(office4.getBlockBBox());
//                        break;
            }
        }
    }
}

