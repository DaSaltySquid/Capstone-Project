package com.learntocode;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
            while((line = reader.readLine()) != null) {

                String[] row = line.split(",");
                for(String index : row) {
                    System.out.printf("%-10s", index);
                }
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        
    }
}
