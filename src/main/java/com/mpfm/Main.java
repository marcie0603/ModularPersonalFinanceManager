package com.mpfm;

import com.mpfm.model.EntryType;
import com.mpfm.repository.EntryRepository;
import com.mpfm.service.FinanceManager;
import com.mpfm.util.ConsoleHelper;

public class Main {
    public static void main(String[] args) {
        FinanceManager manager = new FinanceManager(new EntryRepository());

        boolean running = true;

        while (running) {
            ConsoleHelper.showMenu();
            int choice = ConsoleHelper.promptMenuChoice();

            switch (choice) {
                case 1 -> {
                    EntryType type = ConsoleHelper.promptEntryType();
                    double amount = ConsoleHelper.promptDouble("Amount");
                    String desc = ConsoleHelper.prompt("Description");
                    manager.addEntry(type, amount, desc);
                    System.out.println("Entry added");
                }
                case 2 -> {
                    var entries = manager.getAllEntries();
                    if (entries.isEmpty()) {
                        System.out.println("No entries found");
                    } else {
                        entries.forEach(System.out::println);
                    }
                }
                case 3 -> {
                    EntryType type = ConsoleHelper.promptEntryType();
                    var filtered = manager.getEntriesByType(type);
                    if (filtered.isEmpty()) {
                        System.out.println("No entries found for type " + type);
                    } else {
                        filtered.forEach(System.out::println);
                    }
                }
                case 4 -> System.out.println("Total Income: " + manager.getTotalByType(EntryType.INCOME));
                case 5 -> System.out.println("Total Expenses: " + manager.getTotalByType(EntryType.EXPENSE));
                case 6 -> System.out.println("Net Balance: " + manager.getNetBalance());
                case 0 -> {
                    running = false;
                    System.out.println("Exiting application. Goodbye!");
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
}