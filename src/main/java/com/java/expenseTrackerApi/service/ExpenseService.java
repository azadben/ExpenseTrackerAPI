package com.java.expenseTrackerApi.service;

import com.java.expenseTrackerApi.entity.Expense;
import org.springframework.data.domain.ManagedTypes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.util.List;


public interface ExpenseService {

    public Page<Expense> getAllExpenses(Pageable page);

    public Expense getExpenseById(Long Id) ;

    void deleteExpenseById(Long Id);

    Expense saveExpenseDetails(Expense expense);

    Expense updateExpenseDetails(Expense expense, Long Id);

    Page<Expense> readByCategory(String category, Pageable page);

    Page<Expense> readByNameContaining(String category, Pageable page);

    List<Expense> readByDate(Date strtDate, Date endDate, Pageable page);
}
