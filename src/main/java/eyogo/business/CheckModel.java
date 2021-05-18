package eyogo.business;

import java.io.Serializable;

public class CheckModel implements Serializable {
    private DishCounter dishCounter;
    private Price price;

    public CheckModel(){
    }
    public CheckModel(DishCounter dishCounter, Price price) {
        this.dishCounter = dishCounter;
        this.price = price;
    }

    public DishCounter getDishCounter() {
        return dishCounter;
    }
    public void setDishCounter(DishCounter dishCounter) {
        this.dishCounter = dishCounter;
    }

    public Price getPrice() {
        return price;
    }
    public void setPrice(Price price) {
        this.price = price;
    }
}
