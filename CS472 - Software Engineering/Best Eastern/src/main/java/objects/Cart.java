package objects;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Integer> items; //List of ints IE: reservationID 1,2

    public Cart(List<Integer> items) {
        this.items = items;
    }

    public void addItem(int item) {
        items.add(item);
    }

    public void removeItem(int item) {
        items.remove(Integer.valueOf(item));
    }

    public void removeItems() {
        this.items = new ArrayList<>();
    }

    public List<Integer> getItems() {
        return items;
    }

    public void setItems(List<Integer> items) {
        this.items = items;
    }
}
