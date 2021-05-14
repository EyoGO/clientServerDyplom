package eyogo.business;

import java.io.Serializable;
import java.sql.Date;

// Bean
public class Check implements Serializable {

    private int id;
    private Date date;

    public Check() {
    }
    public Check(int id, Date date) {
        this.id = id;
        this.date = date;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
}
