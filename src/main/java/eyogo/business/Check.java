package eyogo.business;

import java.io.Serializable;
import java.sql.Timestamp;

// Bean
public class Check implements Serializable {

    private int id;
    private Timestamp timestamp;

    public Check() {
    }
    public Check(int id, Timestamp timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
