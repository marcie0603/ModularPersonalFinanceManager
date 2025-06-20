package com.mpfm.service;

import com.mpfm.model.Entry;
import com.mpfm.model.EntryType;
import com.mpfm.repository.EntryRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    public double getNetBalance() {
        return getTotalByType(EntryType.INCOME) - getTotalByType(EntryType.EXPENSE);
    }

    public List<Entry> getEntriesBetween(LocalDate start, LocalDate end){
        return repository.getAllEntries().stream()
                .filter(entry -> !entry.getDate().isBefore(start) && !entry.getDate().isAfter(end))
                .collect(Collectors.toList());
    }

}
