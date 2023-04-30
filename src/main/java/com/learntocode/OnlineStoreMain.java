package com.learntocode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import  static com.learntocode.Product.displayCart;

import static com.learntocode.Product.displayAvailableProduct;
import static com.learntocode.Product.displayCart;

public class OnlineStoreMain {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            try {

                System.out.println("Welcome to The Store, one stop shop for all your shopping needs.");
                System.out.println("-----------------------------------------------------------------");
                System.out.println("What would you like to do?");
                System.out.println("----------------------------");
                System.out.println("Please enter 1 to Show Products, 2 to Show your Cart or 3 to Exit");

                int command = scanner.nextInt();
                scanner.nextLine();
                if ((command <= 0 || command > 3)) {
                    System.out.println("Error occured.");
                    System.out.println("---------------------------------------");
                    System.out.println("Please enter a number between 1 and 3: ");
                } else {

                    switch (command) {
                        case 1:
                            displayAvailableProduct();
                            break;
                        case 2:
                            displayCart();
                            break;
                        case 3:
                            System.out.println("See you next time! ");
                            return;

                    }
                }
            } catch(Exception e){
                    System.out.println("There was an error.");
                    System.out.println("----------------------");
                    scanner.nextLine();
                }
            }
        }
    }
