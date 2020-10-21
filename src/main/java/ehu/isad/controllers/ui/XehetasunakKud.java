package ehu.isad.controllers.ui;

import com.sun.source.doctree.TextTree;
import ehu.isad.Book;
import ehu.isad.Liburuak;
import ehu.isad.controllers.db.OpenLibraryKud;
import ehu.isad.utils.Sarea;
import ehu.isad.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.event.ActionEvent;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

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

        String info = OpenLibraryKud.getInstance().libdatuak(b.getIsbn());
        String[] subinfo = info.split(",");
        DatuIzena_lbl.setText(subinfo[0]);
        List<String> arglista = OpenLibraryKud.getInstance().arglistaLortu(b.getIsbn());
        for (int i=0; i < arglista.size(); i++){
            DatuArg_lbl.setText(DatuArg_lbl.getText()+ ", " + arglista.get(0));
        }
        DatuOrr_lbl.setText(subinfo[1]);
        String path = Utils.lortuEzarpenak().getProperty("pathtoimages")+b.getIsbn()+".png";
        try {
            ImageviewIrudia.setImage(new Image(new FileInputStream(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
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

         */
    }

}

