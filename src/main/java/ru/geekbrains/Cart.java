package ru.geekbrains;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

@Component
@Scope("prototype")
public class Cart {
    @Autowired
    private ProductRepository repository;

    private Map<Long, Product> userCart;

    private AtomicLong id = new AtomicLong(0);

    @PostConstruct
    public void init() {
        userCart = new HashMap<>();
    }

    public void addToCart(Long idProduct) {
        try {
            userCart.put(id.incrementAndGet(), repository.getProductById(idProduct));
        } catch (Exception e) {
            System.out.println("Продукта с таким id - нет, попробуйте снова");
        }
    }

    public void removeFromCart(Long id) {
        try {
            userCart.remove(id);
        } catch (Exception e) {
            System.out.println("Продукта с таким id - нет, попробуйте снова");
        }
    }

    public void getCartList() {
        Stream.of(userCart).forEach(System.out::println);
    }
}
