import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import stageCloud.ModeStage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        ModeStage modeStage = new ModeStage();
        modeStage.setStage(primaryStage);
        modeStage.start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
