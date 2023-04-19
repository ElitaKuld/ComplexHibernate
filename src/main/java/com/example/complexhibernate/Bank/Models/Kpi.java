package com.example.complexhibernate.Bank.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Kpi {
    @Id
    @GeneratedValue
    private long id;
    private int kreditvärdighet;

    public Kpi(int credit) {
        this.kreditvärdighet = credit;
    }
}