package com.mpfm.repository;

import com.mpfm.model.Entry;
import com.mpfm.model.EntryType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntryRepositoryTest {

    private EntryRepository repository;

    @BeforeEach
    void setUp() {
        repository = new EntryRepository();
    }

    @Test
    void testAddAndRetrieveEntries() {
        Entry entry = new Entry(EntryType.INCOME, 5000.0, "Salary", LocalDate.now());
        repository.addEntry(entry);

        List<Entry> entries = repository.getAllEntries();
        assertEquals(1, entries.size());
        assertEquals("Salary", entries.get(0).getDescription());
    }

    @Test
    void testFindByType() {
        Entry income = new Entry(EntryType.INCOME, 2000.0, "Freelance", LocalDate.now());
        Entry expense = new Entry(EntryType.EXPENSE, 150.0, "Groceries", LocalDate.now());

        repository.addEntry(income);
        repository.addEntry(expense);

        List<Entry> incomes = repository.findByType(EntryType.INCOME);
        assertEquals(1, incomes.size());
        assertEquals("Freelance", incomes.get(0).getDescription());
    }
}
