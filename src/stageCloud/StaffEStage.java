package stageCloud;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by alien on 03.10.2017.
 */
public class StaffEStage
{
    private static Stage stage;
    private Parent fxml;
    private Stage mainStage;
    private static FXMLLoader fxmlLoader;
    private MainStageEdit mainStageEdit = new MainStageEdit();

    public StaffEStage()
    {
    }

    public void initLoader()
    {
        try
        {
            fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/stageCloud/fxml/staffE.fxml"));
            fxml = fxmlLoader.load();
        }
        catch (IOException e )
        {
            e.printStackTrace();
        }
    }

    public void showDialog()
    {
        if(stage==null)
        {
            stage = new Stage();
            stage.setTitle("Редактирование записи");
            stage.setResizable(false);
            System.out.println("1");
            stage.setScene(new Scene(fxml));
            System.out.println("2");
            stage.initModality(Modality.WINDOW_MODAL);
            mainStage = mainStageEdit.getStage();
            stage.initOwner(mainStage);
        }
        stage.showAndWait();
    }

    public static FXMLLoader getFxmlLoader() {
        return fxmlLoader;
    }
}
