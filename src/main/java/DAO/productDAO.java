package DAO;

import model.product;
import java.util.*;

public class productDAO {
    private static final List<product> products = new ArrayList<>();
    private static int counter = 1;

    public List<product> listAll() {
        return new ArrayList<>(products);
    }

    public void add(String name, double price) {
        products.add(new product(counter++, name, price));
    }

    public product getById(int id) {
        return products.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public void update(int id, String name, double price) {
        product p = getById(id);
        if (p != null) {
            p.setName(name);
            p.setPrice(price);
        }
    }

    public void delete(int id) {
        products.removeIf(p -> p.getId() == id);
    }
}
