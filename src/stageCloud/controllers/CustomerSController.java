package stageCloud.controllers;

import collections.Objects;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import objects.Customer;
import objects.Object;
import objects.Staff;
import stageCloud.CustomerSStage;
import stageCloud.ObjectEStage;
import stageCloud.ObjectSStage;
import stageCloud.StaffSStage;

import java.io.IOException;

/**
 * Created by alien on 03.10.2017.
 */
public class CustomerSController
{
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtAdres;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtINN;
    @FXML
    private TextField txtKPP;
    @FXML
    private TextField txtFIO1;
    @FXML
    private TextField txtPhone1;
    @FXML
    private TextField txtFIO2;
    @FXML
    private TextField txtPhone2;
    @FXML
    private TextField txtReception1;
    @FXML
    private TextField txtReception2;
    @FXML
    private TextField txtReception3;
    @FXML
    private TextField txtHouse1;
    @FXML
    private TextField txtHouse2;
    @FXML
    private TextField txtHouse3;
    @FXML
    private TextField txtHouse4;
    @FXML
    private TextField txtAnnotation;
    @FXML
    private TableView table;
    @FXML
    private TableColumn clnId;
    @FXML
    private TableColumn clnAdres;
    @FXML
    private TableColumn clnCustNumb;
    @FXML
    private TableColumn clnDate;
    @FXML
    private TableColumn clnType;
    @FXML
    private TableColumn clnCost;
    @FXML
    private TableColumn clnWarranty;
    @FXML
    private CheckBox checkBox;

    private Customer customer;
    private boolean addOrEdit;
    private boolean bool = false;
    private ObjectEStage objectEStage;
    private ObjectEController objectEController;
    private Alert alert;

    private Objects objects = new Objects();
    private ObjectSStage objectSStage;
    private ObjectSController objectSController;
    private Object selectObject;
    private boolean objectBool = false;

    @FXML
    private void initialize() throws IOException
    {
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

                        if (objectBool == false)
                        {
                            objectSStage = new ObjectSStage();
                            objectSStage.initLoader();
                            objectSController = ObjectSStage.getFxmlLoader().getController();
                            objectBool = true;
                        }
                        selectObject = (Object) table.getSelectionModel().getSelectedItem();
                        objectSController.setObject(selectObject);
                        objectSController.setTable();
                        objectSStage.showDialog();

                }
            }
        });
    }

    public void initTable()
    {
        clnId.setCellValueFactory(new PropertyValueFactory<Object, Integer>("id"));
        clnAdres.setCellValueFactory(new PropertyValueFactory<Object, String>("adres"));
        clnCustNumb.setCellValueFactory(new PropertyValueFactory<Object, String>("custNumb"));
        clnDate.setCellValueFactory(new PropertyValueFactory<Object, Integer>("date"));
        clnType.setCellValueFactory(new PropertyValueFactory<Object, String>("type"));
        clnCost.setCellValueFactory(new PropertyValueFactory<Object, String>("cost"));
        clnWarranty.setCellValueFactory(new PropertyValueFactory<Object, String>("warranty"));
        initCheckBox();
    }

    private void initCheckBox() {
        if (checkBox.isSelected() == false)
        {
            if (!addOrEdit)
                table.setItems(objects.selectByCustomer(customer.getId()));
        }
        else
        {
            if (!addOrEdit)
                table.setItems(objects.selectByCustomerIsSelect(customer.getId()));
        }

        checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("TextField Text Changed (newValue: " + newValue + ")");
            if (newValue) {
                table.setItems(objects.selectByCustomerIsSelect(customer.getId()));
                System.out.println("1");
            }
            else {
                table.setItems(objects.selectByCustomer(customer.getId()));
                System.out.println("2");
            }
        });
    }

    public void setCustomer(Customer customer)
    {
        if (customer == null) return;
        this.customer = customer;
        txtName.setText(customer.getName());
        txtAdres.setText(customer.getAdres());
        txtPhone.setText(customer.getPhone());
        txtEmail.setText(customer.getEmail());
        txtFIO1.setText(customer.getFio1());
        txtPhone1.setText(customer.getPhone1());
        txtFIO2.setText(customer.getFio2());
        txtPhone2.setText(customer.getPhone2());
        txtReception1.setText(customer.getReception1());
        txtReception2.setText(customer.getReception2());
        txtReception3.setText(customer.getReception3());
        txtHouse1.setText(customer.getHouse1());
        txtHouse2.setText(customer.getHouse2());
        txtHouse3.setText(customer.getHouse3());
        txtHouse4.setText(customer.getHouse4());
        txtINN.setText(customer.getInn());
        txtKPP.setText(customer.getKpp());
        txtAnnotation.setText(customer.getAnnotation());
    }
}
