package stageCloud.controllers;

import collections.Staffs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import objects.Staff;

/**
 * Created by alien on 03.10.2017.
 */
public class StaffEController
{
    @FXML
    private TextField txtFIO;
    @FXML
    private TextField txtPassport;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtAnnotation;

    private Staff staff;
    private boolean addOrEdit;

    public void setStaff(Staff staff)
    {
        if (staff == null) return;
        this.staff = staff;
        txtFIO.setText(staff.getFIO());
        txtPassport.setText(staff.getPassport());
        txtPhone.setText(staff.getPhone());
        txtEmail.setText(staff.getEmail());
        txtAnnotation.setText(staff.getAnnotation());
    }

    public void setAddOrEdit(boolean addOrEdit)
    {
        this.addOrEdit = addOrEdit;
    }

    public void actionSave(ActionEvent actionEvent)
    {
        staff.setFIO(txtFIO.getText());
        staff.setPassport(txtPassport.getText());
        staff.setPhone(txtPhone.getText());
        staff.setEmail(txtEmail.getText());
        staff.setAnnotation(txtAnnotation.getText());

        if (addOrEdit)
        {
            Staffs.add(staff);
        }
        else
        {
            Staffs.update(staff);
        }

        actionClose(actionEvent);
    }

    public void actionClose(ActionEvent actionEvent)
    {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();//tut - close
    }
}
