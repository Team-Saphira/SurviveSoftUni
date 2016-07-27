package game.sprites;


import javafx.scene.image.Image;

public class ImageLoader {
    //player
    public static Image playerImage = new Image(ImageLoader.class.getResourceAsStream("/game/resources/survivor-move_handgun.png"));
    public static Image playerImageMachineGun = new Image(ImageLoader.class.getResourceAsStream("/game/resources/survivor-move_machinegun.png"));

    //Enemies
    public static Image zombieImage = new Image(ImageLoader.class.getResourceAsStream("/game/resources/zombie.png"));

    //Weapons
    public static Image bulletImage = new Image(ImageLoader.class.getResourceAsStream("/game/resources/bullet.png"));
    public static Image ak47Image = new Image(ImageLoader.class.getResourceAsStream("/game/resources/ak47.png"));
    public static Image uziImage = new Image(ImageLoader.class.getResourceAsStream("/game/resources/uzi.png"));
    public static Image pistolImage = new Image(ImageLoader.class.getResourceAsStream("/game/resources/pistol.png"));

    //Gui and menus
    public static Image healthBar = new Image(ImageLoader.class.getResourceAsStream("/game/resources/healthBar.png"));
    public static Image weaponBarBackground = new Image(ImageLoader.class.getResourceAsStream("/game/resources/WeaponBar_Background.png"));
    public static Image healthBarBackground = new Image(ImageLoader.class.getResourceAsStream("/game/resources/healthBar_Background.png"));
    public static Image mainMenuImage = new Image(ImageLoader.class.getResourceAsStream("/game/resources/menu_1000_640.jpg"));

    //Levels/blocks
    public static Image exitSign = new Image(ImageLoader.class.getResourceAsStream("/game/resources/exitSign.png"));
    public static Image marioBlocks = new Image(ImageLoader.class.getResourceAsStream("/game/resources/1.png"));

    public static Image blocksImg = new Image(ImageLoader.class.getResourceAsStream("/game/resources/blocks.png"));
    public static Image desks = new Image(ImageLoader.class.getResourceAsStream("/game/resources/desk.png"));
    public static Image billiard = new Image(ImageLoader.class.getResourceAsStream("/game/resources/billiard.png"));
    public static Image windows = new Image(ImageLoader.class.getResourceAsStream("/game/resources/windows.png"));
    public static Image sink = new Image(ImageLoader.class.getResourceAsStream("/game/resources/sink.png"));
    public static Image houseStuff = new Image(ImageLoader.class.getResourceAsStream("/game/resources/toilet_table.png"));
    public static Image bloodTable = new Image(ImageLoader.class.getResourceAsStream("/game/resources/blood_table.png"));
    public static Image table = new Image(ImageLoader.class.getResourceAsStream("/game/resources/table.png"));
    public static Image couch = new Image(ImageLoader.class.getResourceAsStream("/game/resources/couch.png"));


    //Bonus
    public static Image heart = new Image(ImageLoader.class.getResourceAsStream("/game/resources/heart.png"));


}
