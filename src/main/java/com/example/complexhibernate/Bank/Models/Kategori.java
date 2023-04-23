package com.example.complexhibernate.Bank.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
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
