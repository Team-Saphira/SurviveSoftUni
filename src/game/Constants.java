package game;

public class Constants {

    public static final int SPACING_SIZE = 5;

    //Title Screen Constants
    public static final String TITLE_TEXT_FONT_TYPE = "Tw Cen MT Condensed";
    public static final int TITLE_TEXT_FONT_SIZE = 50;
    public static final int TITLE_RECTANGLE_WIDTH = 640;
    public static final int TITLE_RECTANGLE_HEIGHT = 60;
    public static final int TITLE_RECTANGLE_BORDER_WIDTH = 2;

    //Main Menu Screen Constants
    public static final String MAIN_MENU_TEXT_FONT_TYPE = "Tw Cen MT Condensed";
    public static final int MAIN_MENU_TEXT_FONT_SIZE = 22;
    public static final int MAIN_MENU_RECTANGLE_WIDTH = 200;
    public static final int MAIN_MENU_RECTANGLE_HEIGHT = 30;
    public static final double MAIN_MENU_RECTANGLE_OPACITY = 0.4;

    //Game Over Screen Constants
    public static final String GAME_OVER_TEXT_FONT_TYPE = "Tw Cen MT Condensed";
    public static final int GAME_OVER_TEXT_FONT_SIZE = 50;
    public static final int GAME_OVER_RECTANGLE_WIDTH = 640;
    public static final int GAME_OVER_RECTANGLE_HEIGHT = 70;
    public static final int GAME_OVER_RECTANGLE_BORDER_WIDTH = 2;


    //Player constants
    public static final int PLAYER_VELOCITY = 4;
    public static final int PLAYER_SIZE = 40;

    //Enemy/NPC constants
    public static final int ZOMBIE_VELOCITY = 1;
    public static final int ZOMBIE_SPAWN_NUM = 50;
    public static final int ZOMBIE_SIZE = 35;
    public static final int ZOMBIE_HEALTH = 3;

    //Level/Blocks constants
    public static final int BLOCK_SIZE = 45;

    //Display constants
    public static final double DISPLAY_WIDTH = 1000;
    public static final double DISPLAY_HEIGHT = 640;

    //Terrain Generation constants
    public static final boolean RANDOMISE_LEVELS = true;
    public static final int LEVEL_WIDTH = 30;
    public static final int LEVEL_HEIGHT = 30;

    //Pathfinding constants
    public static final int MAX_DEQUEUE_SIZE = 15; //for A*, Distance zombies start honing in on enemy
    public static final int V_H_COST = 10; //for a*, heuristic cost for neighbour cells
    public static final char[] ENEMY_DIRECTIONS = {'L', 'R', 'U', 'D'};
}
