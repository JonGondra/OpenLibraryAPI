package ehu.isad.controllers.db;

import ehu.isad.Book;
import ehu.isad.utils.Sarea;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OpenLibraryKud {
    private static final OpenLibraryKud instance = new OpenLibraryKud();

    //singleton patroia
    public static OpenLibraryKud getInstance() {
        return instance;
    }

    private OpenLibraryKud() {
    }
    public List<String> lortuLiburuak() {

        String query = "select isbn,izena from liburua";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        List<String> emaitza = new ArrayList<>();
        try {
            while (rs.next()) {

                String isbn = rs.getString("isbn");
                String izena = rs.getString("izena");

                System.out.println(isbn+ ":" + izena);
                emaitza.add(izena);

            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return emaitza;
    }

    public String lortuisbn(String izena){
        String isbn = "";

        String query = "select isbn from liburua where izena= '" + izena + "'";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);


        try {
            while (rs.next()) {

                isbn = rs.getString("isbn");

            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return isbn;
    }

    public boolean dbBegiratu(String izena){
        boolean aurkituta=true;
        String isbn = "";

        String query = "select isbn from liburua where izena= '" + izena + "'";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        try {
            while (rs.next()) {

                if (rs.getString("isbn")==""){
                    aurkituta = false;
                }

            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return aurkituta;
    }

    public void dbSartu(String isbn) throws IOException {
        Book book = new Book();
        book = book.getBook(isbn);

        String query = "INSERT INTO liburua (isbn,izena) VALUES ('" + book.getIsbn() + "','"+ book.getDetails().getTitle() + "')" ;
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        this.sartuArg(book);
        this.irudiaGorde(book);
    }


    private void sartuArg(Book book) {
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        for(int i=0; i< book.getDetails().getPublishers().length; i++) {
            if (!this.badagoArg(book.getDetails().getPublishers()[i])) {
                String query = "INSERT INTO argitaletxea VALUES (\"" + book.getDetails().getPublishers()[i] + "\")";
                dbKudeatzaile.execSQL(query);
            }
            String query = "INSERT INTO arglista VALUES (" + book.getIsbn()+")"+ "\"" + book.getDetails().getPublishers()[i]+ "\")";
            dbKudeatzaile.execSQL(query);
        }

    }

    private boolean badagoArg(String publisher) {
        boolean aurkituta=true;
        String query = "select izena from argitaletxea where izena= \"" + publisher + "\"";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        try {
                    aurkituta = rs.next();
                } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return aurkituta;
    }

    private void irudiaGorde(Book book) throws IOException {
        Sarea s = new Sarea();
        s.irudiaGorde(book.getThumbnail_url(), book.getIsbn());
    }


    public String libdatuak(String isbn) {

        String datuak="";

        String query = "select izena, orrikop, irudipath  from liburua where isbn= '" + isbn + "'";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);


        try {
            while (rs.next()) {
                datuak = rs.getString("izena") + "," + rs.getInt("orrikop") + "," + rs.getString("irudipath");
            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return datuak;

    }

    public List<String> arglistaLortu(String isbn) {
        List<String> arglista = new ArrayList<>();

        String query = "select izenaarg from arglista where isbnlib= '" + isbn + "'";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);


        try {
            while (rs.next()) {
                arglista.add(rs.getString("izenaarg"));
            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return arglista;
    }
}
