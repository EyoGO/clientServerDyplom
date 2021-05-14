package eyogo.business;

import java.io.Serializable;
import java.sql.Date;

// Bean
public class Price implements Serializable {

    private int dishID;
    private Date date;
    private double value;

    public Price() {
    }
    public Price(int dishID, Date date, double value) {
        this.dishID = dishID;
        this.date = date;
        this.value = value;
    }

    public int getDishID() {
        return dishID;
    }
    public void setDishID(int dishID) {
        this.dishID = dishID;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }
    public void setValue(double value) {
        this.value = value;
    }
}
