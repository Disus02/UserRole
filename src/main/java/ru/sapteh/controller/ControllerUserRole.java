package ru.sapteh.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.Dao;
import ru.sapteh.model.Role;
import ru.sapteh.model.User;
import ru.sapteh.model.UserRole;
import ru.sapteh.service.RoleService;
import ru.sapteh.service.UserRoleService;
import ru.sapteh.service.UserService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;

public class ControllerUserRole {

    ObservableList<Role> roles= FXCollections.emptyObservableList();
    ObservableList<User> users=FXCollections.observableArrayList();
    @FXML
    private ComboBox<Role> comboRole;
    @FXML
    private ComboBox<User> comboUser;
    @FXML
    private DatePicker pickedData;
    @FXML
    public void initialize(){
        init();
    }
    @FXML
    public void pressCreate(ActionEvent event) throws ParseException {
        SessionFactory factory=new Configuration().configure().buildSessionFactory();
        Dao<UserRole,Integer> userRoleService=new UserRoleService(factory);
        String dateStr=pickedData.getValue().toString();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date=df.parse(dateStr);
        date.setTime(date.getTime()+86400000);
        UserRole userRole=new UserRole(comboUser.getValue(),comboRole.getValue(),date);
        userRoleService.create(userRole);
        factory.close();
    }

    private void init(){
        SessionFactory factory=new Configuration().configure().buildSessionFactory();
        Dao<User,Integer> userService=new UserService(factory);
        Dao<Role,Integer> roleService=new RoleService(factory);
        comboRole.setItems(FXCollections.observableArrayList(roleService.readByAll()));
        comboUser.setItems(FXCollections.observableArrayList(userService.readByAll()));
        factory.close();
    }
}
