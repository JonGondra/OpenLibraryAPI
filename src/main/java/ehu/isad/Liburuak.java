/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ehu.isad;

import ehu.isad.controllers.LiburuKud;
import ehu.isad.controllers.XehetasunakKud;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Liburuak extends Application {

    private Parent liburuakUI;
    private Parent xehetasunakUI;
    private Stage stage;
    private LiburuKud libKud;
    private XehetasunakKud xehetKud;
    private Scene libScene, xehetScene;

    @Override
    public void start(Stage primaryStage) throws Exception {

        stage = primaryStage;
        pantailakKargatu();

        stage.setTitle("OpenLibrary API");
        stage.setScene(libScene);
        stage.show();
    }

    private void pantailakKargatu() throws IOException {

        //Scenen Loader-ak
        FXMLLoader loaderLiburua = new FXMLLoader(getClass().getResource("/Liburuak.fxml"));
        liburuakUI = (Parent) loaderLiburua.load();
        libKud = loaderLiburua.getController();
        libKud.setMainApp(this);
        libScene = new Scene(liburuakUI);

        FXMLLoader loaderMain = new FXMLLoader(getClass().getResource("/Xehetasunak.fxml"));
        xehetasunakUI = (Parent) loaderMain.load();
        xehetKud = loaderMain.getController();
        xehetKud.setMainApp(this);
        xehetScene = new Scene(xehetasunakUI);

    }

    public void mainErakutsi() {
        stage.setScene(libScene);
        stage.show();
    }

    public void xehetasunakErakutsi(String isbn) {
        Book book = new Book();
        book = book.getBook(isbn);
        xehetKud.datuak(book);
        stage.setScene(xehetScene);
        stage.show();

    }
}

