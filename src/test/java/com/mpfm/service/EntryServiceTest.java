package com.mpfm.service;

import com.mpfm.model.Entry;
import com.mpfm.model.EntryType;
import com.mpfm.repository.EntryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntryServiceTest {

    private EntryService service;

    @BeforeEach
    void setUp() {
        service = new EntryService(new EntryRepository());
    }

    @Test
    void testAddIncome() {
        service.addIncome(1000.0, "Salary", LocalDate.now());

        List<Entry> entries = service.getAllEntries();
        assertEquals(1, entries.size());
        assertEquals(EntryType.INCOME, entries.get(0).getType());
        assertEquals("Salary", entries.get(0).getDescription());
    }

    @Test
    void testAddExpense() {
        service.addExpense(200.0, "Groceries", LocalDate.now());

        List<Entry> entries = service.getAllEntries();
        assertEquals(1, entries.size());
        assertEquals(EntryType.EXPENSE, entries.get(0).getType());
        assertEquals("Groceries", entries.get(0).getDescription());
    }

    @Test
    void testGetEntriesByType() {
        service.addIncome(1500.0, "Freelance", LocalDate.now());
        service.addExpense(100.0, "Snacks", LocalDate.now());

        List<Entry> incomes = service.getEntriesByType(EntryType.INCOME);
        List<Entry> expenses = service.getEntriesByType(EntryType.EXPENSE);

        assertEquals(1, incomes.size());
        assertEquals("Freelance", incomes.get(0).getDescription());

        assertEquals(1, expenses.size());
        assertEquals("Snacks", expenses.get(0).getDescription());
    }
}
