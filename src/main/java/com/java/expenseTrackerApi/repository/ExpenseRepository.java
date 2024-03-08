package com.java.expenseTrackerApi.repository;

import com.java.expenseTrackerApi.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    Page<Expense> findByCategory(String category, Pageable page);

    // select * from tbl_expenses where name LIKE '%keyboard%'
    Page<Expense> findByNameContaining(String name,Pageable page);

    // select * from tbl_expenses where Date between 'strtDate' and 'endDate'
    Page<Expense> findByDateBetween(Date strtDate, Date endDate,Pageable page );
}
