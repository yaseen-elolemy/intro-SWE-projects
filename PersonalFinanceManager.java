package UniPackage;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PersonalFinanceManager {
    private static List<Transaction> transactions = new ArrayList<>();
    private static List<Category> categories = new ArrayList<>();
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    static {
        dateFormat.setLenient(false);
    }


    public static void main(String[] args) {
        initializeCategories();
        
        loadTransactions();

        // Main menu
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nPersonal Finance Manager");
            System.out.println("1. Add Transaction");
            System.out.println("2. View Transactions");
            System.out.println("3. View Summary");
            System.out.println("4. Edit Transaction");
            System.out.println("5. Delete Transaction");
            System.out.println("6. Manage Categories");
            System.out.println("7. Filter Transactions by Category");
            System.out.println("8. View Monthly Summary");
            System.out.println("9. Save and Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addTransaction(scanner);
                    break;
                case 2:
                    viewTransactions();
                    break;
                case 3:
                    viewSummary();
                    break;
                case 4:
                    editTransaction(scanner);
                    break;
                case 5:
                    deleteTransaction(scanner);
                    break;
                case 6:
                    manageCategories(scanner);
                    break;
                case 7:
                    filterTransactionsByCategory(scanner);
                    break;
                case 8:
                    viewMonthlySummary(scanner);
                    break;
                case 9:
                    saveTransactions();
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initializeCategories() {
        categories.add(new Category("Food"));
        categories.add(new Category("Rent"));
        categories.add(new Category("Utilities"));
        categories.add(new Category("Entertainment"));
        categories.add(new Category("Salary"));
        categories.add(new Category("Other Income"));
        categories.add(new Category("Other Expense"));
    }

    private static void addTransaction(Scanner scanner) {
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter date (yyyy-MM-dd): ");
        String dateStr = scanner.nextLine();
        Date date;
        try {
            date = dateFormat.parse(dateStr);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            return;
        }

        System.out.println("Choose a category:");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).getName());
        }
        int categoryChoice = scanner.nextInt();
        scanner.nextLine();

        if (categoryChoice < 1 || categoryChoice > categories.size()) {
            System.out.println("Invalid category choice.");
            return;
        }

        String category = categories.get(categoryChoice - 1).getName();
        Transaction transaction = new Transaction(amount, category, date);
        transactions.add(transaction);
        System.out.println("Transaction added.");
    }

    private static void viewTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        System.out.println("\nTransactions:");
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    private static void viewSummary() {
        double totalIncome = 0;
        double totalExpense = 0;

        for (Transaction transaction : transactions) {
            if (transaction.getAmount() > 0) {
                totalIncome += transaction.getAmount();
            } else {
                totalExpense += transaction.getAmount();
            }
        }

        System.out.println("\nSummary:");
        System.out.println("Total Income: " + totalIncome);
        System.out.println("Total Expense: " + totalExpense);
        System.out.println("Net Balance: " + (totalIncome + totalExpense));
    }

    private static void editTransaction(Scanner scanner) {
        viewTransactions();
        if (transactions.isEmpty()) {
            return;
        }

        System.out.print("Enter the index of the transaction to edit: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index < 1 || index > transactions.size()) {
            System.out.println("Invalid index.");
            return;
        }

        Transaction transaction = transactions.get(index - 1);

        System.out.print("Enter new amount (current: " + transaction.getAmount() + "): ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter new date (yyyy-MM-dd) (current: " + dateFormat.format(transaction.getDate()) + "): ");
        String dateStr = scanner.nextLine();
        Date date;
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            return;
        }

        System.out.println("Choose a new category:");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).getName());
        }
        int categoryChoice = scanner.nextInt();
        scanner.nextLine();

        if (categoryChoice < 1 || categoryChoice > categories.size()) {
            System.out.println("Invalid category choice.");
            return;
        }

        String category = categories.get(categoryChoice - 1).getName();
        transaction.setAmount(amount);
        transaction.setDate(date);
        transaction.setCategory(category);
        System.out.println("Transaction updated.");
    }

    private static void deleteTransaction(Scanner scanner) {
        viewTransactions();
        if (transactions.isEmpty()) {
            return;
        }

        System.out.print("Enter the index of the transaction to delete: ");
        int index = scanner.nextInt();
        scanner.nextLine();
        if (index < 1 || index > transactions.size()) {
            System.out.println("Invalid index.");
            return;
        }

        transactions.remove(index - 1);
        System.out.println("Transaction deleted.");
    }

    private static void manageCategories(Scanner scanner) {
        while (true) {
            System.out.println("\nManage Categories");
            System.out.println("1. Add Category");
            System.out.println("2. Edit Category");
            System.out.println("3. Delete Category");
            System.out.println("4. View Categories");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addCategory(scanner);
                    break;
                case 2:
                    editCategory(scanner);
                    break;
                case 3:
                    deleteCategory(scanner);
                    break;
                case 4:
                    viewCategories();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addCategory(Scanner scanner) {
        System.out.print("Enter category name: ");
        String name = scanner.nextLine();
        categories.add(new Category(name));
        System.out.println("Category added.");
    }

    private static void editCategory(Scanner scanner) {
        viewCategories();
        if (categories.isEmpty()) {
            return;
        }

        System.out.print("Enter the index of the category to edit: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index < 1 || index > categories.size()) {
            System.out.println("Invalid index.");
            return;
        }

        Category category = categories.get(index - 1);
        System.out.print("Enter new category name (current: " + category.getName() + "): ");
        String name = scanner.nextLine();
        category.setName(name);
        System.out.println("Category updated.");
    }

    private static void deleteCategory(Scanner scanner) {
        viewCategories();
        if (categories.isEmpty()) {
            return;
        }

        System.out.print("Enter the index of the category to delete: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index < 1 || index > categories.size()) {
            System.out.println("Invalid index.");
            return;
        }

        categories.remove(index - 1);
        System.out.println("Category deleted.");
    }

    private static void viewCategories() {
        if (categories.isEmpty()) {
            System.out.println("No categories found.");
            return;
        }

        System.out.println("\nCategories:");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).getName());
        }
    }

    private static void filterTransactionsByCategory(Scanner scanner) {
        viewCategories();
        if (categories.isEmpty()) {
            return;
        }

        System.out.print("Enter the index of the category to filter by: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index < 1 || index > categories.size()) {
            System.out.println("Invalid index.");
            return;
        }

        String category = categories.get(index - 1).getName();
        System.out.println("\nTransactions for category: " + category);
        for (Transaction transaction : transactions) {
            if (transaction.getCategory().equals(category)) {
                System.out.println(transaction);
            }
        }
    }

    private static void viewMonthlySummary(Scanner scanner) {
        System.out.print("Enter year and month (yyyy-MM): ");
        String yearMonth = scanner.nextLine();
        double totalIncome = 0;
        double totalExpense = 0;

        for (Transaction transaction : transactions) {
            String transactionYearMonth = dateFormat.format(transaction.getDate()).substring(0, 7);
            if (transactionYearMonth.equals(yearMonth)) {
                if (transaction.getAmount() > 0) {
                    totalIncome += transaction.getAmount();
                } else {
                    totalExpense += transaction.getAmount();
                }
            }
        }

        System.out.println("\nMonthly Summary for " + yearMonth + ":");
        System.out.println("Total Income: " + totalIncome);
        System.out.println("Total Expense: " + totalExpense);
        System.out.println("Net Balance: " + (totalIncome + totalExpense));
    }

    private static void saveTransactions() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("transactions.txt"))) {
            for (Transaction transaction : transactions) {
                writer.println(transaction.getAmount() + "," + transaction.getCategory() + "," + dateFormat.format(transaction.getDate()));
            }
        } catch (IOException e) {
            System.out.println("Error saving transactions: " + e.getMessage());
        }
    }

    private static void loadTransactions() {
        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                double amount = Double.parseDouble(parts[0]);
                String category = parts[1];
                Date date = dateFormat.parse(parts[2]);
                transactions.add(new Transaction(amount, category, date));
            }
        } catch (IOException | ParseException e) {
            System.out.println("No previous transactions found.");
        }
    }
}

class Transaction {
    private double amount;
    private String category;
    private Date date;

    public Transaction(double amount, String category, Date date) {
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Amount: " + amount + ", Category: " + category + ", Date: " + PersonalFinanceManager.dateFormat.format(date);
    }
}

class Category {
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}