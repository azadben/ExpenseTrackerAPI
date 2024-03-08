package com.java.expenseTrackerApi.service;

import com.java.expenseTrackerApi.entity.Expense;
import com.java.expenseTrackerApi.exceptions.ResourceNotFoundException;
import com.java.expenseTrackerApi.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;


    @Override
    public Page<Expense> getAllExpenses(Pageable page) {
        return expenseRepository.findAll(page);
    }

    @Override
    public Expense getExpenseById(Long Id) {

        Optional<Expense> expenseById = expenseRepository.findById(Id);
        if (expenseById.isPresent()) {
            return expenseById.get();
        }
        throw new ResourceNotFoundException("Expense not found for the Id: " + Id);
    }

    @Override
    public void deleteExpenseById(Long Id) {
        Expense expense = getExpenseById(Id);
        expenseRepository.delete(expense);
    }

    @Override
    public Expense saveExpenseDetails(Expense expense) {
        return expenseRepository.save(expense);
    }

    @Override
    public Expense updateExpenseDetails(Expense expense, Long Id) {
        Expense existingExpense = getExpenseById(Id);
        existingExpense.setName(expense.getName() != null ? expense.getName() : existingExpense.getName());
        existingExpense.setAmount(expense.getAmount() != null ? expense.getAmount() : existingExpense.getAmount());
        existingExpense.setCategory(expense.getCategory() != null ? expense.getCategory() : existingExpense.getCategory());
        existingExpense.setDescription(expense.getDescription() != null ? expense.getDescription() : existingExpense.getDescription());
        existingExpense.setDate(expense.getDate() != null ? expense.getDate() : existingExpense.getDate());
        return expenseRepository.save(existingExpense);
    }

    @Override
    public Page<Expense> readByCategory(String category, Pageable page) {
        return expenseRepository.findByCategory(category,page);
    }

    @Override
    public Page<Expense> readByNameContaining(String name, Pageable page) {
        return expenseRepository.findByNameContaining(name,page);
    }

    @Override
    public List<Expense> readByDate(Date strtDate, Date endDate, Pageable page) {
        if(strtDate==null)
        {
            strtDate=new Date(0);
        }
        if (endDate==null)
        {
            endDate=new Date(System.currentTimeMillis());
        }
        return expenseRepository.findByDateBetween(strtDate,endDate,page).toList();
    }


}
