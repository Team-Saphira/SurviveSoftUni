package game.level.interfaces;

import game.Content;
import javafx.stage.Stage;

public interface LevelManageable {

    void changeLevel();

    void setConfigurables(Content content, Stage stage);
}
