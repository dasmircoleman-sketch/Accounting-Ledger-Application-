package com.pluralsight;

import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import static com.pluralsight.Transaction.printTransactions;

public class Ledger {
    public static ArrayList<Transaction> transactionArrayList = new ArrayList<Transaction>();

    public static void main(String[] args) {
        //

        Scanner myscanner = new Scanner(System.in);
        //
        // code starts inside a boolean loop
        //  transaction objects print out. ,
        // sout needs the transaction class to override to string class overrides
        //  . If it doesn’t, you’ll just see memory references like . transaction783920
    //@Override
        //public String toString() {
        //    return date + " | " + time + " | " + description + " | " + vendor + " | " + amount;
        //}