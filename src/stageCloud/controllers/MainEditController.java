package stageCloud.controllers;

import collections.Customers;
import collections.Objects;
import collections.Staffs;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import objects.Customer;
import objects.Object;
import objects.Staff;
import stageCloud.CustomerEStage;
import stageCloud.CustomerSStage;
import stageCloud.ObjectEStage;
import stageCloud.StaffEStage;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by alien on 25.09.2017.
 */
public class MainEditController
{
    @FXML
    private TextField txtSearch;
    @FXML
    private TableView table;
    @FXML
    private Label txtNameTable;

    private int selectTable;
    private Alert alert;

    private Customers customers = new Customers();
    private CustomerEStage customerEStage;
    private CustomerEController customerEController;
    private Customer selectCustomer;
    private boolean customerBool = false;

    private Objects objects = new Objects();
    private ObjectEStage objectEStage;
    private ObjectEController objectEController;
    private Object selectObject;
    private boolean objectBool = false;

    private Staffs staffs = new Staffs();
    private StaffEStage staffEStage;
    private StaffEController staffEController;
    private Staff selectStaff;
    private boolean staffBool = false;

    @FXML
    private void initialize() throws IOException
    {
        initListiner();
    }

    private void initListiner()
    {
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("TextField Text Changed (newValue: " + newValue + ")");
            if (selectTable==1)
                table.setItems(customers.find(txtSearch.getText()));
            else if (selectTable==2)
                table.setItems(staffs.find(txtSearch.getText()));
        });
    }

    public void openCustomerTable(ActionEvent actionEvent)
    {
        table.getColumns().remove(0, table.getColumns().size());
        TableColumn clnId = new TableColumn("id");
        TableColumn clnName = new TableColumn<>("Наименование");
        TableColumn clnAdres = new TableColumn<>("Адрес");
        TableColumn clnPhone = new TableColumn<>("Телефон");
        TableColumn clnINN = new TableColumn<>("ИНН");
        TableColumn clnKPP = new TableColumn<>("КПП");
        TableColumn clnEmail = new TableColumn<>("Эл. почта");
        TableColumn clnFIO1 = new TableColumn<>("ФИО председателя");
        TableColumn clnPhone1 = new TableColumn<>("Тел председателя");
        TableColumn clnFIO2 = new TableColumn<>("ФИО бухгалтера");
        TableColumn clnPhone2 = new TableColumn<>("Тел бухгалтера");
        TableColumn clnReception1 = new TableColumn<>("Место приема");
        TableColumn clnReception2 = new TableColumn<>("Примечания для посещения");
        TableColumn clnReception3 = new TableColumn<>("Часы приема");
        TableColumn clnHouse1 = new TableColumn<>("Серия дома");
        TableColumn clnHouse2 = new TableColumn<>("Кол-во этажей");
        TableColumn clnHouse3 = new TableColumn<>("Кол-во парадных");
        TableColumn clnHouse4 = new TableColumn<>("Кол-во квартир");
        TableColumn clnAnnotation = new TableColumn<>("Работы на перспективу");
        table.getColumns().addAll(clnId, clnName, clnAdres, clnPhone, clnINN, clnKPP,
                clnEmail, clnFIO1, clnPhone1, clnFIO2, clnPhone2, clnReception1,
                clnReception2, clnReception3, clnHouse1, clnHouse2, clnHouse3, clnHouse4, clnAnnotation);
        clnId.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
        clnName.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        clnAdres.setCellValueFactory(new PropertyValueFactory<Customer, String>("adres"));
        clnPhone.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
        clnINN.setCellValueFactory(new PropertyValueFactory<Customer, String>("inn"));
        clnKPP.setCellValueFactory(new PropertyValueFactory<Customer, String>("kpp"));
        clnEmail.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
        clnFIO1.setCellValueFactory(new PropertyValueFactory<Customer, String>("fio1"));
        clnPhone1.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone1"));
        clnFIO2.setCellValueFactory(new PropertyValueFactory<Customer, String>("fio2"));
        clnPhone2.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone2"));
        clnReception1.setCellValueFactory(new PropertyValueFactory<Customer, String>("reception1"));
        clnReception2.setCellValueFactory(new PropertyValueFactory<Customer, String>("reception2"));
        clnReception3.setCellValueFactory(new PropertyValueFactory<Customer, String>("reception3"));
        clnHouse1.setCellValueFactory(new PropertyValueFactory<Customer, String>("house1"));
        clnHouse2.setCellValueFactory(new PropertyValueFactory<Customer, String>("house2"));
        clnHouse3.setCellValueFactory(new PropertyValueFactory<Customer, String>("house3"));
        clnHouse4.setCellValueFactory(new PropertyValueFactory<Customer, String>("house4"));
        clnAnnotation.setCellValueFactory(new PropertyValueFactory<Customer, String>("annotation"));
        table.setItems(customers.select());
        txtNameTable.setText("Заказчики");
        selectTable = 1;
    }

    public void openStaffTable(ActionEvent actionEvent)
    {
        table.getColumns().remove(0, table.getColumns().size());
        TableColumn clnId = new TableColumn("id");
        TableColumn clnFIO = new TableColumn<>("ФИО");
        TableColumn clnPassport = new TableColumn<>("Номер паспорта");
        TableColumn clnPhone = new TableColumn<>("Телефон");
        TableColumn clnEmail = new TableColumn<>("Эл. почта");
        TableColumn clnAnnotation = new TableColumn<>("Коментарий");
        table.getColumns().addAll(clnId, clnFIO, clnPassport, clnPhone, clnEmail, clnAnnotation);
        clnId.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
        clnFIO.setCellValueFactory(new PropertyValueFactory<Customer, String>("FIO"));
        clnPassport.setCellValueFactory(new PropertyValueFactory<Customer, String>("passport"));
        clnPhone.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
        clnEmail.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
        clnAnnotation.setCellValueFactory(new PropertyValueFactory<Customer, String>("annotation"));
        table.setItems(staffs.select());
        txtNameTable.setText("Персонал");
        selectTable = 2;
    }

    public void add(ActionEvent actionEvent)
    {
        if (selectTable == 1)
        {
            if (customerBool == false)
            {
                customerEStage = new CustomerEStage();
                customerEStage.initLoader();
                customerEController = CustomerEStage.getFxmlLoader().getController();
                customerBool = true;
            }
            customerEController.setCustomer(new Customer());
            customerEController.setAddOrEdit(true);
            customerEController.initTable();
            customerEStage.showDialog();

            table.setItems(customers.select());
        }
        else if (selectTable == 2)
        {
            if (staffBool == false)
            {
                staffEStage = new StaffEStage();
                staffEStage.initLoader();
                staffEController = StaffEStage.getFxmlLoader().getController();
                staffBool = true;
            }
            staffEController.setStaff(new Staff());
            staffEController.setAddOrEdit(true);
            staffEStage.showDialog();

            table.setItems(staffs.select());
        }
    }

    public void edit(ActionEvent actionEvent)
    {
        if (selectTable == 1)
        {
            if (customerBool == false)
            {
                customerEStage = new CustomerEStage();
                customerEStage.initLoader();
                customerEController = CustomerEStage.getFxmlLoader().getController();
                customerBool = true;
            }
            selectCustomer = (Customer) table.getSelectionModel().getSelectedItem();
            customerEController.setCustomer(selectCustomer);
            customerEController.setAddOrEdit(false);
            customerEController.initTable();
            customerEStage.showDialog();

            table.setItems(customers.select());
        }
        else if (selectTable == 2)
        {
            if (staffBool == false)
            {
                staffEStage = new StaffEStage();
                staffEStage.initLoader();
                staffEController = StaffEStage.getFxmlLoader().getController();
                staffBool = true;
            }
            selectStaff = (Staff) table.getSelectionModel().getSelectedItem();
            if (selectStaff==null) return;
            staffEController.setStaff(selectStaff);
            staffEController.setAddOrEdit(false);
            staffEStage.showDialog();

            table.setItems(staffs.select());
        }
    }

    public void delete(ActionEvent actionEvent)
    {
        if (selectTable == 1)
        {
            if (customerBool == false)
            {
                customerEStage = new CustomerEStage();
                customerEStage.initLoader();
                customerEController = CustomerEStage.getFxmlLoader().getController();
                customerBool = true;
            }
            selectCustomer = (Customer) table.getSelectionModel().getSelectedItem();
            if (selectCustomer==null) return;

            initConfimAlert();

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                Customers.delete(selectCustomer.getId());
                table.setItems(customers.select());
            } else {
                alert.close();
            }
        }
        if (selectTable == 2)
        {
            if (staffBool == false)
            {
                staffEStage = new StaffEStage();
                staffEStage.initLoader();
                staffEController = StaffEStage.getFxmlLoader().getController();
                staffBool = true;
            }
            selectStaff = (Staff) table.getSelectionModel().getSelectedItem();
            if (selectStaff==null) return;

            initConfimAlert();

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                Staffs.delete(selectStaff.getId());
                table.setItems(staffs.select());
            } else {
                alert.close();
            }
        }
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
