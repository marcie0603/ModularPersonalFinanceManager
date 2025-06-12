package com.mpfm.service;

import com.mpfm.model.Entry;
import com.mpfm.model.EntryType;
import com.mpfm.repository.EntryRepository;

import java.util.List;

public class FinanceManager {

    private final EntryRepository repository;

    public FinanceManager(EntryRepository repository) {
        this.repository = repository;
    }

    public void addEntry(EntryType type, double amount, String description) {
        Entry entry = new Entry(type, amount, description, java.time.LocalDate.now());
        repository.addEntry(entry);
    }

    public void addEntry(Entry entry){
        repository.addEntry(entry);
    }

    public List<Entry> getAllEntries() {
        return repository.getAllEntries();
    }

    public List<Entry> getEntriesByType(EntryType type) {
        return repository.findByType(type);
    }

    public double getTotalByType(EntryType type) {
        return getEntriesByType(type).stream()
                .mapToDouble(Entry::getAmount)
                .sum();
    }
}
