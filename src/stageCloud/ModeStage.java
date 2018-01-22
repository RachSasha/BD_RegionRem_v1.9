package stageCloud;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by alien on 25.09.2017.
 */
public class ModeStage
{
    private static Stage stage;
    private Parent fxml;

    public ModeStage() {}

    public void start()
    {
        System.out.println("Mode stage start");
        initLoader();
        showDialog();
    }

    public void initLoader()
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/stageCloud/fxml/mode.fxml"));
            fxml = fxmlLoader.load();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }

    public void showDialog()
    {
        stage.setResizable(false);
        stage.setTitle("Выбор режима");
        stage.setScene(new Scene(fxml));
        stage.show();
    }

    public static void close()
    {
        stage.close();
    }

    public static void hide()
    {
        stage.hide();
    }

    public void show()
    {
        stage.show();
    }

    public static void setStage(Stage stage) {
        ModeStage.stage = stage;
    }
}
