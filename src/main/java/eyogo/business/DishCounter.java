package eyogo.business;

import java.util.Objects;

public class DishCounter {
    private Dish dish;
    private int count;

    public DishCounter(){

    }
    public DishCounter(Dish dish, int count) {
        this.dish = dish;
        this.count = count;
    }

    public Dish getDish() {
        return dish;
    }
    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishCounter that = (DishCounter) o;
        return count == that.count &&
                Objects.equals(dish, that.dish);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dish, count);
    }
}
