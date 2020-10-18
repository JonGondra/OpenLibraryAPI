package ehu.isad.controllers;

import com.sun.source.doctree.TextTree;
import ehu.isad.Book;
import ehu.isad.Liburuak;
import ehu.isad.utils.Sarea;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import javafx.event.ActionEvent;
import javafx.scene.text.Text;

import java.io.IOException;

public class XehetasunakKud {

    private Liburuak mainApp;


    public void setMainApp(Liburuak main){
        this.mainApp = main;
    }

    @FXML
    private Text DatuIzena_lbl;

    @FXML
    private Text DatuArg_lbl;

    @FXML
    private Text DatuOrr_lbl;

    @FXML
    private ImageView ImageviewIrudia;

    @FXML
    private Button Atzera_btn;

    @FXML
    void onClick(ActionEvent event) {
        mainApp.mainErakutsi();
    }

    public  void datuak(Book b){
        System.out.println(b.getDetails().getTitle());

        DatuIzena_lbl.setText(b.getDetails().getTitle());
        DatuArg_lbl.setText(b.getDetails().getPublishers()[0]);
        String idazleak = "";
        for (int i=0; i < b.getDetails().getPublishers().length; i++){
            DatuArg_lbl.setText(DatuArg_lbl.getText()+ ", " +  b.getDetails().getPublishers()[i]);
        }
        DatuOrr_lbl.setText(String.valueOf(b.getDetails().getNumber_of_pages()));
        try {
            ImageviewIrudia.setImage(new Sarea().createImage(b.getThumbnail_url()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

