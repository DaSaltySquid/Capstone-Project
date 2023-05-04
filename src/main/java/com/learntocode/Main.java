package com.learntocode;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Main {
    private static ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    private static final String FILE_NAME = "transactions.csv";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String TIME_FORMAT = "HH:mm:ss";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_FORMAT);
    private static LocalTime time;

    public static void main(String[] args) {
        loadTransactions(FILE_NAME);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        boolean running = true;

        while (running) {
            System.out.println("Welcome to TransactionApp");
            System.out.println("Choose an option:");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");


            switch (input.toUpperCase()) {
                case "D":
                    addDeposit(scanner);
                    break;
                case "P":
                    addPayment(scanner);
                    break;
                case "L":
                    ledgerMenu(scanner);
                    break;
                case "X":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }

        scanner.close();
    }

    public static void loadTransactions(String fileName) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        try {
            BufferedReader br = new BufferedReader(new FileReader("transactions.csv"));
            String line;
            while ((line = br.readLine()) != null) ;
            String[] parts = line.split("\\|");
            String date = parts[0];
            String description = parts[1];
            String vendor = parts[2];
            double amount = Double.parseDouble(parts[3]);
            transactions.add(new Transaction(date, time, description, vendor, amount));

        } catch (IOException e) {
            System.out.println("An error has occurred");
        }
    }

    private static void addDeposit(Scanner scanner) {

        // prompt the user for deposit information
        System.out.println("Please enter the deposit information:");
        System.out.print("Date (yyyy-mm-dd): ");
        String date = scanner.nextLine();
        System.out.print("Time (hh:mm:ss): ");
        String time = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();
        System.out.print("Amount: ");
        double amount = scanner.nextDouble();

        // save the deposit information to the csv file
        try {
            File file = new File("transactions.csv");
            FileWriter writer = new FileWriter(file, true);
            writer.write(date + "|" + time + "|" + description + "|" + vendor + "|" + amount + "\n");
            writer.close();
            System.out.println("Deposit added successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

        scanner.close();
    }


    private static void addPayment(Scanner scanner) {
        System.out.println("Enter the payment amount:");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume the newline character

        System.out.println("Enter the payment description:");
        String description = scanner.nextLine();


        Transaction transaction = new Transaction(date, time, description, vendor, amount);
        transactions.add(transaction);

        new Transaction(date, time, description, vendor, amount);
        System.out.println("Payment saved successfully.");
    }



    private static void ledgerMenu(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("Ledger");
            System.out.println("Choose an option:");
            System.out.println("A) All");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");

            String input = scanner.nextLine().trim();

            switch (input.toUpperCase()) {
                case "A":
                    displayLedger();
                    break;
                case "D":
                    displayDeposits();
                    break;
                case "P":
                    displayPayments();
                    break;
                case "R":
                    reportsMenu(scanner);
                    break;
                case "H":
                    running = false;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    private static void displayLedger() {
        System.out.printf("%-12s %-12s %-30s %-20s %-15s%n", "Date", "Time", "Description", "Vendor", "Amount");
        for (Transaction transaction : transactions) {
            System.out.printf("%-12s %-12s %-30s %-20s %-15.2fn", transaction.getDate(),
                transaction.getTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")),
                transaction.getDescription(), transaction.getVendor(), transaction.getAmount());

        }
    }


    private static void displayDeposits() {
        System.out.println("Deposits: ");
        System.out.printf("%-15s %-15s %-30s %-20s %-15s %n", "Date", "Time", "Description", "From", "Amount");

        for (Transaction t : transactions){
            if (t.getAmount() > 0) {
                System.out.printf("%-15s %-15s %-30s %-20s %-15.2f %n", t.getDate(), t.getTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")), t.getDescription(), t.getVendor(), t.getAmount());
            }
        }
    }

    private static void displayPayments() {
        System.out.format("%-15s %-15s %-30s %-20s %-15s git sta%n", "Date", "Time", "Description", "From", "Amount");
        System.out.println("---------------------------------------------------------------");
        for (Transaction t : transactions) {
            if (t.getAmount() < 0) {
                System.out.printf("%-15s %-15s %-30s %-20s %-15s %n", t.getDate(), t.getTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")), t.getDescription(), t.getVendor(), t.getAmount());
            }
        }
    }


    private static void reportsMenu(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("Reports");
            System.out.println("Choose an option:");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    // Generate a report for all transactions within the current month,
                    // including the date, vendor, and amount for each transaction.
                    // The report should include a total of all transaction amounts for the month.
                case "2":
                    // Generate a report for all transactions within the previous month,
                    // including the date, vendor, and amount for each transaction.
                    // The report should include a total of all transaction amounts for the month.
                case "3":
                    // Generate a report for all transactions within the current year,
                    // including the date, vendor, and amount for each transaction.
                    // The report should include a total of all transaction amounts for the year.

                case "4":
                    // Generate a report for all transactions within the previous year,
                    // including the date, vendor, and amount for each transaction.
                    // The report should include a total of all transaction amounts for the year.
                case "5":
                    // Prompt the user to enter a vendor name, then generate a report for all transactions
                    // with that vendor, including the date, vendor, and amount for each transaction.
                    // The report should include a total of all transaction amounts for the vendor.
                case "0":
                    running = false;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }


    private static void filterTransactionsByDate(LocalDate startDate, LocalDate endDate) {
        // This method filters the transactions by date and prints a report to the console.
        // It takes two parameters: startDate and endDate, which represent the range of dates to filter by.
        // The method loops through the transactions list and checks each transaction's date against the date range.
        // Transactions that fall within the date range are printed to the console.
        // If no transactions fall within the date range, the method prints a message indicating that there are no results.
    }

    private static void filterTransactionsByVendor(String vendor) {
        // This method filters the transactions by vendor and prints a report to the console.
        // It takes one parameter: vendor, which represents the name of the vendor to filter by.
        // The method loops through the transactions list and checks each transaction's vendor name against the specified vendor name.
        // Transactions with a matching vendor name are printed to the console.
        // If no transactions match the specified vendor name, the method prints a message indicating that there are no results.
    }
}




