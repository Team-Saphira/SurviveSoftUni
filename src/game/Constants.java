package game;

public class Constants {
    public static final int PLAYER_VELOCITY = 4;
    public static final int PLAYER_SIZE = 40;
    public static final int ZOMBIE_VELOCITY = 1;
    public static final int ZOMBIE_SPAWN_NUM = 50;
    public static final int ZOMBIE_SIZE = 35;
    public static final int ZOMBIE_HEALTH = 3;

    public static final int BLOCK_SIZE = 45;

    public static final double DISPLAY_WIDTH = 1000;
    public static final double DISPLAY_HEIGHT = 640;

    public static final boolean RANDOMISE_LEVELS = true;
    public static final int LEVEL_WIDTH = 30;
    public static final int LEVEL_HEIGHT = 30;

    public static final int MAX_DEQUEUE_SIZE = 15; //for A*, Distance zombies start honing in on enemy
    public static final int V_H_COST = 10; //for a*, heuristic cost for neighbour cells
    public static final char[] CREATURE_DIRECTIONS = {'L', 'R', 'U', 'D'};
}
