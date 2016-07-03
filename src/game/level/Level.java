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
    public static ArrayList<Block> platforms = new ArrayList<>();
    public static ArrayList<Shape> boxes = new ArrayList<>();
    
    public static int getLevelWidth() {
        return levelWidth;
    }

    public static int getLevelHeight() {
        return levelHeight;
    }

    public static void initLevel() {
        LevelData levelData = new LevelData();

        levelWidth = levelData.getLevels()[levelNumber][0].length() * Constants.BLOCK_SIZE;
        levelHeight = levelData.getLevels()[levelNumber].length * Constants.BLOCK_SIZE;
        levelBlockWidth = levelData.getLevels()[levelNumber][0].length();
        levelBlockHeight = levelData.getLevels()[levelNumber].length;

        System.out.println(levelBlockHeight +" "+ levelBlockWidth);
        System.out.println(levelHeight+" "+levelWidth);
        levelBlockMatrix = new int[levelBlockWidth][levelBlockHeight];


        for (int i = 0; i < levelData.getLevels()[levelNumber].length; i++) {
            String line = levelData.getLevels()[levelNumber][i];
            for (int j = 0; j < line.length(); j++) {
                levelBlockMatrix[j][i] = Integer.parseInt("" + line.charAt(j));
                switch (line.charAt(j)) {
//                    case '0':
//                        break;
                    case '1':
                        Block platformFloor = new Block(BlockType.PLATFORM, j * Constants.BLOCK_SIZE, i * Constants.BLOCK_SIZE);
                        platforms.add(platformFloor);
                        boxes.add(platformFloor.getBlockBBox());
                        break;
                    case '2':
                        Block brick = new Block(BlockType.BRICK, j * Constants.BLOCK_SIZE, i * Constants.BLOCK_SIZE);
                        platforms.add(brick);
                        boxes.add(brick.getBlockBBox());
                        break;
                }
            }
        }
    }
}
