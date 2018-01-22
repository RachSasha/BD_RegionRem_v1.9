package stageCloud.controllers;

import collections.Customers;
import collections.Objects;
import collections.Staffs;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import objects.Customer;
import objects.Object;
import objects.Staff;
import stageCloud.*;

import java.io.IOException;

/**
 * Created by alien on 25.09.2017.
 */
public class MainShowController
{
    @FXML
    private TextField txtSearch;
    @FXML
    private TableView table;
    @FXML
    private Label txtNameTable;

    private int selectTable;

    private Customers customers = new Customers();
    private CustomerSStage customerSStage;
    private CustomerSController customerSController;
    private Customer selectCustomer;
    private boolean customerBool = false;

    private Objects objects = new Objects();
    private ObjectSStage objectSStage;
    private ObjectSController objectSController;
    private Object selectObject;
    private boolean objectBool = false;

    private Staffs staffs = new Staffs();
    private StaffSStage staffSStage;
    private StaffSController staffSController;
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
            else if (selectTable==3)
                table.setItems(staffs.find(txtSearch.getText()));
            else if (selectTable==2)
                table.setItems(objects.find(txtSearch.getText()));
        });

        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                if (event.getClickCount() == 2)
                {
                    if (selectTable==1)
                    {
                        if (customerBool == false)
                        {
                            customerSStage = new CustomerSStage();
                            customerSStage.initLoader();
                            customerSController = CustomerSStage.getFxmlLoader().getController();
                            customerBool = true;
                        }
                        selectCustomer = (Customer) table.getSelectionModel().getSelectedItem();
                        customerSController.setCustomer(selectCustomer);
                        customerSController.initTable();
                        customerSStage.showDialog();
                    }
                    else if (selectTable==2)
                    {
//                        if (objectBool == false)
//                        {
//                            objectSStage = new ObjectSStage();
//                            objectSStage.initLoader();
//                            objectSController = ObjectSStage.getFxmlLoader().getController();
//                            objectBool = true;
//                        }
//                        selectObject = (Object) table.getSelectionModel().getSelectedItem();
//                        objectSController.setObject(selectObject);
//                        objectSController.initTable();
//                        objectSStage.showDialog();
                    }
                    else if (selectTable==3)
                    {
                        if (staffBool == false)
                        {
                            staffSStage = new StaffSStage();
                            staffSStage.initLoader();
                            staffSController = StaffSStage.getFxmlLoader().getController();
                            staffBool = true;
                        }
                        selectStaff = (Staff) table.getSelectionModel().getSelectedItem();
                        staffSController.setStaff(selectStaff);
                        staffSStage.showDialog();
                    }
                }
            }
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

    public void openObjectTable(ActionEvent actionEvent)
    {
        table.getColumns().remove(0, table.getColumns().size());
        TableColumn clnId = new TableColumn("id");
        TableColumn clnCustomer = new TableColumn<>("Заказчик");
        TableColumn clnAdres = new TableColumn<>("Адрес");
        TableColumn clnCustNumb = new TableColumn<>("Номер Договора");
        TableColumn clnDate = new TableColumn<>("Дата договора");
        TableColumn clnType = new TableColumn<>("Вид работ");
        TableColumn clnCost = new TableColumn<>("Стоимость работ");
        TableColumn clnWarranty = new TableColumn<>("Срок гарантии");
        table.getColumns().addAll(clnId, clnCustomer, clnAdres, clnCustNumb, clnDate, clnType,
                clnCost, clnWarranty);
        clnId.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
        clnCustomer.setCellValueFactory(new PropertyValueFactory<Customer, String>("customer"));
        clnAdres.setCellValueFactory(new PropertyValueFactory<Customer, String>("adres"));
        clnCustNumb.setCellValueFactory(new PropertyValueFactory<Customer, String>("custNumb"));
        clnDate.setCellValueFactory(new PropertyValueFactory<Customer, String>("date"));
        clnType.setCellValueFactory(new PropertyValueFactory<Customer, String>("type"));
        clnCost.setCellValueFactory(new PropertyValueFactory<Customer, String>("cost"));
        clnWarranty.setCellValueFactory(new PropertyValueFactory<Customer, String>("warranty"));
        table.setItems(objects.select());
        txtNameTable.setText("Объекты");
        selectTable = 2;
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
        selectTable = 3;
    }
}
