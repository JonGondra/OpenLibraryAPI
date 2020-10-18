package ehu.isad.controllers;

import ehu.isad.Liburuak;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LiburuKud implements Initializable {

    private Liburuak mainApp;


    public void setMainApp(Liburuak main) {

        this.mainApp = main;
    }


    @FXML
    private Label lbl_hautatu;

    @FXML
    private ComboBox<String> liburuak_combobox;

    @FXML
    private Button btn_Ikusi;

    @FXML
    void onClick(ActionEvent event) {
        String isbn = this.getisbn(liburuak_combobox.getValue());
        mainApp.xehetasunakErakutsi(isbn);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        liburuak_combobox.getItems().add(0, "Blockchain: Blueprint for a New Economy");
        liburuak_combobox.getItems().add(1, "R for Data Science");
        liburuak_combobox.getItems().add(2, "Fluent Python");
        liburuak_combobox.getItems().add(3, "Natural Language Processing with PyTorch");
        liburuak_combobox.getItems().add(4, "Data Algorithms");
    }

    private String getisbn(String lib){
        String isbn = "";
        switch (lib) {
            case "Blockchain: Blueprint for a New Economy":
                isbn = "9781491920497";
                break;
            case "R for Data Science":
                isbn = "1491910399";
                break;
            case "Fluent Python":
                isbn = "1491946008";
                break;
            case "Natural Language Processing with PyTorch":
                isbn = "1491978236";
                break;
            case "Data Algorithms":
                isbn = "9781491906187";
                break;
        }
        return isbn;
    }
}

