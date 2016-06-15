package game.Level;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

import static game.Level.Block.*;

public class Level extends Pane {
    static int levelNumber = 0;

    private static int levelWidth;
    private static int levelHeight;
    public static int levelBlockWidth;
    public static int levelBlockHeight;
    public static int[][] levelBlockMatrix;
    public static ArrayList<Block> platforms = new ArrayList<>();
    public static ArrayList<Shape> bboxes = new ArrayList<>();
//    public static Pane gameRoot = new Pane();

    public static int getLevelWidth() {
        return levelWidth;
    }

    public static int getLevelHeight() {
        return levelHeight;
    }

    public static void initLevel() {

        levelWidth = LevelData.levels[levelNumber][0].length() * BLOCK_SIZE;
        levelHeight = LevelData.levels[levelNumber].length * BLOCK_SIZE;
        levelBlockWidth = LevelData.levels[levelNumber][0].length();
        levelBlockHeight = LevelData.levels[levelNumber].length;

        System.out.println(levelBlockHeight +" "+ levelBlockWidth);
        System.out.println(levelHeight+" "+levelWidth);
        levelBlockMatrix = new int[levelBlockHeight][levelBlockWidth];


        for (int i = 0; i < LevelData.levels[levelNumber].length; i++) {
            String line = LevelData.levels[levelNumber][i];
            for (int j = 0; j < line.length(); j++) {
                levelBlockMatrix[i][j] = Integer.parseInt("" + line.charAt(j));
                switch (line.charAt(j)) {
//                    case '0':
//                        break;
                    case '1':
                        Block platformFloor = new Block(BlockType.PLATFORM, j * BLOCK_SIZE, i * BLOCK_SIZE);
                        break;
                    case '2':
                        Block brick = new Block(BlockType.BRICK, j * BLOCK_SIZE, i * BLOCK_SIZE);
                        break;
                }
            }
        }
    }
}
