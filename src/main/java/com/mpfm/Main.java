package com.mpfm;

import com.mpfm.model.EntryType;
import com.mpfm.repository.EntryRepository;
import com.mpfm.service.FinanceManager;
import com.mpfm.util.ConsoleHelper;

import java.time.LocalDate;

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
                case 7 -> {
                    LocalDate start = LocalDate.parse(ConsoleHelper.prompt("Start date (YYYY-MM-DD)"));
                    LocalDate end = LocalDate.parse(ConsoleHelper.prompt("End date (YYYY-MM-DD)"));
                    var filtered = manager.getEntriesBetween(start, end);
                    if (filtered.isEmpty()) {
                        System.out.println("No entries found in the given date range");
                    } else {
                        filtered.forEach(System.out::println);
                    }
                }
                case 0 -> {
                    running = false;
                    System.out.println("Exiting application. Goodbye!");
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
}