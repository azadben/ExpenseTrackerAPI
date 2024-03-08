package com.java.expenseTrackerApi.controller;

import com.java.expenseTrackerApi.entity.Expense;
import com.java.expenseTrackerApi.service.ExpenseService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;


    // return expenseRepository.findAll(page); gives Page<Expense> getAllExpenses(Pageable page)
    @GetMapping("/expenses")
    public List<Expense> getAllExpenses(Pageable page)
    {
        return expenseService.getAllExpenses(page).toList();
    }

    @GetMapping("/expenses/{id}")
    public Expense getExpenseById(@PathVariable("id") Long Id)
    {
        return expenseService.getExpenseById(Id);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/expenses")
    public String deleteExpenseById(@RequestParam("id") Long Id)
    {
         expenseService.deleteExpenseById(Id);

        return "Expense deleted with Id "+Id;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/expenses")
    public Expense saveExpenseDetails(@Valid @RequestBody Expense expense)
    {
        return expenseService.saveExpenseDetails(expense);
    }

    @PutMapping("/expenses/{id}")
    public Expense updateExpenseDetails(@RequestBody Expense expense,
                                        @PathVariable("id") Long Id)
    {
        return expenseService.updateExpenseDetails(expense,Id);
    }

    @GetMapping("/expenses/category")
    public List<Expense> getAllExpensesByCategory(@RequestParam String category, Pageable page)
    {
        return expenseService.readByCategory(category,page).toList();
    }

    @GetMapping("/expenses/name")
    public List<Expense> getAllExpensesByName(@RequestParam String name, Pageable page)
    {
        return expenseService.readByNameContaining(name,page).toList();
    }

    @GetMapping("/expenses/date")
    public List<Expense> getAllExpensesByDate(@RequestParam(required = false) Date strtDate,
                                              @RequestParam(required = false) Date endDate,
                                              Pageable page)
    {
        return expenseService.readByDate(strtDate,endDate,page);
    }
}
