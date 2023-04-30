package com.learntocode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static com.learntocode.OnlineStoreMain.scanner;

public class Product {
    private String id;
    private String name;
    private float price;
    private static List<Product> cart = new ArrayList<>();

    public Product(String id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public static List<Product> readProductFromCSV(String csvFile) {
        List<Product> products = new ArrayList<>();

        // String csvFile = "products.csv";
        String line = " ";
        String cvsSplitBy = "\\|";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile))) {
            while ((line = bufferedReader.readLine()) != null) {
                String[] productInfo = line.split(cvsSplitBy);
                String id = productInfo[0];
                String name = productInfo[1];
                Float price = Float.parseFloat(productInfo[2]);
                products.add(new Product(id, name, price));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public static void displayAvailableProduct() {
        try {
            System.out.println("Available products: ");
            System.out.println("-------------------------");

            List<Product> products = readProductFromCSV("storeinventory.csv");

            for (int i = 0; i < products.size(); i++) {
                Product product = products.get(i);
                System.out.printf("%s - %s - $%.2f\n", product.getId(), product.getName(), product.getPrice());
            }
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter the ID of the item you would like to add to your cart, or enter X to go back to the home screen: ");
            String command = scanner.nextLine();

            if (command.equalsIgnoreCase("x")) {
                return;

            }
            for (Product product : products) {
                if (command.equals(product.getId())) {
                    addItemToCart(product, cart);
                    return;
                }

            }
            throw new Exception("Invalid command entered.");
        } catch (Exception e) {
            System.out.println("An error occured while displaying the available products. ");
            System.out.println("------------------------------------------------------------");
        }

    }

    public static void addItemToCart(Product item, List<Product> cart) {
        cart.add(item);
        System.out.println(item.getName() + " has been added to your cart.");
        System.out.println("------------------------------------------------");
        return;
    }

    public static void displayCart() {
        try {
            System.out.println("Here are the items in your cart: ");
            System.out.println("-----------------------------------");

            if (cart.size() == 0) {
                System.out.println("Your cart is empty. ");
                System.out.println("----------------------------");
                return;

            } else {
                for (Product product : cart) {
                    System.out.printf("%s - %s - $%.2f\n", product.getId(), product.getName(), product.getPrice());
                }
            }
            Scanner scanner = new Scanner(System.in);
            String choice;
            do {
                System.out.println("--------------------------------------------------------------------------------");
                System.out.println("What would you like to do? 'C' to check out or 'X' to go back to the home-screen. ");

                choice = scanner.nextLine();
            } while (!choice.equalsIgnoreCase("C") && !choice.equalsIgnoreCase("X"));

            if (choice.equalsIgnoreCase("X")) {
                return;
            }
            checkOut();
        } catch (Exception e) {
            System.out.println("An error occured.");
            System.out.println("-------------------");
        }
    }

    public static void showAllItemsInCart() {
        if (cart.size() == 0) {
            System.out.println("Your cart is empty.");
        } else {
            for (int i = 0; i < cart.size(); i++) {
                Product product = cart.get(i);
                System.out.printf("%s, %s, $%.2f\n", product.getId(), product.getName(), product.getPrice());
            }

        }
    }

    public static void checkOut() {
        try {
            float totalAmountOwed = 0.0f;

            for (int i = 0; i < cart.size(); i++) {
                Product product = cart.get(i);
                totalAmountOwed += product.getPrice();

                Scanner scanner = new Scanner(System.in);

            }
            System.out.println("Total amount owed: " + String.format("%.2f", totalAmountOwed));
            System.out.println("Please provide payment: ");
            float payment = scanner.nextFloat();
            float change = payment - totalAmountOwed;

            if (payment < totalAmountOwed) {
                System.out.println("Insufficient funds. The amount " + payment + " has been returned to you.");
                System.out.println("------------------------------------------------------------------------");
            }

            if (payment == totalAmountOwed) {
                System.out.println("Thank you for your payment. You provided exact change. Here is a summary of your order: ");
                System.out.println("-------------------------------------------------------------------");
                showAllItemsInCart();
            }

            if (payment > totalAmountOwed) {
                System.out.printf("Thank you for your payment. Your change is $%.2f\n", change);
                System.out.println("Here is a summary of your order: ");
                System.out.println("------------------------------------------------------------------------");
                showAllItemsInCart();
                cart.clear();
                System.out.println("----------------------------------");
                return;

            } else {
                return;
            }
        } catch (Exception e) {
            System.out.println("An error occured.");
            System.out.println("-------------------");
        }
    }
}




