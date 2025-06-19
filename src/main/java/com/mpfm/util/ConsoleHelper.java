package com.mpfm.util;

import com.mpfm.model.EntryType;

import java.util.Scanner;

public class ConsoleHelper {

    private static final Scanner scanner = new Scanner(System.in);

    public static String prompt(String message) {
        System.out.println(message + ": ");
        return scanner.nextLine().trim();
    }

    public static double promptDouble(String message) {
        while (true) {
            try {
                System.out.println(message + ": ");
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    public static EntryType promptEntryType() {
        while (true) {
            try {
                String input = prompt("Entry Type (INCOME / EXPENSE)").trim().toUpperCase();
                return EntryType.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid type. Please enter INCOME or EXPENSE.");
            }
        }
    }

    public static void showMenu() {
        System.out.println("\n== Finance Manager Menu ==");
        System.out.println("1. Add Entry");
        System.out.println("2. View All Entries");
        System.out.println("3. View Entries By Type");
        System.out.println("4. View Total Income ");
        System.out.println("5. View Total Expenses");
        System.out.println("6. View Net Balance");
        System.out.println("0. Exit");
    }

    public static int promptMenuChoice() {
        while (true) {
            try {
                System.out.println("Choose an option: ");
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number");
            }
        }
    }
}
