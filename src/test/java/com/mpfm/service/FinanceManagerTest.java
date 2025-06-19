package com.mpfm.service;

import com.mpfm.model.Entry;
import com.mpfm.model.EntryType;
import com.mpfm.repository.EntryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FinanceManagerTest {

    private FinanceManager manager;

    @BeforeEach
    void setUp() {
        manager = new FinanceManager(new EntryRepository());
    }

    @Test
    void testAddAndRetrieveEntry() {
        Entry entry = new Entry(EntryType.EXPENSE, 100.0 ,"Lunch", LocalDate.now());
        manager.addEntry(entry);

        List<Entry> entries = manager.getAllEntries();
        assertEquals(1, entries.size());
        assertEquals("Lunch", entries.get(0).getDescription());
    }

    @Test
    void testEntriesByType() {
        manager.addEntry(new Entry(EntryType.INCOME, 500.0, "Job", LocalDate.now()));
        manager.addEntry(new Entry(EntryType.EXPENSE, 100.0, "Food", LocalDate.now()));

        List<Entry> incomes = manager.getEntriesByType(EntryType.INCOME);
        assertEquals(1, incomes.size());
        assertEquals("Job", incomes.get(0).getDescription());
    }

    @Test
    void testGetTotalByType() {
        manager.addEntry(new Entry(EntryType.INCOME, 1000.0, "Salary", LocalDate.now()));
        manager.addEntry(new Entry(EntryType.INCOME, 500.0, "Freelance", LocalDate.now()));
        manager.addEntry(new Entry(EntryType.EXPENSE, 300.0, "Groceries", LocalDate.now()));

        assertEquals(1500.0, manager.getTotalByType(EntryType.INCOME));
        assertEquals(300.0, manager.getTotalByType(EntryType.EXPENSE));
    }

    @Test
    void testGetNetBalance() {
        manager.addEntry(new Entry(EntryType.INCOME, 2000.0, "Main Job", LocalDate.now()));
        manager.addEntry(new Entry(EntryType.EXPENSE, 800.0, "Rent", LocalDate.now()));

        assertEquals(1200.0, manager.getNetBalance());
    }
}
