package eyogo.business;

import java.io.Serializable;

// Bean
public class DishCheck implements Serializable {

    private int dishID;
    private int checkID;
    private int count;

    public DishCheck() {
    }
    public DishCheck(int dishID, int checkID, int count) {
        this.dishID = dishID;
        this.checkID = checkID;
        this.count = count;
    }

    public int getDishID() {
        return dishID;
    }
    public void setDishID(int dishID) {
        this.dishID = dishID;
    }

    public int getCheckID() {
        return checkID;
    }
    public void setCheckID(int checkID) {
        this.checkID = checkID;
    }

    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
}
