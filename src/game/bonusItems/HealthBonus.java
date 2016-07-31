package game.bonusItems;

import game.Constants;
import game.bonusItems.interfaces.Bonus;
import game.sprites.ImageLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HealthBonus extends Pane implements Bonus {

    private Image image;
    private ImageView imageView;
    private int posX;
    private int posY;
    private Rectangle boundingBox;

    public HealthBonus(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        this.imageView = new ImageView(ImageLoader.HEART);
        this.imageView.setFitHeight(Constants.BONUS_IMAGE_HEIGHT);
        this.imageView.setFitWidth(Constants.BONUS_IMAGE_WIDTH);
        this.imageView.setTranslateX(this.getPosX());
        this.imageView.setTranslateY(this.getPosY());
        this.boundingBox = new Rectangle(this.getImageView().getFitWidth(), this.getImageView().getFitHeight());
        this.boundingBox.setTranslateX(this.posX);
        this.boundingBox.setTranslateY(this.posY);
        this.boundingBox.setFill(Color.BLACK);

        this.boundingBox.setOpacity(0);

        this.getChildren().addAll(this.imageView, this.boundingBox);
    }

    @Override
    public Rectangle getBoundingBox() {
        return this.boundingBox;
    }

    @Override
    public ImageView getImageView() {
        return this.imageView;
    }

    @Override
    public int getPosX() {
        return this.posX;
    }

    @Override
    public int getPosY() {
        return this.posY;
    }

    @Override
    public Image getImage() {
        return this.image;
    }

    private void setPosX(int posX) {
        this.posX = posX;
    }

    private void setPosY(int posY) {
        this.posY = posY;
    }
}
