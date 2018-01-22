package stageCloud.controllers;

import collections.Objects;
import collections.Staffs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import objects.Customer;
import objects.MyFile;
import objects.Object;
import objects.Staff;
import stageCloud.MainStageEdit;
import stageCloud.ObjectSStage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by alien on 03.10.2017.
 */
public class ObjectSController
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
    @FXML
    private TableView table;
    @FXML
    private TableColumn clnName;
    @FXML
    private TableColumn clnPath;

    private Object object;
    private Customer customer;
    private Staff staff;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
    private ObservableList<Staff> staffList;
    private String path;
    private Objects objects = new Objects();
    private MyFile myFile;

    private ObservableList<MyFile> fileList = FXCollections.observableArrayList();

    @FXML
    private void initialize()
    {
        initCheckBox();
        initComboBox();
        initListiner();
    }

    private void initListiner()
    {
        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                if (event.getClickCount() == 2)
                {
                    myFile = (MyFile) table.getSelectionModel().getSelectedItem();
                    System.out.println("myFile");
                    File file = new File(myFile.getPath());
                    try {
                        java.awt.Desktop.getDesktop().open(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
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
        path = object.getPath();

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

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public void setTable()
    {
        System.out.println(path);
        if (path!=null&&!"---".equals(path))
        {
            System.out.println("no null");
            System.out.println(path);
            File f = new File(this.path);
            File[] list = f.listFiles();
            for (int i = 0; i < list.length; i++) {
                fileList.add(new MyFile(list[i].getName(), list[i].getAbsolutePath()));
            }
            clnName.setCellValueFactory(new PropertyValueFactory<MyFile, Integer>("name"));
            clnPath.setCellValueFactory(new PropertyValueFactory<MyFile, String>("path"));
            table.setItems(fileList);
        }
    }
}
