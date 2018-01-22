package stageCloud.controllers;

import javafx.event.ActionEvent;
import stageCloud.MainStageEdit;
import stageCloud.MainStageShow;
import stageCloud.ModeStage;

/**
 * Created by alien on 25.09.2017.
 */
public class ModeController
{
    public void editMode(ActionEvent actionEvent)
    {
        ModeStage.hide();
        MainStageEdit mainStageEdit = new MainStageEdit();
        mainStageEdit.start();
    }

    public void showMode(ActionEvent actionEvent)
    {
        ModeStage.hide();
        MainStageShow mainStageShow = new MainStageShow();
        mainStageShow.start();
    }
}
