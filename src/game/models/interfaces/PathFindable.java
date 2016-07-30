package game.models.interfaces;

public interface PathFindable {
    void updatePath(int levelWidth, int levelHeight, int playerX, int playerY, int zombieX, int zombieY, int[][] matrix);
}
