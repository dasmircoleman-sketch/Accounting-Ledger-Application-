package com.pluralsight;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Ledger {
    public static void main(String[] args) {
        //
        Scanner myscanner = new Scanner(System.in);
        //
        boolean appProcessing = true;
        while( appProcessing)
        {
            //
            System.out.println("💰💰Ledger Home Screen💰💰");
            System.out.println("Choose an Option:");
            System.out.println("D. Add Deposit");
            System.out.println("P. Make A Payment");
            System.out.println("L. Ledger");
            System.out.println("X. Exit");
            System.out.println("Please Make A Selection");
            String choice = myscanner.nextLine();

            switch (choice.toUpperCase()) {
                case "D":
                System.out.println("Option D. Selected");
                System.out.println("Make a Deposit");
                System.out.println("What is the description of the deposit?");
                String description = myscanner.nextLine();
                System.out.println("Name your Specific Vendor.");
                String vendor = myscanner.nextLine();
                System.out.println("What is the correct amount of the deposit");
                double amount =myscanner.nextDouble();
                Transaction newTransaction = new Transaction(LocalDate.now(), LocalTime.now(),description,vendor,amount);
                    try {
                        BufferedWriter csvToWriteTo =  new BufferedWriter(new FileWriter("src/main/resources/transactions.csv", true));
                        //add to a new line
                        csvToWriteTo.newLine();
                        csvToWriteTo.write(newTransaction.getDate()+"|"+newTransaction.getTime()+
                                "|"+newTransaction.getDescription()+"|"+
                                newTransaction.getVendor()+"|"+newTransaction.getAmount());
                        csvToWriteTo.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Deposit Accepted, Thank you ❤️💰");


                    break;

                case "P":
                    System.out.println("Option P. Selected");
                System.out.println("Make A Payment");
                    System.out.println("Option D. Selected");
                    System.out.println("Make a Payment");
                    System.out.println("What is the description of the payment?");
                    String paymentDescription = myscanner.nextLine();
                    System.out.println("Name your Specific Vendor.");
                    String paymentVendor = myscanner.nextLine();
                    System.out.println("What is the correct amount of the deposit");
                    double paymentAmount =myscanner.nextDouble();
                    Transaction newPayment = new Transaction(LocalDate.now(), LocalTime.now(),paymentDescription,paymentVendor,-paymentAmount);
                    try {
                        BufferedWriter csvToWriteTo =  new BufferedWriter(new FileWriter("src/main/resources/transactions.csv", true));
                        //add to a new line
                        csvToWriteTo.newLine();
                        csvToWriteTo.write(newPayment.getDate()+"|"+newPayment.getTime()+
                                "|"+newPayment.getDescription()+"|"+
                                newPayment.getVendor()+"|"+newPayment.getAmount());
                        csvToWriteTo.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Payment Recieved, Thank you ❤️💰");


                    break;
                break;

                case "L":
                    System.out.println("Option L. Selected");

                System.out.println("ledger Entries");
                System.out.println("A. All");
                System.out.println("D. Deposits");
                System.out.println("Payments");
                System.out.println("Reports");
                break;

                case "R":
                System.out.println("Reports");
                System.out.println("1. Month TO Date");
                System.out.println("2 Previous Month");
                System.out.println("3. Year To DAte");
                System.out.println("4. Previous Year");
                System.out.println("5. Search by Vendor");
                System.out.println("0. Back");
                System.out.println("X. Exit");


                case "X":
                    System.out.println("Option X. Selected");
                    System.out.println("you selected Option X." +
                            "........... Exiting App.");
                    appProcessing = false;
                    break;

                default:
                    System.out.println("Invalid Option. Please Select Again.");


            }
        }
    }

}
