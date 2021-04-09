package ru.geekbrains;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("ru.geekbrains");
        ProductRepository repository = context.getBean("productRepository", ProductRepository.class);
        Cart cart = context.getBean("cart", Cart.class);
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("-----------------------");
            System.out.println("Введите команду: \n\t" +
                    "1. Получить список товаров \n\t" +
                    "2. Получить товар по id \n\t" +
                    "3. Вывести корзину \n\t" +
                    "4. Добавить товар в корзину (по id) \n\t" +
                    "5. Удалить товар из корзины (по id) \n\t" +
                    "6. Выход");
            switch (scanner.nextLine()){
                case "1":
                    repository.getProductsList();
                    break;
                case "2":
                    repository.getProductsList(checkCorrectValue(scanner));
                    break;
                case "3":
                    cart.getCartList();
                    break;
                case "4":
                    cart.addToCart(checkCorrectValue(scanner));
                    break;
                case "5":
                    cart.removeFromCart(checkCorrectValue(scanner));
                    break;
                case "6":
                    context.close();
                    System.exit(-1);
            }
        }
    }

    public static Long checkCorrectValue(Scanner scanner){
        try {
            System.out.println("Введите число: ");
            return Long.parseLong(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Некорректно введено значение, попробуйте снова");
            return checkCorrectValue(scanner);
        }
    }
}
