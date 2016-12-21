import javafx.beans.property.SimpleStringProperty;

/**
 * Created by marko on 21/12/2016.
 */
public class Persoon {
    final SimpleStringProperty nimi;
    final SimpleStringProperty skoor;
    public Persoon(String score, String name){
        this.skoor= new SimpleStringProperty(score);
        this.nimi=new SimpleStringProperty(name);
    }
    public String getNimi() {
        return nimi.get();
    }
    public String getSkoor() {
        return skoor.get();
    }
    public void setNimi(String name) {
        nimi.set(name);
    }
    public void setScore(String score) {
        skoor.set(score);
    }
}
