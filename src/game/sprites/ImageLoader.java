package game.sprites;


import javafx.scene.image.Image;

public class ImageLoader {
    //player
    public static final Image PLAYER_IMAGE = new Image(ImageLoader.class.getResourceAsStream("/game/resources/survivor-move_handgun.png"));
    public static final Image PLAYER_IMAGE_MACHINE_GUN = new Image(ImageLoader.class.getResourceAsStream("/game/resources/survivor-move_machinegun.png"));

    //Enemies
    public static final Image ZOMBIE_IMAGE = new Image(ImageLoader.class.getResourceAsStream("/game/resources/zombie.png"));

    //Weapons
    public static final Image BULLET_IMAGE = new Image(ImageLoader.class.getResourceAsStream("/game/resources/bullet.png"));
    public static final Image UZI_IMAGE = new Image(ImageLoader.class.getResourceAsStream("/game/resources/uzi.png"));
    public static final Image PISTOL_IMAGE = new Image(ImageLoader.class.getResourceAsStream("/game/resources/pistol.png"));

    //Gui and menus
    public static final Image HEALTH_BAR = new Image(ImageLoader.class.getResourceAsStream("/game/resources/HealthBar.png"));
    public static final Image WEAPON_BAR_BACKGROUND = new Image(ImageLoader.class.getResourceAsStream("/game/resources/WeaponBar_Background.png"));
    public static final Image HEALTH_BAR_BACKGROUND = new Image(ImageLoader.class.getResourceAsStream("/game/resources/healthBar_Background.png"));
    public static final Image MAIN_MENU_IMAGE = new Image(ImageLoader.class.getResourceAsStream("/game/resources/menu_1000_640.jpg"));

    //Levels/blocks
    public static final Image EXIT_SIGN = new Image(ImageLoader.class.getResourceAsStream("/game/resources/exitSign.png"));
    public static final Image MARIO_BLOCKS = new Image(ImageLoader.class.getResourceAsStream("/game/resources/1.png"));

    public static final Image BLOCKS_IMG = new Image(ImageLoader.class.getResourceAsStream("/game/resources/blocks.png"));
    public static final Image DESKS = new Image(ImageLoader.class.getResourceAsStream("/game/resources/desk.png"));
    public static final Image BILLIARD = new Image(ImageLoader.class.getResourceAsStream("/game/resources/billiard.png"));
    public static final Image WINDOWS = new Image(ImageLoader.class.getResourceAsStream("/game/resources/windows.png"));
    public static final Image SINK = new Image(ImageLoader.class.getResourceAsStream("/game/resources/sink.png"));
    public static final Image HOUSE_STUFF = new Image(ImageLoader.class.getResourceAsStream("/game/resources/toilet_table.png"));
    public static final Image BLOOD_TABLE = new Image(ImageLoader.class.getResourceAsStream("/game/resources/blood_table.png"));
    public static final Image TABLE = new Image(ImageLoader.class.getResourceAsStream("/game/resources/table.png"));
    public static final Image COUCH = new Image(ImageLoader.class.getResourceAsStream("/game/resources/couch.png"));

    //Bonus
    public static final Image HEART = new Image(ImageLoader.class.getResourceAsStream("/game/resources/heart.png"));


}
