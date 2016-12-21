import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Sefert on 27.10.2016.
 */
public class SQLiteAndmed {
    Connection c=null;
    Statement stmt = null;
    public ObservableList<Persoon> persoonid= FXCollections.observableArrayList();

    public void uhenda(){
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:highScore.db");
        }catch (Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
    public void sulge(){
        try {
            Class.forName("org.sqlite.JDBC");
            c.close();
        }catch (Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Closed database successfully");
    }
    public void lisaKasutaja(String nimi, int fullscore){
        try {
            stmt=c.createStatement();
            String sql="INSERT INTO HighScore (Nimi, Score) VALUES ('"+ nimi +"', "+ fullscore +")";
            stmt.execute(sql);
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
        sulge();
    }
    public void loeAndmed(){
        uhenda();
        try {
            stmt=c.createStatement();
            ResultSet sql=stmt.executeQuery( "SELECT * FROM HighScore ORDER BY Score DESC" );
            while ( sql.next() ) {
                persoonid.add(new Persoon(
                        sql.getString("Score"),
                        sql.getString("Nimi")
                ));
            }
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Database read successfully");
        sulge();
    }
}
