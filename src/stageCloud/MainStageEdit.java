package stageCloud;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by alien on 25.09.2017.
 */
public class MainStageEdit
{
    private Stage stage;
    private Parent fxml;

    public MainStageEdit() {}

    public void start()
    {
        System.out.println("MainStageEdit start");
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
            fxmlLoader.setLocation(getClass().getResource("/stageCloud/fxml/mainEdit.fxml"));
            fxml = fxmlLoader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void showDialog()
    {
        stage.setTitle("Редактор таблиц");
        stage.setMinHeight(600);
        stage.setMinWidth(900);
        stage.setScene(new Scene(fxml));
    }

    public Stage getStage()
    {
        return stage;
    }
}
