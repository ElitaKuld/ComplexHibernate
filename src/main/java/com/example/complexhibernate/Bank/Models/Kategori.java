package com.example.complexhibernate.Bank.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Kategori {
    @Id
    @GeneratedValue
    private long id;
    private String name;

    @OneToMany(mappedBy = "kategori")
    //@JsonIgnore //TEST
    private List<Kund> kunder;

    public Kategori(String name) {
        this.name = name;
    }
}
