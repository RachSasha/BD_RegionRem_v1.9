package stageCloud;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by alien on 25.09.2017.
 */
public class MainStageShow
{
    private Stage stage;
    private Parent fxml;

    public MainStageShow() {}

    public void start()
    {
        System.out.println("MainStageShow start");
        if (stage == null)
        {
            stage = new Stage();
            initLoader();
            showDialog();
        }
        stage.show();
    }

    public void initLoader()
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/stageCloud/fxml/mainShow.fxml"));
            fxml = fxmlLoader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void showDialog()
    {
        stage.setTitle("Просмотр таблиц");
        stage.setMinHeight(600);
        stage.setMinWidth(900);
        stage.setScene(new Scene(fxml));
    }

    public Stage getStage()
    {
        return stage;
    }
}
