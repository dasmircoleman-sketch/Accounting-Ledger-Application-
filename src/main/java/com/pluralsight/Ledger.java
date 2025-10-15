package com.pluralsight;

import java.awt.*;
import java.util.Scanner;

public class Ledger {
    public static void main(String[] args) {
        Scanner myscanner = new Scanner(System.in);
        String choice = myscanner.nextLine();
        boolean isRight;
        while( true)
        {

            switch (choice) {
                case "C":
                    System.out.println("💰💰Ledger Home Screen💰💰");
                    System.out.println("Choose an Option:");
                    System.out.println("D. Add Deposit");
                    System.out.println("P. Make A Payment");
                    System.out.println("L. Ledger");
                    System.out.println("X. Exit");

                    System.out.println("Please Make A Selection");

                    break;
                case "D":

                    System.out.println("Option D. Selected");

                System.out.println("Make a Deposit");
                System.out.println("please select an Acount to Make deposit");
                System.out.println(" C. Checkings");
                System.out.println("S. Savings");
                System.out.println("Account Money Transfer");
                System.out.println("Deposit Check");
                System.out.println("deposit Money Order");

                break;

                case "P":
                    System.out.println("Option P. Selected");

                System.out.println("Make A Payment");
                System.out.println("Pay Credit Cards");
                System.out.println("Pay Loans");
                System.out.println(" 401k or IRA Contributions");
                System.out.println("Pay Recurring Bills & Subscriptions");

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
                    break;

                default:
                    System.out.println("Invalid Option. Please Select Again.");


            }
        }
    }

}
