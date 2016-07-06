package game.level;

import game.Constants;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

import static game.level.Block.*;

public class Level extends Pane {
    static int levelNumber = 0;

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

        levelWidth = levelData.getLevels().get(levelNumber)[0].length() * Constants.BLOCK_SIZE;
        levelHeight = levelData.getLevels().get(levelNumber).length * Constants.BLOCK_SIZE;
        levelBlockWidth = levelData.getLevels().get(levelNumber)[0].length();
        levelBlockHeight = levelData.getLevels().get(levelNumber).length;

        System.out.println(levelBlockHeight +" "+ levelBlockWidth);
        System.out.println(levelHeight+" "+levelWidth);
        levelBlockMatrix = new int[levelBlockWidth][levelBlockHeight];


        for (int i = 0; i < levelData.getLevels().get(levelNumber).length; i++) {
            String line = levelData.getLevels().get(levelNumber)[i];
            for (int j = 0; j < line.length(); j++) {
                levelBlockMatrix[j][i] = Integer.parseInt("" + line.charAt(j));
                switch (line.charAt(j)) {
//                    case '0':
//                        break;
                    case '2':
                        Block exit = new Block(BlockType.EXIT, j * Constants.BLOCK_SIZE, i * Constants.BLOCK_SIZE);
                        passableBlocks.add(exit);
                        passableBlockBBoxes.add(exit.getBlockBBox());
                        break;
                    case '3':
                        Block platformFloor = new Block(BlockType.PLATFORM, j * Constants.BLOCK_SIZE, i * Constants.BLOCK_SIZE);
                        impassableBlocks.add(platformFloor);
                        impassableBlockBBoxes.add(platformFloor.getBlockBBox());
                        break;
                    case '4':
                        Block brick = new Block(BlockType.BRICK, j * Constants.BLOCK_SIZE, i * Constants.BLOCK_SIZE);
                        destructibleBlocks.add(brick);
                        destructibleBlockBBoxes.add(brick.getBlockBBox());
                        break;
                }
            }
        }
    }
}
