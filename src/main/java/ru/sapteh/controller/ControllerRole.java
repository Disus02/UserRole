package ru.sapteh.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.model.Role;
import ru.sapteh.service.RoleService;

import java.util.ArrayList;
import java.util.List;

public class ControllerRole {
    @FXML
    private TextField txtName;
    @FXML
    private Label status;
    List<Role> roles=new ArrayList<>();

    @FXML
    public void pressCreate(ActionEvent event){
        SessionFactory factory=new Configuration().configure().buildSessionFactory();
        RoleService roleService=new RoleService(factory);
        Role role=new Role();
        role.setName(txtName.getText());
        roles=roleService.readByAll();
        for (Role role1:roles) {
            while (role1.getName().equals(txtName.getText())){
                status.setText("ошибка");
            }break;
        }
        roleService.create(role);
        status.setText("Роль зарегистрирована");
        factory.close();

    }
}
