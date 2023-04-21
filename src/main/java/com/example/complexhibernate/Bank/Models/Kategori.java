package com.example.complexhibernate.Bank.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Kategori {
    @Id
    @GeneratedValue
    private long id;
    private String name;

    public Kategori(String name) {
        this.name = name;
    }
}
