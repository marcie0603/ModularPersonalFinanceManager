package com.mpfm.service;

import com.mpfm.model.Entry;
import com.mpfm.model.EntryType;
import com.mpfm.repository.EntryRepository;

import java.time.LocalDate;
import java.util.List;

public class EntryService {

    private final EntryRepository repository;

    public EntryService(EntryRepository repository) {
        this.repository = repository;
    }

    public void addIncome(double amount, String description, LocalDate date) {
        Entry income = new Entry(EntryType.INCOME, amount, description, date);
        repository.addEntry(income);
    }

    public void addExpense(double amount, String description, LocalDate date) {
        Entry expense = new Entry(EntryType.EXPENSE, amount, description, date);
        repository.addEntry(expense);
    }

    public List<Entry> getAllEntries() {
        return repository.getAllEntries();
    }

    public List<Entry> getEntriesByType(EntryType type) {
        return repository.findByType(type);
    }

}
