package stageCloud.controllers;

import collections.Customers;
import collections.Objects;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import objects.Customer;
import objects.Object;
import stageCloud.CustomerEStage;
import stageCloud.ObjectEStage;

import java.util.Optional;

/**
 * Created by alien on 03.10.2017.
 */
public class CustomerEController
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

    private Objects objects = new Objects();
    private Object selectObject;
    private Customer customer;
    private boolean addOrEdit;
    private boolean bool = false;
    private ObjectEStage objectEStage;
    private ObjectEController objectEController;
    private Alert alert;

    @FXML
    private void initialize()
    {

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

    public void add(ActionEvent actionEvent)
    {
        if (bool == false)
        {
            objectEStage = new ObjectEStage();
            objectEStage.initLoader();
            objectEController = ObjectEStage.getFxmlLoader().getController();
            bool = true;
        }
        objectEController.setObject(new Object());
        objectEController.setAddOrEdit(true);
        objectEController.setCustomer(customer);
        objectEStage.showDialog();

        table.setItems(objects.selectByCustomer(customer.getId()));
    }

    public void edit(ActionEvent actionEvent)
    {
        if (bool == false)
        {
            objectEStage = new ObjectEStage();
            objectEStage.initLoader();
            objectEController = ObjectEStage.getFxmlLoader().getController();
            bool = true;
        }
        selectObject = (Object) table.getSelectionModel().getSelectedItem();
        objectEController.setObject(selectObject);
        objectEController.setAddOrEdit(false);
        objectEController.setCustomer(customer);
        objectEStage.showDialog();

        table.setItems(objects.selectByCustomer(customer.getId()));
    }

    public void delete(ActionEvent actionEvent)
    {
        if (bool == false)
        {
            objectEStage = new ObjectEStage();
            objectEStage.initLoader();
            objectEController = ObjectEStage.getFxmlLoader().getController();
            bool = true;
        }
        selectObject = (Object) table.getSelectionModel().getSelectedItem();
        if (selectObject==null) return;

        initConfimAlert();

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Objects.delete(selectObject.getId());
            table.setItems(objects.selectByCustomer(customer.getId()));
        } else {
            alert.close();
        }
    }

    public void actionSave(ActionEvent actionEvent)
    {
        customer.setName(txtName.getText());
        customer.setAdres(txtAdres.getText());
        customer.setPhone(txtPhone.getText());
        customer.setEmail(txtEmail.getText());
        customer.setFio1(txtFIO1.getText());
        customer.setPhone1(txtPhone1.getText());
        customer.setFio2(txtFIO2.getText());
        customer.setPhone2(txtPhone2.getText());
        customer.setReception1(txtReception1.getText());
        customer.setReception2(txtReception2.getText());
        customer.setReception3(txtReception3.getText());
        customer.setHouse1(txtHouse1.getText());
        customer.setHouse2(txtHouse2.getText());
        customer.setHouse3(txtHouse3.getText());
        customer.setHouse4(txtHouse4.getText());
        customer.setInn(txtINN.getText());
        customer.setKpp(txtKPP.getText());
        customer.setAnnotation(txtAnnotation.getText());

        if (addOrEdit)
            Customers.add(customer);
        else
            Customers.update(customer);

        actionClose(actionEvent);
    }

    public void actionClose(ActionEvent actionEvent)
    {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();//tut - close
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

    public void setAddOrEdit(boolean addOrEdit)
    {
        this.addOrEdit = addOrEdit;
    }

    private void initConfimAlert()
    {
        if(alert==null)
        {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Внимание");
            alert.setHeaderText("Удаление записи");
            alert.setContentText("Вы уверены что хотите удалить запись?");
        }
    }
}
