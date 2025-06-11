package com.mpfm.model;

import java.time.LocalDate;

public class Entry {
    private EntryType type;
    private double amount;
    private String description;
    private LocalDate date;

    public Entry(EntryType type, double amount, String description, LocalDate date) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    public EntryType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "[" + type + "] " + description + " - " + amount + " (" + date + ")";
    }
}
