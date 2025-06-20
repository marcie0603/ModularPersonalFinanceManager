package com.mpfm.repository;

import com.mpfm.model.Entry;
import com.mpfm.model.EntryType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EntryRepository {

    private final List<Entry> entries;

    public EntryRepository() {
        this.entries = EntryStorage.load();
    }

    public void addEntry(Entry entry) {
        entries.add(entry);
        EntryStorage.save(entries);
    }

    public List<Entry> getAllEntries() {
        return new ArrayList<>(entries);
    }

    public List<Entry> findByType(EntryType type) {
        return entries.stream()
                .filter(entry -> entry.getType() == type)
                .collect(Collectors.toList());
    }

    public void clearEntries() {
        entries.clear();
    }
}
