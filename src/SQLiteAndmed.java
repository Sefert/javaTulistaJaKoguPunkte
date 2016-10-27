import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Sefert on 27.10.2016.
 */
public class SQLiteAndmed {

    public static void main( String args[] ){
        Connection c=null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            //c = DriverManager.getConnection("C:\\javaTulistaJaKoguPunkte\\src\\highScore.db");
        }catch (Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
}
