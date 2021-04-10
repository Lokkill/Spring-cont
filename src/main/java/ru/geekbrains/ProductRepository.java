package ru.geekbrains;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    @PostConstruct
    public void init() {
        products = new ArrayList<>(Arrays.asList(
                new Product(1L, "Bublik", 100),
                new Product(2L, "Bread", 200),
                new Product(3L, "Milk", 150),
                new Product(4L, "Eggs", 120),
                new Product(5L, "Water", 50)
        ));
    }

    public Product getProductById(Long id) {
        try {
            return products.get(Math.toIntExact(id) - 1);
        } catch (Exception e) {
            System.out.println("Продукта с таким id - нет, попробуйте снова");
            return null;
        }
    }

    public void getProductsList() {
        for (Product p : products) {
            System.out.println(p);
        }
    }

    public void getProductsList(Long id) {
        try {
            System.out.println(products.get(Math.toIntExact(id - 1)));
        } catch (Exception e) {
            System.out.println("Продукта с таким id - нет, попробуйте снова");
        }
    }
}
