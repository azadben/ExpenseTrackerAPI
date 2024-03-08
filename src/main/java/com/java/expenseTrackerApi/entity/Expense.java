package com.java.expenseTrackerApi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;

import java.sql.Date;
import java.sql.Timestamp;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "expense_name")
    @NotBlank(message = "Please enter your name")
    @Size(min = 3,message = "Name should be greater than 3 letter")
   private String name;

   private String description;

    @Column(name = "expense_amount")
    @NotNull(message = "Amount can't be empty")
   private BigDecimal amount;

    @NotNull(message = "category should not be null")
   private String category;

    @NotNull(message = "date should not be null")
   private Date date;

   @Column(name = "created_at", nullable = false, updatable = false)
   @CreationTimestamp
   private Timestamp createdAt;

   @Column(name = "updated_at")
   @UpdateTimestamp
   private Timestamp updatedAt;
}
