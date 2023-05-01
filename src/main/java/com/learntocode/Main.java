package com.learntocode;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Date thisDate = new Date();
        SimpleDateFormat dateForm = new SimpleDateFormat("YY-MM-dd");
        System.out.println(dateForm.format(thisDate));

        loadInventory("transactions.csv");

    }

    private static void loadInventory(String s) {
        String file = "transactions.csv";
        BufferedReader reader = null;
        String line = "";

        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {

                String[] row = line.split(",");
                for (String index : row) {
                    System.out.printf("%-10s", index);
                }
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 3) {
            System.out.println("Welcome to the Accounting Ledger App!");
            System.out.println("1. Add Deposit");
            System.out.println("2. Make Payment(Debit)");
            System.out.println("3. Ledger");
            System.out.println("4. Exit");
            System.out.println("Enter your choice (1-4): ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    displayEntries(inventory, cart, scanner);
                    break;
                case 2:
                    displayDeposits(cart, scanner, totalAmount);
                    break;
                case 3:
                    displayPayments(cart, scanner, totalAmount);
                    break;
                case 4:
                    displayReports(cart, scanner, totalAmount);
                    break;
                case 5:
                    System.out.println("Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }
}
