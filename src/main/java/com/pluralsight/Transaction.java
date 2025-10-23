package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

public class Transaction {

    // time date vendor , description amount
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    // constructor created within the class
    // constructor and class holds same name & similar properties
    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public static void printTransactions(List<Transaction> transactions) {

        transactions.sort(Comparator.comparing(Transaction::getDate).thenComparing(Transaction::getTime).reversed());

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

        String header = ("\nDate\t\t| Time\t\t | Description\t\t\t\t\t\t\t\t\t\t\t\t   | Vendor\t\t\t\t   |   Amount\n");
        System.out.print(header);

        for (Transaction t : transactions) {
            String formattedDate = t.getDate().format(dateFormat);
            String formattedTime = t.getTime().format(timeFormat);

            String formatTrans = String.format("%-12s| %-11s| %-60s| %-22s|%10.2f\n",
                    formattedDate, formattedTime, t.getDescription(), t.getVendor(), t.getAmount());
            System.out.print(formatTrans);
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
