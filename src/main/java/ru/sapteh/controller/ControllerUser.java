package ru.sapteh.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.model.User;
import ru.sapteh.service.UserService;

public class ControllerUser {
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtEmail;

    @FXML
    public void pressCreate(ActionEvent event){
        SessionFactory factory=new Configuration().configure().buildSessionFactory();
        UserService userService=new UserService(factory);
        User user=new User();
        user.setFirstName(txtFirstName.getText());
        user.setLastName(txtLastName.getText());
        user.setEmail(txtEmail.getText());
        userService.create(user);
        factory.close();
    }
}
