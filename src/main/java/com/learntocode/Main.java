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

    public static void main(String[] args) throws IOException {
        loadTransactions(FILE_NAME);
        Scanner scanner = new Scanner(System.in);
        String choice = "";

        while (!choice.equalsIgnoreCase("x")) {
            System.out.println("Welcome to the TransactionApp Home Screen \n-----------");
            System.out.println("Choose an option:");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");

            choice = scanner.nextLine().trim();

            switch (choice.toUpperCase()) {
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
                    System.out.println("Exiting the application.");
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
                    break;
            }
        }

    }


    private static void addDeposit(Scanner scanner) throws IOException {

        try {
            System.out.println("Please enter the deposit information:");
            System.out.print("Enter date (yyyy-mm-dd): ");
            String date = scanner.nextLine();

            System.out.print("Enter time (hh:mm:ss): ");
            String time = scanner.nextLine();

            System.out.print("Enter description: ");
            String description = scanner.nextLine();

            System.out.print("Enter vendor: ");
            String vendor = scanner.nextLine();

            System.out.print("Enter amount: ");
            double amount = scanner.nextDouble();

            FileWriter writer = new FileWriter(FILE_NAME, true);
            writer.write(date + "|" + time + "|" + description + "|" + vendor + "|" + amount + "\n");

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static void addPayment(Scanner scanner) {
        try {

            System.out.println("Enter your debit information");
            System.out.println("Enter date: ");
            String date = scanner.nextLine();

            System.out.println("Enter time: ");
            String time = scanner.nextLine();

            System.out.println("Enter description: ");
            String description = scanner.nextLine();

            System.out.println("Enter vendor: ");
            String vendor = scanner.nextLine();

            System.out.println("Enter amount: ");
            double amount = scanner.nextDouble();

            //date|time|description|vendor|amount
            FileWriter writer = new FileWriter(FILE_NAME, true);
            writer.write(date + "|" + time + "|" + description + "|" + vendor + "|" + amount + "\n");


            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void displayLedger() throws IOException {
        System.out.println("Ledger: ");
        System.out.printf("%-15s %-15s %-30s %-20s %-15s %n", "Date", "Time", "Description", "From", "Amount");

        for (Transaction t : transactions) {
            System.out.printf("%-15s %-15s %-30s %-20s %-15.2f %n", t.getDate(), t.getTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")), t.getDescription(), t.getVendor(), t.getAmount());

        }
    }

    private static void ledgerMenu(Scanner scanner) throws IOException {
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


    private static void displayDeposits() {
        System.out.println("Deposits: ");
        System.out.printf("%-15s %-15s %-30s %-20s %-15s %n", "Date", "Time", "Description", "From", "Amount");

        for (Transaction t : transactions) {
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
                    for (Transaction transaction : transactions) {
                        LocalDate date = LocalDate.now();

                        if (transaction.getDate().getMonth() == date.getMonth() && transaction.getDate().getYear() == date.getYear()) {
                            System.out.println(transaction.getDate() + " " + transaction.getTime() + " " + transaction.getDescription() + " " + transaction.getVendor() + " " + transaction.getAmount());


                        }
                    }
                    break;


                case "2":
                    for (Transaction transaction : transactions) {
                        LocalDate date = LocalDate.now();
                        LocalDate previousMonthDate = date.minusMonths(1);

                        if (transaction.getDate().getMonth() == previousMonthDate.getMonth() && transaction.getDate().getYear() == date.getYear()) {
                            System.out.println(transaction.getDate() + " " + transaction.getTime() + " " + transaction.getDescription() + " " + transaction.getVendor() + " " + transaction.getAmount());
                        }
                    }
                    break;


                case "3":

                    for (Transaction transaction : transactions) {
                        LocalDate date = LocalDate.now();

                        if (transaction.getDate().getYear() == date.getYear()) {
                            System.out.println(transaction.getDate() + " " + transaction.getTime() + " " + transaction.getDescription() + " " + transaction.getVendor() + " " + transaction.getAmount());
                        }
                    }
                    break;


                case "4":
                    for (Transaction transaction : transactions) {
                        LocalDate date = LocalDate.now();
                        LocalDate previousYearDate = date.minusYears(1);

                        if (transaction.getDate().getYear() == previousYearDate.getYear()) {
                            System.out.println(transaction.getDate() + " " + transaction.getTime() + " " + transaction.getDescription() + " " + transaction.getVendor() + " " + transaction.getAmount());
                        }
                    }
                    break;


                case "5":
                    filterTransactionsByVendor();
                    break;


                case "0":
                    running = false;

                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }


    private static void filterTransactionsByDate(LocalDate startDate, LocalDate endDate) {

    }

    private static void filterTransactionsByVendor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the vendor name: ");
        String choice = scanner.nextLine();

        for (Transaction transaction : transactions) {

            if (transaction.getVendor().equalsIgnoreCase(choice)) {
                System.out.println(transaction.getDate() + " " + transaction.getTime() + " " + transaction.getDescription() + " " + transaction.getVendor() + " " + transaction.getAmount());

            }

        }

    }

    public static void loadTransactions(String fileName) {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Failed to create transactions file");
                return;
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length != 5) {
                    System.err.println("Invalid transaction format: " + line);
                    continue;
                }

                LocalDate date = LocalDate.parse(parts[0], DATE_FORMATTER);
                LocalTime time = LocalTime.parse(parts[1], TIME_FORMATTER);
                String description = parts[2];
                String vendor = parts[3].toUpperCase();
                double amount = Double.parseDouble(parts[4]);

                transactions.add(new Transaction(date, time, description, vendor, amount));
            }
        } catch (IOException e) {
            System.err.println("Failed to read transactions file");
        }
    }
}





