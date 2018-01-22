package stageCloud.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import objects.Staff;

/**
 * Created by alien on 03.10.2017.
 */
public class StaffSController
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
}
