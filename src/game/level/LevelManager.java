package game.level;

import game.Content;
import javafx.stage.Stage;

public class LevelManager {
    private Content content;
    private Stage stage;

    public LevelManager() {
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public void changeLevel() {

        this.clearLevelData();

        this.getContent().loadNextLevel();
        this.getContent().getRoot().setLayoutX(0);
        this.getContent().getRoot().setLayoutY(0);
        this.getStage().show();

    }

    private void clearLevelData() {

        Level.impassableBlocks.clear();
        Level.impassableBlockBBoxes.clear();
        Level.passableBlocks.clear();
        Level.passableBlockBBoxes.clear();
        Level.destructibleBlocks.clear();
        Level.destructibleBlockBBoxes.clear();

        this.getContent().getRoot().getChildren().clear();
    }


}
