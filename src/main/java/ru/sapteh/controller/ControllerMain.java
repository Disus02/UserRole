package ru.sapteh.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.model.Role;
import ru.sapteh.model.User;
import ru.sapteh.model.UserRole;
import ru.sapteh.service.UserRoleService;
import ru.sapteh.service.UserService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class ControllerMain {
    ObservableList<User> listUsers= FXCollections.observableArrayList();
    ObservableList<Role> roleList=FXCollections.observableArrayList();
    ObservableList<Date> listData=FXCollections.observableArrayList();
    @FXML
    private Button buttonUser;
    @FXML
    private Button buttonRole;
    @FXML
    private Button buttonUserRole;
    @FXML
    private TableView<User> tableUsers;
    @FXML
    private TableColumn<User,String> columnFirstName;
    @FXML
    private TableColumn<User,String> columnLastName;
    @FXML
    private Label labelFirstName;
    @FXML
    private Label labelLastName;
    @FXML
    private Label labelEmail;
    @FXML
    private ComboBox<Role> comboRole;
    @FXML
    private ComboBox<Date> comboRegData;


    public void initialize(){
        SessionFactory factory=new Configuration().configure().buildSessionFactory();
        UserService userService=new UserService(factory);
        listUsers.addAll(userService.readByAll());
        columnFirstName.setCellValueFactory(u->new SimpleObjectProperty<>(u.getValue().getFirstName()));
        columnLastName.setCellValueFactory(u->new SimpleObjectProperty<>(u.getValue().getLastName()));
        tableUsers.setItems(listUsers);
        tableUsers.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    getUser(newValue);
                });
        factory.close();
    }

    @FXML
    public void openUser(ActionEvent event) throws IOException {
        buttonUser.getScene().getWindow().hide();
        Parent parent= FXMLLoader.load(getClass().getResource("/view/createUser.fxml"));
        Stage stage=new Stage();
        stage.setTitle("Создание пользователя");
        stage.setScene(new Scene(parent));
        stage.show();
    }
    @FXML
    public void openRole(ActionEvent event) throws IOException {
        buttonRole.getScene().getWindow().hide();
        Parent parent= FXMLLoader.load(getClass().getResource("/view/createRole.fxml"));
        Stage stage=new Stage();
        stage.setTitle("Создание роли");
        stage.setScene(new Scene(parent));
        stage.show();
    }
    @FXML
    public void openUserRole(ActionEvent event) throws IOException {
        buttonUserRole.getScene().getWindow().hide();
        Parent parent= FXMLLoader.load(getClass().getResource("/view/createUserRole.fxml"));
        Stage stage=new Stage();
        stage.setTitle("Изменение прав пользователя");
        stage.setScene(new Scene(parent));
        stage.show();
    }
    private void getUser(User user){
        listData.clear();
        roleList.clear();
        labelFirstName.setText(user.getFirstName());
        labelLastName.setText(user.getLastName());
        labelEmail.setText(user.getEmail());
        for (UserRole userRole:user.getRoles()) {
            roleList.addAll(userRole.getRole());
        }
        for (UserRole userRole: user.getRoles()) {
            listData.addAll(userRole.getRegistrationDate());
        }
        comboRole.setItems(roleList);
        comboRegData.setItems(listData);

    }

}
