package com.mycompany.loan_control.controllers.home;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.mycompany.loan_control.App;
import com.mycompany.loan_control.Location;
import com.mycompany.loan_control.entities.Book;
import com.mycompany.loan_control.entities.response.Response;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

@SuppressWarnings("unchecked")
public class SecondaryController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb){
        System.out.println("SecondaryController");        
        Response<List<Book>> param = (Response<List<Book>>) Location.getParams().getData();
        System.out.println(param.getData());
    }

    @FXML
    public void switchToPrimary() throws IOException {      
        App.setRoot("home", "primary");
    }
}