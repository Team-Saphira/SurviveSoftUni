package game.sprites;


import javafx.scene.image.Image;

public class ImageLoader {
    public static Image zombieImage = new Image(ImageLoader.class.getResourceAsStream("/game/resources/zombie.png"));
    public static Image playerImage = new Image(ImageLoader.class.getResourceAsStream("/game/resources/survivor-move_handgun.png"));
    public static Image healthBar = new Image(ImageLoader.class.getResourceAsStream("/game/resources/HealthBar.png"));
    public static Image healthBarBackground = new Image(ImageLoader.class.getResourceAsStream("/game/resources/HealthBar_Background.png"));
    public static Image bulletImage = new Image(ImageLoader.class.getResourceAsStream("/game/resources/bullet.png"));
    public static Image pistolImage = new Image(ImageLoader.class.getResourceAsStream("/game/resources/pistol.png"));
    public static Image ak47Image = new Image(ImageLoader.class.getResourceAsStream("/game/resources/ak47.png"));
    public static Image uziImage = new Image(ImageLoader.class.getResourceAsStream("/game/resources/uzi.png"));
}
