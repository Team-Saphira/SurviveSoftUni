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
    public static Image healthBarBackground = new Image(ImageLoader.class.getResourceAsStream("/game/resources/healthBar_Background.png"));
    public static Image mainMenuImage = new Image(ImageLoader.class.getResourceAsStream("/game/resources/menu_1000_640.jpg"));

    //Levels/blocks
    public static Image exitSign = new Image(ImageLoader.class.getResourceAsStream("/game/resources/exitSign.png"));
    public static Image marioBlocks = new Image(ImageLoader.class.getResourceAsStream("/game/resources/1.png"));


}
