package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import static com.pluralsight.Transaction.printTransactions;

public class Ledger {
    public static ArrayList<Transaction> transactionArrayList = new ArrayList<Transaction>();

    public static void main(String[] args) throws IOException {
        //

        Scanner myscanner = new Scanner(System.in);
        // code starts inside a boolean loop
        // code starts inside a boolean loop
        boolean appProcessing = true;
        while (appProcessing) {
            //
            System.out.println("hi");
            System.out.println("💰💰Ledger " + "Home Screen💰💰");
            System.out.println("Choose an Option:");
            System.out.println("D. Add Deposit");
            System.out.println("P. Make A Payment");
            System.out.println("L. Ledger");
            System.out.println("X. Exit");
            System.out.println("Please Make A Selection");
            String choice = myscanner.nextLine();


            switch (choice.toUpperCase().trim()) {
                case "D":
                    System.out.println("Option D. Selected");
                    System.out.println("Make a Deposit");
                    System.out.println("What is the description of the deposit?");
                    String description = myscanner.nextLine();
                    System.out.println("Name your Specific Vendor.");
                    String vendor = myscanner.nextLine();
                    System.out.println("What is the correct amount of the deposit");
                    double amount = myscanner.nextDouble();
                    Transaction newTransaction = new Transaction(LocalDate.now(), LocalTime.now(), description, vendor, amount);
                    try {
                        BufferedWriter csvToWriteTo = new BufferedWriter(new FileWriter("src/main/resources/transactions.csv", true));
                        //add to a new line
                        csvToWriteTo.newLine();
                        csvToWriteTo.write(newTransaction.getDate() + "|" + newTransaction.getTime() +
                                "|" + newTransaction.getDescription() + "|" +
                                newTransaction.getVendor() + "|" + newTransaction.getAmount());
                        csvToWriteTo.close();
                    } catch (IOException e) {
                        //throw new RuntimeException(e);
                        System.out.println("ERROR: " + e.getLocalizedMessage());
                    }
                    System.out.println("Deposit Accepted, Thank you ❤️💰");

                    break;

                case "P":
                    System.out.println("Option P. Selected");
                    System.out.println("Make A Payment");
                    System.out.println("What is the description of the payment?");
                    String paymentDescription = myscanner.nextLine();
                    System.out.println("Name your Specific Vendor.");
                    String paymentVendor = myscanner.nextLine();
                    System.out.println("What is the correct amount of the deposit");
                    double paymentAmount = myscanner.nextDouble();
                    Transaction newPayment = new Transaction(LocalDate.now(), LocalTime.now(), paymentDescription, paymentVendor, -paymentAmount);
                    try {
                        BufferedWriter csvToWriteTo = new BufferedWriter(new FileWriter("src/main/resources/transactions.csv", true));
                        //add to a new line
                        csvToWriteTo.newLine();
                        csvToWriteTo.write(newPayment.getDate() + "|" + newPayment.getTime() +
                                "|" + newPayment.getDescription() + "|" +
                                newPayment.getVendor() + "|" + newPayment.getAmount());
                        csvToWriteTo.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Payment Received, Thank you ❤️💰");
                    break;

                case "L":
                    try {
                        //create the buffered reader
                        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/transactions.csv"));

                        //ignore the header row
                        String line = reader.readLine();

                        // Remaining lines: transaction records
                        while ((line = reader.readLine()) != null) {
                            //split into parts
                            String[] parts = line.split("\\|");

                            if (parts[0].equalsIgnoreCase("date")) {
                                continue;
                            }

                            try {
                                //save the parts into variables and convert the data where apporpriate
                                LocalDate date = LocalDate.parse(parts[0]);
                                LocalTime time = LocalTime.parse(parts[1]);
                                String descriptionR = parts[2];
                                String vendorR = parts[3];
                                double amountR = Double.parseDouble(parts[4]);

                                //create a transaction from the data
                                Transaction newTransactionR = new Transaction(date, time, descriptionR, vendorR, amountR);
                                //add the transaction to the array list
                                transactionArrayList.add(newTransactionR);
                            } catch (DateTimeParseException e) {
                                System.out.println();
                            }
                        }
                        //formatted transactions
                        // printTransactions(transactionArrayList);

                        reader.close();
                    } catch (IOException e) {
                        System.out.println("Error reading transaction file: " + e.getMessage());
                    }


                    boolean inLedgerMenu = true;
                    while (inLedgerMenu) {

                        System.out.println("Option L. Selected");
                        System.out.println("ledger Entries");
                        System.out.println("A. All");
                        System.out.println("D. Deposits");
                        System.out.println("P. Payments");
                        System.out.println("R. Reports");
                        System.out.println("H. Return to Home Screen");
                        System.out.println("Please Make A Selection");
                        String selection = myscanner.nextLine();
                        switch (selection.toUpperCase()) {
                            case "A":
                                //run a for loop over transaction array list
                                //print each line out
                                // Display all entries (newest first)
                                // for (int i = transactionArrayList.size() - 1; i >= 0; i--) {
                                //   Transaction t = transactionArrayList.get(i);
                                // System.out.println(t); // relies on Transaction.toString()
                                //  }
                                printTransactions(transactionArrayList);
                                break;
                            case "D":
                                // Display only deposits (amount > 0)
                                //   for (int i = transactionArrayList.size() - 1; i >= 0; i--) {
                                // Transaction t = transactionArrayList.get(i);
                                //if (t.getAmount() > 0) {
                                //   System.out.println(t);
                                //}
                                // }

                                transactionArrayList.sort(Comparator.comparing(Transaction::getDate).thenComparing(Transaction::getTime).reversed());

                                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                                DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

                                String header = ("\nDate\t\t| Time\t\t | Description\t\t\t\t\t\t\t\t\t\t\t\t   | Vendor\t\t\t\t   |   Amount\n");
                                System.out.print(header);

                                for (Transaction t : transactionArrayList) {
                                    if (t.getAmount() > 0) {
                                        String formattedDate = t.getDate().format(dateFormat);
                                        String formattedTime = t.getTime().format(timeFormat);

                                        String formatTrans = String.format("%-12s| %-11s| %-60s| %-22s|%10.2f\n",
                                                formattedDate, formattedTime, t.getDescription(), t.getVendor(), t.getAmount());
                                        System.out.print(formatTrans);
                                    }

                                }
                                break;
                            case "P":
                                // Display only payments (amount < 0)
                                transactionArrayList.sort(Comparator.comparing(Transaction::getDate).thenComparing(Transaction::getTime).reversed());

                                DateTimeFormatter dateP = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                                DateTimeFormatter timeP = DateTimeFormatter.ofPattern("HH:mm:ss");

                                String headerP = ("\nDate\t\t| Time\t\t | Description\t\t\t\t\t\t\t\t\t\t\t\t   | Vendor\t\t\t\t   |   Amount\n");
                                System.out.print(headerP);

                                for (Transaction t : transactionArrayList) {
                                    if (t.getAmount() < 0) {
                                        String formattedDate = t.getDate().format(dateP);
                                        String formattedTime = t.getTime().format(timeP);

                                        String formatTrans = String.format("%-12s| %-11s| %-60s| %-22s|%10.2f\n",
                                                formattedDate, formattedTime, t.getDescription(), t.getVendor(), t.getAmount());
                                        System.out.print(formatTrans);
                                    }

                                }
                                break;
                            case "R":
                                // Reports menu (you can nest another switch here)
                                boolean inReportsMenu = true;
                                while (inReportsMenu) {
                                    System.out.println("Reports Menu");
                                    System.out.println("1. Month To Date");
                                    System.out.println("2. Previous Month");
                                    System.out.println("3. Year To Date");
                                    System.out.println("4. Previous Year");
                                    System.out.println("5. Search by Vendor");
                                    System.out.println("0. Back");
                                    String reportSelection = myscanner.nextLine();
                                    // handle reportSelection with another switch
                                    switch (reportSelection) {
                                        case "1":
                                            System.out.println("Running Month To Date report...");
                                            // filter transactionArrayList by current month
                                            transactionArrayList.sort(Comparator.comparing(Transaction::getDate).thenComparing(Transaction::getTime).reversed());
                                            for (Transaction t : transactionArrayList) {

                                                if (t.getDate().getMonth() == LocalDate.now().getMonth() &&
                                                        t.getDate().getYear() == LocalDate.now().getYear()) {

                                                    DateTimeFormatter mTD = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                                                    String headerMonToD = ("\nDate\t\t| Description\t\t\t\t\t\t\t\t\t\t\t\t   | Vendor\t\t\t\t   |   Amount\n");
                                                    String formattedDate = t.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                                                    String formatTrans = String.format("%-12s| %-60s| %-22s|%10.2f\n",
                                                            formattedDate, t.getDescription(), t.getVendor(), t.getAmount());
                                                    System.out.print(formatTrans);
                                                }
                                            }
                                            break;
                                        case "2":
                                            System.out.println("Running Previous Month report...");
                                            LocalDate now = LocalDate.now();
                                            LocalDate prevMonth = now.minusMonths(1);
                                            transactionArrayList.sort(Comparator.comparing(Transaction::getDate).thenComparing(Transaction::getTime).reversed());
                                            for (Transaction t : transactionArrayList) {

                                                if (t.getDate().getMonth() == prevMonth.getMonth() &&
                                                        t.getDate().getYear() == prevMonth.getYear()) {
                                                    DateTimeFormatter mTD = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                                                    String headerMonToD = ("\nDate\t\t| Description\t\t\t\t\t\t\t\t\t\t\t\t   | Vendor\t\t\t\t   |   Amount\n");
                                                    String formattedDate = t.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                                                    String formatTrans = String.format("%-12s| %-60s| %-22s|%10.2f\n",
                                                            formattedDate, t.getDescription(), t.getVendor(), t.getAmount());
                                                    System.out.print(formatTrans);
                                                }
                                            }
                                            break;
                                        case "3":
                                            System.out.println("Running Year To Date report...");
                                            int currentYear = LocalDate.now().getYear();
                                            transactionArrayList.sort(Comparator.comparing(Transaction::getDate).thenComparing(Transaction::getTime).reversed());
                                            for (Transaction t : transactionArrayList) {

                                                if (t.getDate().getYear() == currentYear) {
                                                    // transactionArrayList.sort(Comparator.comparing(Transaction::getDate).thenComparing(Transaction::getTime).reversed());
                                                    DateTimeFormatter mTD = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                                                    String headerMonToD = ("\nDate\t\t| Description\t\t\t\t\t\t\t\t\t\t\t\t   | Vendor\t\t\t\t   |   Amount\n");
                                                    String formattedDate = t.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                                                    String formatTrans = String.format("%-12s| %-60s| %-22s|%10.2f\n",
                                                            formattedDate, t.getDescription(), t.getVendor(), t.getAmount());
                                                    //todo: formate transaction
                                                    System.out.println(formatTrans);
                                                }
                                            }
                                            break;
                                        case "4":
                                            System.out.println("Running Previous Year report...");
                                            int prevYear = LocalDate.now().getYear() - 1;

                                            for (Transaction t : transactionArrayList) {
                                                if (t.getDate().getYear() == prevYear) {
                                                    DateTimeFormatter mTD = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                                                    String headerMonToD = ("\nDate\t\t| Description\t\t\t\t\t\t\t\t\t\t\t\t   | Vendor\t\t\t\t   |   Amount\n");
                                                    String formattedDate = t.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                                                    String formatTrans = String.format("%-12s| %-60s| %-22s|%10.2f\n",
                                                            formattedDate, t.getDescription(), t.getVendor(), t.getAmount());
                                                    // transactionArrayList.sort(Comparator.comparing(Transaction::getDate).thenComparing(Transaction::getTime).reversed());
                                                    System.out.println(formatTrans);
                                                }


                                            }
                                            break;
                                        case "5":
                                            System.out.println("Enter vendor name to search:");
                                            String vendorSearch = myscanner.nextLine();
                                            for (Transaction t : transactionArrayList) {
                                                if (t.getVendor().equalsIgnoreCase(vendorSearch))
                                                //  transactionArrayList.sort(Comparator.comparing(Transaction::getDate).thenComparing(Transaction::getTime).reversed());
                                                {
                                                    DateTimeFormatter mTD = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                                                    String headerMonToD = ("\nDate\t\t| Description\t\t\t\t\t\t\t\t\t\t\t\t   | Vendor\t\t\t\t   |   Amount\n");
                                                    String formattedDate = t.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                                                    String formatTrans = String.format("%-12s| %-60s| %-22s|%10.2f\n",
                                                            formattedDate, t.getDescription(), t.getVendor(), t.getAmount());
                                                    System.out.println(formatTrans);
                                                }
                                            }
                                            break;
                                        case "0":
                                            // Exit Reports submenu
                                            inReportsMenu = false;
                                            break;
                                        default:
                                            System.out.println("Invalid selection. Try again.");
                                            break;
                                    }
                                }
                                break;

                            case "H":
                                // Exit the Ledger submenu loop and return to Home
                                inLedgerMenu = false;
                                break;
                            // default:
                            //  System.out.println("Invalid selection. Try again.");
                            //  break;

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
    }


}

//  transaction objects print out. ,
// sout needs the transaction class to override to string class overrides
//  . If it doesn’t, you’ll just see memory references like . transaction783920
//@Override
//public String toString() {
//    return date + " | " + time + " | " + description + " | " + vendor + " | " + amount;
//}