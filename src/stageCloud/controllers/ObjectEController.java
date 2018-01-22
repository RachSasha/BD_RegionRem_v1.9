package stageCloud.controllers;

import collections.Objects;
import collections.Staffs;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import objects.Customer;
import objects.Object;
import objects.Staff;
import stageCloud.MainStageEdit;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by alien on 03.10.2017.
 */
public class ObjectEController
{
    @FXML
    private TextField txtAdres;
    @FXML
    private TextField txtCustNumb;
    @FXML
    private DatePicker date;
    @FXML
    private TextField txtType;
    @FXML
    private TextField txtCost;
    @FXML
    private TextField txtWarranty;
    @FXML
    private CheckBox checkBox;
    @FXML
    private ComboBox txtFIO;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtAnnotation;
    @FXML
    private HBox hBoxFIO;
    @FXML
    private HBox hBoxPhE;

    private boolean addOrEdit;
    private Object object;
    private Customer customer;
    private Staff staff;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
    private ObservableList<Staff> staffList;
    private String path;
    private Objects objects = new Objects();

    @FXML
    private void initialize()
    {
        initCheckBox();
        initComboBox();
    }

    private void initComboBox()
    {
        Staffs staffs = new Staffs();
        staffList = staffs.select();
        for (Staff staff : staffList)
        {
            txtFIO.getItems().add(staff.getFIO());
        }

        txtFIO.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("TextField Text Changed (newValue: " + newValue + ")");
            staff = staffs.findOfFio(newValue.toString());
            txtEmail.setText(staff.getEmail());
            txtPhone.setText(staff.getPhone());
        });
    }

    private void initCheckBox() {
        if (checkBox.isSelected() == false)
        {
            hBoxFIO.setDisable(true);
            hBoxPhE.setDisable(true);
        }
        else
        {
            hBoxFIO.setDisable(false);
            hBoxPhE.setDisable(false);
        }

        checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("TextField Text Changed (newValue: " + newValue + ")");
            hBoxFIO.setDisable(!newValue);
            hBoxPhE.setDisable(!newValue);
        });
    }

    public void setObject(Object object)
    {
        if (object == null) return;
        this.object = object;
        txtAdres.setText(object.getAdres());
        txtCustNumb.setText(object.getCustNumb());

        if (object.getDate() != null)
        {
            LocalDate localDate = LocalDate.parse(object.getDate(), formatter);
            date.setValue(localDate);
        }

        txtType.setText(object.getType());
        txtCost.setText(object.getCost());
        txtWarranty.setText(object.getWarranty());

        checkBox.setSelected(object.isGo());
        if (object.isGo())
        {
            Staff staff = Staffs.getStaff(object.getIdStaff());
            txtFIO.setValue(staff.getFIO());
        }


        txtAnnotation.setText(object.getAnnotation());
    }

    public void setAddOrEdit(boolean addOrEdit)
    {
        this.addOrEdit = addOrEdit;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public void chousePath(ActionEvent actionEvent)
    {
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        configuringDirectoryChooser(directoryChooser);
        MainStageEdit mainStageEdit = new MainStageEdit();
        File dir = directoryChooser.showDialog(mainStageEdit.getStage());
        if (dir != null) {
            path = dir.getAbsolutePath();
        } else {
//            textArea.setText(null);
        }
    }

    public void actionSave(ActionEvent actionEvent)
    {
        object.setAdres(txtAdres.getText());
        object.setCustNumb(txtCustNumb.getText());
        object.setDate(date.getValue().format(formatter));
        object.setType(txtType.getText());
        object.setCost(txtCost.getText());
        object.setWarranty(txtWarranty.getText());
        object.setGo(checkBox.isSelected());
        object.setAnnotation(txtAnnotation.getText());
        System.out.println(path);
        if (path != null)
            System.out.println("четко");
            object.setPath(path);
        if (staff != null)
            object.setIdStaff(staff.getId());
        if (customer != null)
            object.setIdCustomer(customer.getId());

        if (addOrEdit)
        {
            objects.select();
            Objects.add(object);
        }
        else
        {
            objects.select();
            Objects.update(object);
        }

        actionClose(actionEvent);
    }

    public void actionClose(ActionEvent actionEvent)
    {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();//tut - close
    }

    private void configuringDirectoryChooser(DirectoryChooser directoryChooser) {
        // Set title for DirectoryChooser
        directoryChooser.setTitle("Select Some Directories");

        // Set Initial Directory
//        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    }
}
