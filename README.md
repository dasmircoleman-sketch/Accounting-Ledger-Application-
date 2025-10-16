[README.MD](https://github.com/user-attachments/files/22958369/README.MD)

💰 Personal Ledger Console App

Welcome to the Personal Ledger – a Java console application designed to help you track deposits and payments, and view powerful financial reports, all within a simple menu-driven interface.

📜 Project Description

The Personal Ledger app allows users to:

Log Deposits and Payments

View an organized Ledger of all transactions

Filter transactions by type (Deposit or Payment)

Generate Reports (Month-to-Date, Previous Month, Year-to-Date, etc.)

Search transactions by Vendor

Exit and return seamlessly between screens

All transactions are stored persistently in a CSV file (transactions.csv), and the app loads existing transactions every time the ledger is accessed.

🧑‍💻 How to Use
🏠 Home Screen

The application starts on the Home Screen, which presents these options:

💰💰Ledger Home Screen💰💰
Choose an Option:
D. Add Deposit
P. Make A Payment
L. Ledger
X. Exit

💳 Add Deposit

Enter a description, vendor, and amount.

The deposit is saved as a positive number in the CSV file.

💸 Make a Payment

Enter a description, vendor, and amount.

The payment is saved as a negative number in the CSV file.

📚 Ledger

Displays your transaction history in newest first order with options to:

A) Show All Transactions

D) Show Only Deposits

P) Show Only Payments

R) Reports Menu

H) Return to Home Screen

📊 Reports Menu

Inside the Ledger, select R to access the Reports:

Reports Menu
1. Month To Date
2. Previous Month
3. Year To Date
4. Previous Year
5. Search by Vendor
0. Back

✅ Report Features:

Auto filters by date or vendor name

Formatted output with clean columns for readability


💡 Notable Code Snippet

Here’s a look at the Deposit Creation & File Saving Logic, which adds a new transaction to your CSV:

System.out.println("What is the correct amount of the deposit");
double amount = myscanner.nextDouble();
Transaction newTransaction = new Transaction(LocalDate.now(), LocalTime.now(), description, vendor, amount);
BufferedWriter csvToWriteTo = new BufferedWriter(new FileWriter("src/main/resources/transactions.csv", true));
csvToWriteTo.newLine();
csvToWriteTo.write(newTransaction.getDate() + "|" + newTransaction.getTime() +
"|" + newTransaction.getDescription() + "|" +
newTransaction.getVendor() + "|" + newTransaction.getAmount());
csvToWriteTo.close();
System.out.println("Deposit Accepted, Thank you ❤️💰");


This cleanly formats and persists each transaction to the file using a | delimiter.

🧪 Potential Enhancements (Extra Credit Ideas)

✅ Add Custom Search with filters for:

Start/End Date

Description

Vendor

Amount

🎨
📁 Project Structure
/src
└── /main
└── /resources
└── transactions.csv   ← Stores all deposit/payment data
└── Ledger.java                  ← Main application logic
└── Transaction.java             ← Transaction model (date, time, description, vendor, amount)

🔧 Tech Stack

Java (Standard Edition)

CSV File I/O

Console-based UI

Java Date/Time API (LocalDate, LocalTime)

Collections API (ArrayList, Comparator)

🔗 GitHub Repository
https://github.com/dasmircoleman-sketch/Accounting-Ledger-Application-
✅ 

✅ Submission Checklist

Home screen with required options

Add Deposit

Make Payment

Ledger with filters

Reports with 5 options

Proper CSV data handling

README file with images & code snippet

Public GitHub repo with commits
