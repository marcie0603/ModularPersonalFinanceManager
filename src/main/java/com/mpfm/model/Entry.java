package com.mpfm.model;

import java.time.LocalDate;
import java.util.UUID;

public class Entry {
    private final UUID id;
    private EntryType type;
    private double amount;
    private String description;
    private LocalDate date;

    public Entry(EntryType type, double amount, String description, LocalDate date) {
        this.id = UUID.randomUUID();
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    public UUID getId() {
        return id;
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
